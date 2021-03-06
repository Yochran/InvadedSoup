package me.yochran.invadedsoup.kits;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class KangarooKit implements Listener {

    private InvadedSoup plugin;

    public KangarooKit() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void selectKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"))) {
            if (event.getCurrentItem()== null) {
                return;
            } else {
                if (event.getCurrentItem().getType() == XMaterial.FIREWORK_ROCKET.parseMaterial()) {
                    if (!player.hasPermission("soup.kits.kangaroo")) {
                        player.closeInventory();
                        Utils.sendMessage(player, "&cYou can't use that kit!");
                    } else {
                        event.setCancelled(true);
                        player.getInventory().clear();
                        plugin.kit.remove(player.getUniqueId());
                        ItemStack soup = XMaterial.MUSHROOM_STEW.parseItem();
                        ItemStack sword = XMaterial.IRON_SWORD.parseItem();
                        ItemStack rocket = XMaterial.FIREWORK_ROCKET.parseItem();
                        ItemStack boots = XMaterial.LEATHER_BOOTS.parseItem();
                        boots.addEnchantment(Enchantment.DURABILITY, 2);
                        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                        player.getInventory().setItem(0, sword);
                        for (int i = 1; i < 36; i++) {
                            player.getInventory().setItem(i, soup);
                        }
                        player.closeInventory();
                        player.getInventory().setHelmet(XMaterial.IRON_HELMET.parseItem());
                        player.getInventory().setChestplate(XMaterial.IRON_CHESTPLATE.parseItem());
                        player.getInventory().setLeggings(XMaterial.IRON_LEGGINGS.parseItem());
                        player.getInventory().setBoots(boots);
                        int slot = 8;
                        player.getInventory().getItem(slot).setType(XMaterial.AIR.parseMaterial());
                        player.getInventory().setItem(8, rocket);

                        Utils.sendMessage(player, "&7You have recieved the &dKangaroo &7Kit.");
                        plugin.kit.put(player.getUniqueId(), "Kangaroo");
                        plugin.potion.remove(player.getUniqueId());
                        plugin.urgal.remove(player.getUniqueId());
                        plugin.switcher.remove(player.getUniqueId());
                        plugin.stomper.remove(player.getUniqueId());
                        plugin.kangaroo.add(player.getUniqueId());
                        plugin.ninja.remove(player.getUniqueId());
                        plugin.snowman.remove(player.getUniqueId());
                        plugin.thor.remove(player.getUniqueId());
                        plugin.viper.remove(player.getUniqueId());
                    }
                }
            }
        }
    }
}
