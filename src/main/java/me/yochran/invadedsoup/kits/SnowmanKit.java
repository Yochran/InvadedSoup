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
import org.bukkit.inventory.meta.ItemMeta;

public class SnowmanKit implements Listener {

    private InvadedSoup plugin;

    public SnowmanKit() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void selectKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"))) {
            if (event.getCurrentItem()== null) {
                return;
            } else {
                if (event.getCurrentItem().getType() == XMaterial.JACK_O_LANTERN.parseMaterial()) {
                    if (!player.hasPermission("soup.kits.snowman")) {
                        player.closeInventory();
                        Utils.sendMessage(player, "&cYou can't use that kit!");
                    } else {
                        event.setCancelled(true);
                        player.getInventory().clear();
                        plugin.kit.remove(player.getUniqueId());
                        ItemStack soup = XMaterial.MUSHROOM_STEW.parseItem();
                        ItemStack sword = XMaterial.DIAMOND_SWORD.parseItem();
                        ItemStack snowball = XMaterial.SNOWBALL.parseItem();
                        ItemMeta snowballName = snowball.getItemMeta();
                        snowballName.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7&lSacred Snow"));
                        snowball.setItemMeta(snowballName);
                        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                        player.getInventory().setItem(0, sword);
                        for (int i = 1; i < 36; i++) {
                            player.getInventory().setItem(i, soup);
                        }
                        player.closeInventory();
                        int slot = 1;
                        player.getInventory().setItem(slot, snowball);
                        player.getInventory().setHelmet(XMaterial.JACK_O_LANTERN.parseItem());
                        player.getInventory().setChestplate(XMaterial.IRON_CHESTPLATE.parseItem());
                        player.getInventory().setLeggings(XMaterial.IRON_LEGGINGS.parseItem());
                        player.getInventory().setBoots(XMaterial.IRON_BOOTS.parseItem());

                        Utils.sendMessage(player, "&7You have recieved the Snowman Kit.");
                        plugin.kit.put(player.getUniqueId(), "Snowman");
                        plugin.potion.remove(player.getUniqueId());
                        plugin.urgal.remove(player.getUniqueId());
                        plugin.switcher.remove(player.getUniqueId());
                        plugin.stomper.remove(player.getUniqueId());
                        plugin.kangaroo.remove(player.getUniqueId());
                        plugin.ninja.remove(player.getUniqueId());
                        plugin.snowman.add(player.getUniqueId());
                        plugin.thor.remove(player.getUniqueId());
                        plugin.viper.remove(player.getUniqueId());
                    }
                }
            }
        }
    }
}
