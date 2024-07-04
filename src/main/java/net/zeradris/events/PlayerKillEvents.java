package net.zeradris.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.zeradris.configs.ConfigManager;
import net.zeradris.killmanager.KillManager;
import net.zeradris.main.Main;
import net.zeradris.utils.Utils;

public class PlayerKillEvents {

	KillManager mng = Main.manager;

	@EventHandler
	public void killevent(PlayerDeathEvent event) {

		if (!(event.getEntity().getKiller() instanceof Player)) {
			return;
		}

		Player victim = event.getEntity();
		Player killer = victim.getKiller();

		mng.addKill(killer.getName(), victim.getName());

		ConfigManager cfg = new ConfigManager();
		FileConfiguration messages = cfg.getMessages().getCustomConfig();

		if (!messages.getBoolean("kill_messages_enabled")) {
			event.setDeathMessage("");
			return;
		}
		String deathmsg = messages.getString("kill_message");
		String formatted = Utils.formatMessage(deathmsg, killer.getName(), victim.getName());
		if (deathmsg != "default") {
			event.setDeathMessage(formatted);
		}

	}
}
