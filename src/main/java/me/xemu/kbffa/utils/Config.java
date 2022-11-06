package me.xemu.kbffa.utils;

import lombok.Getter;
import lombok.Setter;
import me.xemu.kbffa.Knockback;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class Config extends YamlConfiguration {

	private File file;
	private FileConfiguration config;

	public Config(String parent, String child) {
		this.file = new File(parent, child);
		this.config = YamlConfiguration.loadConfiguration(file);

		create();
	}

	public Config(String child) {
		this.file = new File(Knockback.getInstance().getDataFolder(), child);
		this.config = YamlConfiguration.loadConfiguration(file);

		create();
	}

	private void create() {
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
