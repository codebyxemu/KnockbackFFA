package me.xemu.kbffa.data;

import me.xemu.kbffa.data.map.Map;
import me.xemu.kbffa.data.profile.Profile;

import javax.xml.stream.Location;
import java.util.UUID;

public interface Storage {

	String name();

	Profile getProfile(UUID uuid);

	void storeProfile(Profile profile);

	me.xemu.kbffa.data.map.Map getMap(String name);

	void storeMap(Map map);

}
