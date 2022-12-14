package me.xemu.kbffa;

import lombok.Getter;
import me.xemu.kbffa.data.Storage;
import me.xemu.kbffa.data.map.Map;
import me.xemu.kbffa.data.types.YamlStorage;
import me.xemu.kbffa.gui.GUIManager;
import me.xemu.kbffa.utils.Config;
import me.xemu.kbffa.utils.Message;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Knockback extends JavaPlugin {

	@Getter private static Knockback instance;

	@Getter private GUIManager guiManager;
	@Getter private Config config;
	@Getter private Config shop;
	@Getter private Config data;

	@Getter private Storage storage;

	// Maps
	@Getter private Map activeMap;
	@Getter private List<Map> maps;

	@Override
	public void onEnable() {
		new Message("§eKnockbackFFA is starting...").console();

		instance = this;

		guiManager = new GUIManager(this);
		config = new Config("config.yml");
		shop = new Config("shop.yml");
		data = new Config("data.yml");

		storage = new YamlStorage();

		new Message("§3Registering Commands & Events").console();

		registerCommands();
		registerEvents();

		maps = new ArrayList<>();

		activeMap = maps.stream().findAny().get();

		new Message("§aKnockbackFFA successfully started! For support, please use SpigotMC: @Xemu").console();
	}

	@Override
	public void onDisable() {
		new Message("§cSuccessfully disabled KnockbackFFA.").console();
	}

	private void registerCommands() {

	}

	private void registerEvents() {

	}

}