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
package com.djrapitops.plan.commands.use;

import cn.nukkit.command.CommandSender;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class NukkitCMDSender implements CMDSender {

    final CommandSender sender;

    public NukkitCMDSender(CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public MessageBuilder buildMessage() {
        return new NukkitMessageBuilder(this);
    }

    @Override
    public Optional<String> getPlayerName() {
        return Optional.empty();
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }

    @Override
    public Optional<UUID> getUUID() {
        return Optional.empty();
    }

    @Override
    public void send(String message) {
        sender.sendMessage(message);
    }

    @Override
    public ChatFormatter getFormatter() {
        return new ConsoleChatFormatter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NukkitCMDSender that = (NukkitCMDSender) o;
        return sender.equals(that.sender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender);
    }
}
