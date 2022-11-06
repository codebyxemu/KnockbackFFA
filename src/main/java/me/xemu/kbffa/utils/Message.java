package me.xemu.kbffa.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Getter
@Setter
@AllArgsConstructor
public class Message {

	private String message;

	public Message placeholder(String placeholder, String replace) {
		this.message = message.replaceAll(placeholder, replace);
		return this;
	}

	public Message colorize() {
		this.message = ChatColor.translateAlternateColorCodes('&', message);
		return this;
	}

	public void send(Player player) {
		player.sendMessage(getMessage());
	}

	public void console() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6Knockback&7] &r") + getMessage());
	}

}
