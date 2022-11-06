package me.xemu.kbffa.data.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Profile {

	private UUID uuid;
	private int coins;
	private List<String> unlocked;

}
