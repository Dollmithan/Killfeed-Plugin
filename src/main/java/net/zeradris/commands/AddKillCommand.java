package net.zeradris.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.zeradris.killmanager.KillManager;
import net.zeradris.main.Main;
import net.zeradris.utils.Utils;

public class AddKillCommand implements CommandExecutor {

	Main instance = Main.instance;
	KillManager mng = Main.manager;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender.hasPermission("killfeed.admin") || sender.isOp()) {

			if (args.length != 2) {
				sender.sendMessage(Utils.Color("&cINVALID ARGS! &c&l/addkill <killer> <victim>"));
				return true;
			}

			mng.addKill(args[0], args[1]);
			sender.sendMessage(Utils.Color("&eADDED SUBMITTED KILL"));
			return true;
		}

		sender.sendMessage(Utils.Color("&cINVALID PERMISSIONS"));

		return true;
	}

}
