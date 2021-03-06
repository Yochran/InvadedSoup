package me.yochran.invadedsoup.kits;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionKit implements Listener {

    private InvadedSoup plugin;

    public PotionKit() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void selectKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"))) {
            if (event.getCurrentItem() == null) {
                return;
            } else {
                if (event.getCurrentItem().getType() == Material.POTION) {
                    event.setCancelled(true);
                    player.getInventory().clear();
                    plugin.kit.remove(player.getUniqueId());
                    ItemStack sword = XMaterial.IRON_SWORD.parseItem();
                    ItemStack potion = new ItemStack(Material.POTION, 1, (short) 16421);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
                    player.getInventory().setItem(0, sword);
                    for (int i = 1; i < 36; i++) {
                        player.getInventory().setItem(i, potion);
                    }
                    player.closeInventory();
                    player.getInventory().setHelmet(XMaterial.LEATHER_HELMET.parseItem());
                    player.getInventory().setChestplate(XMaterial.IRON_CHESTPLATE.parseItem());
                    player.getInventory().setLeggings(XMaterial.IRON_LEGGINGS.parseItem());
                    player.getInventory().setBoots(XMaterial.IRON_BOOTS.parseItem());

                    Utils.sendMessage(player, "&7You have recieved the &2Potion &7Kit.");
                    plugin.kit.put(player.getUniqueId(), "Potion");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000000, 0));
                    plugin.potion.add(player.getUniqueId());
                    plugin.urgal.remove(player.getUniqueId());
                    plugin.switcher.remove(player.getUniqueId());
                    plugin.stomper.remove(player.getUniqueId());
                    plugin.kangaroo.remove(player.getUniqueId());
                    plugin.ninja.remove(player.getUniqueId());
                    plugin.snowman.remove(player.getUniqueId());
                    plugin.thor.remove(player.getUniqueId());
                    plugin.viper.remove(player.getUniqueId());
                }
            }
        }
    }
}
