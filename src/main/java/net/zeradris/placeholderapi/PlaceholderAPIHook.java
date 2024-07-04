package net.zeradris.placeholderapi;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.zeradris.killmanager.KillManager;
import net.zeradris.main.Main;

public class PlaceholderAPIHook extends PlaceholderExpansion {

	private final Main plugin = Main.instance; //
	private final KillManager manager = Main.manager;

	public PlaceholderAPIHook() {
	}

	@Override
	@NotNull
	public String getAuthor() {
		return String.join(", ", plugin.getDescription().getAuthors()); //
	}

	@Override
	@NotNull
	public String getIdentifier() {
		return "killfeed";
	}

	@Override
	@NotNull
	public String getVersion() {
		return plugin.getDescription().getVersion(); //
	}

	@Override
	public boolean persist() {
		return true;
	}

	@Override
	public String onPlaceholderRequest(Player player, @NotNull String params) {

		switch (params.toLowerCase()) {

		case "1_killer":
			return manager.getKiller(0);
		case "1_victim":
			return manager.getVictim(0);
		case "1_message":
			return manager.getMessage(0);
		case "1_timestamp":
			return manager.getTimeStamp(0);
		case "1_datestamp":
			return manager.getDateStamp(0);

		case "2_killer":
			return manager.getKiller(1);
		case "2_victim":
			return manager.getVictim(1);
		case "2_message":
			return manager.getMessage(1);
		case "2_timestamp":
			return manager.getTimeStamp(0);
		case "2_datestamp":
			return manager.getDateStamp(0);

		case "3_killer":
			return manager.getKiller(2);
		case "3_victim":
			return manager.getVictim(2);
		case "3_message":
			return manager.getMessage(2);
		case "3_timestamp":
			return manager.getTimeStamp(0);
		case "3_datestamp":
			return manager.getDateStamp(0);

		case "4_killer":
			return manager.getKiller(3);
		case "4_victim":
			return manager.getVictim(3);
		case "4_message":
			return manager.getMessage(3);
		case "4_timestamp":
			return manager.getTimeStamp(0);
		case "4_datestamp":
			return manager.getDateStamp(0);

		case "5_killer":
			return manager.getKiller(4);
		case "5_victim":
			return manager.getVictim(4);
		case "5_message":
			return manager.getMessage(4);
		case "5_timestamp":
			return manager.getTimeStamp(0);
		case "5_datestamp":
			return manager.getDateStamp(0);
		}

		return null; //
	}

}
