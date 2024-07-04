package net.zeradris.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;

import me.clip.placeholderapi.PlaceholderAPI;
import net.zeradris.configs.ConfigManager;
import net.zeradris.utils.Utils;

public class TestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length < 1) {
			return false;
		}

		String sds = "";

		for (String arg : args) {
			sds = sds + " " + arg;
		}

		String msg = PlaceholderAPI.setPlaceholders(null, sds);
		sender.sendMessage(Utils.Color(msg));
		
		ConfigManager manager = new ConfigManager();

		if (sender instanceof Player) {
			Player player = (Player) sender;
			TextDisplay text = player.getWorld().spawn(player.getLocation(), TextDisplay.class);
			text.setText(Utils.Color(msg));

			
			text.setBillboard(Billboard.CENTER);

		}

		return true;
	}

}
