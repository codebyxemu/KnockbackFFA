package me.xemu.kbffa.data.types;

import me.xemu.kbffa.Knockback;
import me.xemu.kbffa.data.DataType;
import me.xemu.kbffa.data.profile.Profile;

import java.util.UUID;

public class FileData implements DataType {
	@Override
	public String name() {
		return "File";
	}

	@Override
	public Profile getProfile(UUID uuid) {
		return new Profile(
				UUID.fromString(Knockback.getInstance().getData().getString(uuid.toString())),
				Knockback.getInstance().getData().getInt(uuid.toString() + ".coins"),
				Knockback.getInstance().getData().getStringList(uuid.toString() + ".unlocked")
		);
	}

	@Override
	public void storeProfile(Profile profile) {
		Knockback.getInstance().getData().set(profile.getUuid() + ".coins", profile.getCoins());
		Knockback.getInstance().getData().set(profile.getUuid() + ".unlocked", profile.getUnlocked());
		Knockback.getInstance().getData().save();
	}
}
