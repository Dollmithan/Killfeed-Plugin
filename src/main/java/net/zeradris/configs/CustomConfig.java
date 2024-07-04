package net.zeradris.configs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.zeradris.main.Main;

public class CustomConfig {

	private File customConfigFile;
	private FileConfiguration customConfig;
	private String name;
	private Main instance = Main.instance;

	public CustomConfig(String name) {
		this.name = name;
		this.createCustomConfig();
	}

	public FileConfiguration getCustomConfig() {
		return this.customConfig;
	}

	public void save() {
		try {
			customConfig.save(customConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void set(String path, String value) {
		getCustomConfig().set(path, value);
		save();
	}

	private void createCustomConfig() {
		customConfigFile = new File(instance.getDataFolder(), name);
		if (!customConfigFile.exists()) {
			customConfigFile.getParentFile().mkdirs();
			instance.saveResource(name, false);
		}
		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	}

	public void reloadConfig() {
		customConfig = new YamlConfiguration();
		try {
			customConfig.load(customConfigFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

}
