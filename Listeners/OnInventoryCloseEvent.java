package adam.banblocks.Listeners;

import adam.banblocks.BanBlocks;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class OnInventoryCloseEvent implements Listener {
    @EventHandler
    public void inventoryCloseEvent(InventoryCloseEvent event) {
        System.out.println(event.getView().getTitle());
        // check if its the custom inventory we made
        if(!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Block Inventory (" + BanBlocks.inventoryPage + "/" + BanBlocks.maxInventoryPage + ")")) {
            return;
        }

        // if inventory reset counter to 1
        BanBlocks.inventoryPage = 1;
    }
}
