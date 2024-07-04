package net.zeradris.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.zeradris.commands.AddKillCommand;
import net.zeradris.commands.KillFeedCommand;
import net.zeradris.commands.TestCommand;
import net.zeradris.configs.ConfigManager;
import net.zeradris.killmanager.KillManager;
import net.zeradris.placeholderapi.PlaceholderAPIHook;

public class Main extends JavaPlugin {

	public static Main instance;
	public static KillManager manager;

	  private BukkitAudiences adventure;

	  public @NonNull BukkitAudiences adventure() {
	    if(this.adventure == null) {
	      throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
	    }
	    return this.adventure;
	  }

	@Override
	public void onEnable() {
	    this.adventure = BukkitAudiences.create(this);
		instance = this;
		manager = new KillManager();
		ConfigManager configmanager = new ConfigManager();
		configmanager.reloadconfigs();

		this.getCommand("addkill").setExecutor(new AddKillCommand());
		this.getCommand("killfeed").setExecutor(new KillFeedCommand());
		this.getCommand("test").setExecutor(new TestCommand());

		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) { //
			new PlaceholderAPIHook().register(); //
		}
	}

	@Override
	public void onDisable() {
		if (this.adventure != null) {
			this.adventure.close();
			this.adventure = null;
		}
	}

}
