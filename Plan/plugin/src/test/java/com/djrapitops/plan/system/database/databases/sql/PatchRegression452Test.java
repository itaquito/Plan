package com.djrapitops.plan.system.database.databases.sql;

import utilities.TestConstants;

public abstract class PatchRegression452Test {

    String insertServer = "INSERT INTO plan_servers (uuid) VALUES ('" + TestConstants.SERVER_UUID + "')";
    String insertUser = "INSERT INTO plan_users (uuid, name, registered) VALUES ('" + TestConstants.PLAYER_ONE_UUID + "', 'TestName', 1234)";
    String insertUser2 = "INSERT INTO plan_users (uuid, name, registered) VALUES ('" + TestConstants.PLAYER_TWO_UUID + "', 'TestName2', 1234)";
    String insertUserInfo = "INSERT INTO plan_user_info (user_id, registered, server_id) VALUES (1, 1234, 1)";
    String insertIP = "INSERT INTO plan_ips (user_id, ip, geolocation, ip_hash, last_used) VALUES (1, '1.1.1.1', 'Finland', 'hash', 1234)";
    String insertNickname = "INSERT INTO plan_nicknames (user_id, nickname, server_id, last_used) VALUES (1, 'Nickname', 1, 1234)";
    String insertSession = "INSERT INTO plan_sessions (user_id, server_id, session_start, session_end, mob_kills, deaths, afk_time) VALUES (1,1,1234,5678,2,2,2)";
    String insertKill = "INSERT INTO plan_kills (killer_id, session_id, server_id, victim_id,  weapon, date) VALUES (1,1,1, 2, 'Sword', 3456)";
    String insertWorld = "INSERT INTO plan_worlds (server_id, world_name) VALUES (1, 'World')";
    String insertWorldTimes = "INSERT INTO plan_world_times (user_id, server_id, world_id, session_id, survival_time) VALUES (1,1,1,1,1234)";


    protected void insertData(SQLDB underTest) {
        underTest.execute(insertServer);
        underTest.execute(insertUser);
        underTest.execute(insertUser2);
        underTest.execute(insertUserInfo);
        underTest.execute(insertIP);
        underTest.execute(insertNickname);
        underTest.execute(insertSession);
        underTest.execute(insertKill);
        underTest.execute(insertWorld);
        underTest.execute(insertWorldTimes);
    }
}