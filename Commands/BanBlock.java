package adam.banblocks.Commands;

import adam.banblocks.Inventorys.BlockInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanBlock implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;

        if(sender instanceof Player) {
            player = (Player) sender;
        } else {
            return true;
        }

        // open inventory
        player.openInventory(BlockInventory.openBlockInventory());

        return true;
    }
}
