package main.java.com.djrapitops.plan.data.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import main.java.com.djrapitops.plan.Plan;
import main.java.com.djrapitops.plan.Settings;
import main.java.com.djrapitops.plan.data.UserData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 *
 * @author Rsl1122
 */
public class InspectCacheHandler {

    private DataCacheHandler handler;
    private Plan plugin;
    private HashMap<UUID, UserData> cache;
    private HashMap<UUID, Long> clearTimes;

    /**
     * Class constructor
     *
     * @param plugin Current instance of Plan.class
     */
    public InspectCacheHandler(Plan plugin) {
        this.handler = plugin.getHandler();
        this.plugin = plugin;
        this.cache = new HashMap<>();
        this.clearTimes = new HashMap<>();
    }

    /**
     * Caches the UserData of user to the HashMap for X minutes. Data is removed
     * from the cache automatically after 5 minutes with a BukkitRunnable
     *
     * @param uuid UUID of the player
     */
    public void cache(UUID uuid) {
        int minutes = Settings.CLEAR_INSPECT_CACHE.getNumber();
        if (minutes <= 0) {
            minutes = 3;
        }
        cache(uuid, minutes);
    }

    /**
     *
     * @param uuid
     * @param minutes
     */
    public void cache(UUID uuid, int minutes) {
        DBCallableProcessor cacher = new DBCallableProcessor() {
            @Override
            public void process(UserData data) {
                cache.put(uuid, new UserData(data));
            }
        };
        handler.getUserDataForProcessing(cacher, uuid, false);
        long clearTime = new Date().toInstant().getEpochSecond() + (long) 60 * (long) minutes;
        if (clearTimes.get(uuid) == null) {
            clearTimes.put(uuid, (long) 0);
        }
        if (clearTimes.get(uuid) < clearTime) {
            clearTimes.put(uuid, clearTime);
            BukkitTask timedInspectCacheClearTask = (new BukkitRunnable() {
                @Override
                public void run() {
                    if (new Date().toInstant().getEpochSecond() - clearTimes.get(uuid) < 30) {
                        UserData uData = cache.get(uuid);
                        if (uData == null) {
                            this.cancel();
                            return;
                        }
                        if (!uData.isAccessed()) {
                            clearFomCache(uuid);
                        }
                    } 
                    this.cancel();
                }
            }).runTaskLater(plugin, 60 * 20 * minutes);
        }
    }

    private void clearFomCache(UUID uuid) {
        cache.remove(uuid);
    }

    /**
     * Checks the cache for UserData matching UUID
     *
     * @param uuid UUID of the Player
     * @return UserData that matches the player, null if not cached.
     */
    public UserData getFromCache(UUID uuid) {
        return cache.get(uuid);
    }

    /**
     * Check if the data of a player is in the inspect cache.
     *
     * @param uuid UUID of player.
     * @return true if cached.
     */
    public boolean isCached(UUID uuid) {
        return cache.containsKey(uuid);
    }
}
