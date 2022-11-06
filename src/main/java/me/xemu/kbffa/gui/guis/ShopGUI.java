package me.xemu.kbffa.gui.guis;

import me.xemu.kbffa.Knockback;
import me.xemu.kbffa.data.profile.Profile;
import me.xemu.kbffa.gui.GUI;
import me.xemu.kbffa.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ShopGUI extends GUI<Knockback> {
	protected Knockback plugin = Knockback.getInstance();

	public ShopGUI(Knockback plugin) {
		super(plugin);
	}

	@Override
	public int getSize() {
		return 54;
	}

	@Override
	public String getTitle() {
		return "KnockbackFFA - Admin Home";
	}

	@Override
	public boolean canClose(Player player) {
		return true;
	}

	public void handle(Player player) {
		ItemBuilder currentMoney = new ItemBuilder(Material.GOLD_INGOT);
		currentMoney.setName("&6&lCurrent Coins");

		set(0, currentMoney.toItemStack(), new ButtonCompletion() {
			@Override
			public ButtonAction onClick(Player whoClicked, ItemStack clickedItem) {
				return ButtonAction.CANCEL;
			}
		});

		for (String key : plugin.getShop().getConfigurationSection("items").getKeys(true)) {
			Material material = Material.valueOf(plugin.getShop().getConfig().getString("items." + key + ".material"));
			String displayName = plugin.getShop().getConfig().getString("items." + key + ".displayName");
			int cost = plugin.getShop().getInt("items." + key + ".cost");

			ItemBuilder item = new ItemBuilder(material)
					.setName(displayName)
					.setLore("&7Item Cost: &6" + cost);

			Profile profile = plugin.getStorage().getProfile(player.getUniqueId());
			boolean purchased = profile.getUnlocked().contains(key);

			if (purchased) {
				item.addLoreLine("&6You already have this product. Click to select.");
			} else if (!purchased && profile.getCoins() >= profile.getCoins()) {
				item.addLoreLine("&aClick to purchase.");
			} else if (!purchased && profile.getCoins() < profile.getCoins()) {
				int missing = cost - profile.getCoins();
				item.addLoreLine("&cNot enough coins! You are missing " + missing);
			}

			set(getInventory().firstEmpty(), item.toItemStack(), (whoClicked, clickedItem) -> {
				if (!purchased && profile.getCoins() >= profile.getCoins()) {
					profile.getUnlocked().add(key);
					profile.setCoins(profile.getCoins() - cost);
					plugin.getStorage().storeProfile(profile);
					return ButtonAction.CLOSE_GUI;
				} else if (!purchased && profile.getCoins() <= profile.getCoins()) {
					whoClicked.sendMessage("§cNot enough money.");
					return ButtonAction.CLOSE_GUI;
				}
				return ButtonAction.CANCEL;
			});
		}
	}

}
