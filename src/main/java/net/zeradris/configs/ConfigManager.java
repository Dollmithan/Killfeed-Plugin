package net.zeradris.configs;

import java.util.ArrayList;
import java.util.List;

import net.zeradris.main.Main;

public class ConfigManager {

	Main instance = Main.instance;

	public ConfigManager() {
		this.loadConfigs();
	}

	public CustomConfig config;
	public CustomConfig messages;
	public CustomConfig data;
	public List<CustomConfig> configlist = new ArrayList<CustomConfig>();

	// config.yml
	public CustomConfig getConfig() {
		return config;
	}

	// messages.yml
	public CustomConfig getMessages() {
		return messages;
	}

	// data.yml
	public CustomConfig getData() {
		return data;
	}
	
	public void loadConfigs() {

		config = new CustomConfig("config.yml");
		messages = new CustomConfig("messages.yml");
		data = new CustomConfig("data.yml");
		configlist.add(data);
		configlist.add(config);
		configlist.add(messages);
	}

	public void saveAll() {
		data.save();
		config.save();
		messages.save();
	}
	
	public void reloadconfigs() {
		for (CustomConfig cfg : configlist) {
			cfg.reloadConfig();
		}
	}

}
