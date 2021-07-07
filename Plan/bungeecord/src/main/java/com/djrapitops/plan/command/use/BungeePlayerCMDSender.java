/*
 *  This file is part of Player Analytics (Plan).
 *
 *  Plan is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License v3 as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Plan is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Plan. If not, see <https://www.gnu.org/licenses/>.
 */
package com.djrapitops.plan.command.use;

import com.djrapitops.plan.commands.use.ChatFormatter;
import com.djrapitops.plan.commands.use.PlayerChatFormatter;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class BungeePlayerCMDSender extends BungeeCMDSender {

    private final ProxiedPlayer player;

    public BungeePlayerCMDSender(ProxiedPlayer player) {
        super(player);

        this.player = player;
    }

    @Override
    public Optional<String> getPlayerName() {
        return Optional.of(player.getName());
    }

    @Override
    public Optional<UUID> getUUID() {
        return Optional.of(player.getUniqueId());
    }

    @Override
    public ChatFormatter getFormatter() {
        return new PlayerChatFormatter();
    }

    @Override
    public boolean supportsChatEvents() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BungeePlayerCMDSender that = (BungeePlayerCMDSender) o;
        return player.equals(that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), player);
    }
}
