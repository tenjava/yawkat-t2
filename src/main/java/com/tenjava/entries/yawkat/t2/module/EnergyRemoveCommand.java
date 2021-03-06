package com.tenjava.entries.yawkat.t2.module;

import com.tenjava.entries.yawkat.t2.Energy;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command to remove energy from players (including self).
 *
 * @author yawkat
 */
public class EnergyRemoveCommand extends EnergyChangeCommand {
    public EnergyRemoveCommand() {
        super("energyremove");
    }

    @Override
    protected void perform(CommandSender sender, Player target, double amount) {
        if (Energy.deductEnergy(target, amount, Energy.DeductFailurePolicy.FAIL_ROUND)) {
            sender.sendMessage(ChatColor.GOLD + "Removed " +
                               ChatColor.AQUA + Commands.toDisplayString(amount) +
                               ChatColor.GOLD + " energy from " +
                               ChatColor.AQUA + target.getName() +
                               ChatColor.GOLD + ".");
        } else {
            double only = Energy.getEnergy(target);
            only = Math.round(only * 100) / 100D;
            sender.sendMessage(Commands.ERROR_PREFIX + target.getName() + " only has " +
                               Commands.toDisplayString(only) + " energy.");
        }
    }
}
