package me.xemu.kbffa.data;

import me.xemu.kbffa.data.profile.Profile;

import java.util.UUID;

public interface DataType {

	String name();

	Profile getProfile(UUID uuid);

	void storeProfile(Profile profile);

}
