package adam.banblocks;

import adam.banblocks.Commands.BanBlock;
import adam.banblocks.Inventorys.BlockInventory;
import adam.banblocks.Listeners.OnBlockPlaceEvent;
import adam.banblocks.Listeners.OnInventoryClickEvent;
import adam.banblocks.Listeners.OnInventoryCloseEvent;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/*
make the block have a glowing effect if its banned only visible in the inventory no idea how
 */

public final class BanBlocks extends JavaPlugin {

    public static ArrayList<Material> allBlocks = new ArrayList<>();
    public static ArrayList<Material> bannedBlocks = new ArrayList<>();
    public static int inventoryPage = 1;
    public static int maxInventoryPage = 18;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Ban Blocks Plugin Loaded");

        getCommand("banblock").setExecutor(new BanBlock());
        BlockInventory.createAllInventorys();
        getServer().getPluginManager().registerEvents(new OnInventoryClickEvent(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryCloseEvent(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlaceEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
