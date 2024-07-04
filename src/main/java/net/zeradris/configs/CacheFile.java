package net.zeradris.configs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.zeradris.main.Main;
import net.zeradris.utils.Utils;

public class CacheFile {

	public File cache_file;
	private Main instance;

	public CacheFile(Main instance) {
		this.instance = instance;
		this.cache_file = new File(instance.getDataFolder(), "killfeed_cache.txt");
		this.createCacheFile();
	}

	private void createCacheFile() {
		if (!cache_file.exists()) {
			cache_file.getParentFile().mkdirs();
			instance.saveResource("killfeed_cache.txt", false);
		}
	}

	public void clear() {
		cache_file.delete();
		createCacheFile();
	}

	public void log(String text) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(cache_file, true));
			writer.append("[" + Utils.formattedTime() + "] " + text + "\n");
			writer.close();
		} catch (IOException e) {

		}
	}

}
