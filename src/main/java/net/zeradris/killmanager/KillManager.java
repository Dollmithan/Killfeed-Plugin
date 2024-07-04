package net.zeradris.killmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Lists;

import me.clip.placeholderapi.PlaceholderAPI;
import net.zeradris.configs.CacheFile;
import net.zeradris.configs.ConfigManager;
import net.zeradris.main.Main;
import net.zeradris.utils.Utils;

public class KillManager {

	public KillManager() {

	}

	Main instance = Main.instance;
	CacheFile cachef = new CacheFile(instance);
	ConfigManager cfgm = new ConfigManager();

	int MAX_FEED_LENGTH = 5;

	SortedMap<Integer, HashMap<String, String>> info = new TreeMap<>();
	public List<HashMap<String, String>> kills = new ArrayList<>();

	public void addKill(String killer, String killed) {
		Boolean cache = cfgm.getConfig().getCustomConfig().getBoolean("killfeed_cache");
		String cache_message = cfgm.getConfig().getCustomConfig().getString("cache_message");
		HashMap<String, String> tempmap = new HashMap<>();

		tempmap.put("killer", killer);
		tempmap.put("killed", killed);
		tempmap.put("timestamp", Utils.formattedTimeNow());
		tempmap.put("datestamp", Utils.formattedDateNow());

		kills.add(0, tempmap);
		if (cache) {
			cachef.log(Utils.formatMessage(cache_message, killer, killed));
		}

		if (kills.size() > MAX_FEED_LENGTH) {
			kills.remove(kills.size() - 1);
		}
		
	}

	public List<HashMap<String, String>> getKillFeed() {
		return kills;
	}

	public void clear() {
		kills.clear();
	}

	@Nullable
	public HashMap<String, String> getKillAtIndex(int index) {

		if (index >= kills.size() || index < 0) {
			return null;
		}

		return Lists.reverse(kills).get(index);
	}

	public String getTimeStamp(int index) {
		HashMap<String, String> entry = getKillAtIndex(index);

		return entry.get("timestamp");
	}

	public String getDateStamp(int index) {
		HashMap<String, String> entry = getKillAtIndex(index);

		return entry.get("datestamp");
	}

	@Nullable
	public String getKiller(int index) {
		HashMap<String, String> entry = getKillAtIndex(index);
		return entry == null ? "null" : entry.get("killer");
	}

	public String getVictim(int index) {
		HashMap<String, String> entry = getKillAtIndex(index);
		return entry == null ? "null" : entry.get("killed");
	}

	public String getMessage(int index) {

		FileConfiguration messages = new ConfigManager().getMessages().getCustomConfig();
		List<String> msgs = messages.getStringList("killfeed_message");

		HashMap<String, String> entry = getKillAtIndex(index);
		String empty_message = messages.getString("empty_message").replace("%killnumber%", String.valueOf(index+1));
		if (entry == null) {
			return Utils.Color(empty_message);
		}
		String formatted = Utils.formatMessage(msgs.get(index));
		return PlaceholderAPI.setPlaceholders(null, formatted);

	}

	public List<String> fullMessage() {
		FileConfiguration messages = new ConfigManager().getMessages().getCustomConfig();
		List<String> fullmessage = new ArrayList<>();

		List<String> headers = messages.getStringList("killfeed_header");
		List<String> footers = messages.getStringList("killfeed_footer");
		List<String> msgs = messages.getStringList("killfeed_message");

		
		for (String str : headers) {
			fullmessage.add(str);
		}

		for (int i = 0; i < msgs.size(); i++) {
			fullmessage.add(getMessage(i));
		}

		for (String str : footers) {
			fullmessage.add(str);
		}

		return fullmessage;
	}

}
