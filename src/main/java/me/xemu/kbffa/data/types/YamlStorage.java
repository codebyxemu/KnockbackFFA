package me.xemu.kbffa.data.types;

import me.xemu.kbffa.Knockback;
import me.xemu.kbffa.data.Storage;
import me.xemu.kbffa.data.map.Map;
import me.xemu.kbffa.data.profile.Profile;
import me.xemu.kbffa.utils.Config;
import org.bukkit.Location;

import java.util.UUID;

public class YamlStorage implements Storage {
	private Knockback plugin = Knockback.getInstance();
	private Config data = plugin.getData();

	@Override
	public String name() {
		return "Yaml";
	}

	@Override
	public Profile getProfile(UUID uuid) {
		String path = "Profiles." + uuid;
		return new Profile(
				UUID.fromString(data.getString("Profiles." + uuid.toString())),
				data.getInt(path + ".coins"),
				data.getStringList(path + ".unlocked")
		);
	}

	@Override
	public void storeProfile(Profile profile) {
		data.set("Profiles." + profile.getUuid() + ".coins", profile.getCoins());
		data.set("Profiles." + profile.getUuid() + ".unlocked", profile.getUnlocked());
		data.save();
	}

	@Override
	public Map getMap(String name) {
		return new Map(
				name,
				(Location) data.get("Maps." + name + ".spawn"),
				data.getInt("Maps." + name + ".death"),
				data.getInt("Maps." + name + ".pvp")
		);
	}

	@Override
	public void storeMap(Map map) {
		data.set("Maps." + map.getName() + ".spawn", map.getMapSpawnLocation());
		data.set("Maps." + map.getName() + ".death", map.getDeathHeight());
		data.set("Maps." + map.getName() + ".pvp", map.getPvpHeight());
		data.save();
	}
}
