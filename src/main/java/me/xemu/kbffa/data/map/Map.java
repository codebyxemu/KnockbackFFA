package me.xemu.kbffa.data.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Map {

	private String name;
	private Location mapSpawnLocation;
	private int deathHeight; // Height or under = kill
	private int pvpHeight; // Over height = disable


}
