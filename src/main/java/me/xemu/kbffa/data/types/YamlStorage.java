package me.xemu.kbffa.data.types;

import me.xemu.kbffa.Knockback;
import me.xemu.kbffa.data.Storage;
import me.xemu.kbffa.data.profile.Profile;
import me.xemu.kbffa.utils.Config;

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
		return new Profile(
				UUID.fromString(data.getString(uuid.toString())),
				data.getInt(uuid + ".coins"),
				data.getStringList(uuid + ".unlocked")
		);
	}

	@Override
	public void storeProfile(Profile profile) {
		data.set(profile.getUuid() + ".coins", profile.getCoins());
		data.set(profile.getUuid() + ".unlocked", profile.getUnlocked());
		data.save();
	}
}
