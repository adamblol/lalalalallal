package adam.banblocks.Listeners;

import adam.banblocks.BanBlocks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static adam.banblocks.Inventorys.BlockInventory.allInventorys;

public class OnInventoryClickEvent implements Listener {
    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Block Inventory (" + BanBlocks.inventoryPage + "/" + BanBlocks.maxInventoryPage + ")")) {
            System.out.println(BanBlocks.inventoryPage);
            System.out.println("not equal too");
            player.sendMessage(event.getView().getTitle());
            return;
        }

        event.setCancelled(true);

        // check if its the go foward page button
        if(event.getSlot() == 50) {
            // if onlast page do nothing
            if(BanBlocks.maxInventoryPage == BanBlocks.inventoryPage) {
                return;
            }
            // scrape the name of the item clicked
            BanBlocks.inventoryPage++;
            player.openInventory(allInventorys.get(BanBlocks.inventoryPage - 1));
            return;
        }

        // check if its the go back page
        if(event.getSlot() == 48) {
            // if on page 1 return cause cant go to -1
            if(BanBlocks.inventoryPage == 1) {
                return;
            }

            BanBlocks.inventoryPage--;
            player.openInventory(allInventorys.get(BanBlocks.inventoryPage - 1));
            return;
        }

        // now if its either of those then they obviously clicked on item or empty item
        if(event.getCurrentItem() == null) {
            return;
        }

        // get the item they clicked
        Material itemClicked = event.getCurrentItem().getType();

        // check if it already exist then remove it
        if(BanBlocks.bannedBlocks.contains(itemClicked)) {
            BanBlocks.bannedBlocks.remove(itemClicked);
            player.sendMessage(ChatColor.GREEN + "You have just unbanned the block " + itemClicked);
            return;
        }

        // add the material into the blocked items array
        BanBlocks.bannedBlocks.add(itemClicked);
        player.sendMessage(ChatColor.GREEN + "You have just banned the block " + itemClicked);
    }
}
