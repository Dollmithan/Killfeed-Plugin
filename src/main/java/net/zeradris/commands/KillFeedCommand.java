package net.zeradris.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.zeradris.killmanager.KillManager;
import net.zeradris.main.Main;
import net.zeradris.utils.Utils;

public class KillFeedCommand implements CommandExecutor {

	Main instance = Main.instance;
	KillManager manager = Main.manager;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender.hasPermission("killfeed.admin") && args.length > 0) {
			switch (args[0]) {

			case "clear":
				manager.clear();
				sender.sendMessage(Utils.Color("&eCleared the killfeed!"));
				break;
			default:
				sender.sendMessage(Utils.Color("&cWrong arguments! args available: &8&bCLEAR"));
			}
			return true;
		}

		sendKillFeed(sender);

		return true;
	}

	private void sendKillFeed(CommandSender sender) {

		List<String> msgs = manager.fullMessage();

		for (String str : msgs) {
			sender.sendMessage(str);
		}

	}

}
