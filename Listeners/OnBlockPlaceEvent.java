package adam.banblocks.Listeners;

import adam.banblocks.BanBlocks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlaceEvent implements Listener {
    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        // get the material of block placing
        Material blockPlacing = event.getBlock().getType();

        // check if it exist in banned array
        if(!BanBlocks.bannedBlocks.contains(blockPlacing)) {
            return;
        }

        // its banned so sucks
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "You cant place that block as it is currently banned");
    }
}
