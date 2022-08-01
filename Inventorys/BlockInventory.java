package adam.banblocks.Inventorys;

import adam.banblocks.BanBlocks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class BlockInventory {

    public static ArrayList<Inventory> allInventorys = new ArrayList<>();
    static Inventory inventoryYes;
    public static Inventory openBlockInventory() {
        return allInventorys.get(0);
    }

    public static void createAllInventorys() {
        final int[] counter = {0};
        final int[] inventoryCurrent = {1};
        inventoryYes = Bukkit.createInventory(null, 54, ChatColor.AQUA + "Block Inventory (" + inventoryCurrent[0] + "/" + BanBlocks.maxInventoryPage + ")");

        // get every block on mc somehow do soon
        Material[] listOfBlocks = Material.values();

        // filter the blocks then push each block into an array
        Arrays.stream(listOfBlocks).filter(Material::isBlock).forEach(material -> {
            if(!material.equals(Material.AIR)) {
                BanBlocks.allBlocks.add(material);
            }
        });

        BanBlocks.allBlocks.forEach(material -> {
            // add the items to the slots up to slot 45
            if(inventoryYes.getItem(counter[0]) == null) {
                inventoryYes.setItem(counter[0], new ItemStack(material));
                counter[0] = counter[0] + 1;
            }

            int newPage;
            int lastPage;

            // if counter is 45 the one where we add the current inventory to the array then create a new one
            if(counter[0] == 45) {
                // add the next page button
                ItemStack nextPageButton = new ItemStack(Material.OAK_SIGN);
                ItemMeta nextPageButtonMeta = nextPageButton.getItemMeta();

                // page button stuff
                if(inventoryCurrent[0] == 1) {
                    lastPage = 1;
                } else {
                    lastPage = inventoryCurrent[0] - 1;
                }

                if(inventoryCurrent[0] == 18) {
                    newPage = 18;
                } else {
                    newPage = inventoryCurrent[0] + 1;
                }

                ItemStack goBackPageButton = new ItemStack(Material.OAK_SIGN);
                ItemMeta goBackPageButtonMeta = goBackPageButton.getItemMeta();

                nextPageButtonMeta.setDisplayName(ChatColor.AQUA + "Click to go to page " + newPage);
                nextPageButton.setItemMeta(nextPageButtonMeta);

                goBackPageButtonMeta.setDisplayName(ChatColor.AQUA + "Click to go back to page " + lastPage);
                goBackPageButton.setItemMeta(goBackPageButtonMeta);

                inventoryCurrent[0]++;
                // add it to array
                allInventorys.add(inventoryYes);
                // set the next page button on the inventory
                inventoryYes.setItem(50, nextPageButton);
                inventoryYes.setItem(48, goBackPageButton);
                // create a new inventory
                inventoryYes = Bukkit.createInventory(null, 54, ChatColor.AQUA + "Block Inventory (" + inventoryCurrent[0] + "/" + BanBlocks.maxInventoryPage + ")");
                counter[0] = 0;
            }
        });
    }
}
