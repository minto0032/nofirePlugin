package com.github.minto0032.nofireplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class NofirePlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    public static boolean enabled = true;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();
        Material blockType = event.getBlock().getType();

        if (enabled) {
            if (blockType == Material.FIRE) {
                // 2ブロック以内に他のプレイヤーがいたら火を消す
                for (Player otherPlayer : location.getWorld().getPlayers()) {
                    if (otherPlayer.getLocation().distance(location) <= 2.0) {
                        if (!otherPlayer.equals(player)) {
                            event.setCancelled(true);
                            player.sendMessage("§4他のプレイヤーの近くに火をつけることはできません！");
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equals("on")){
            sender.sendMessage("オンにしました");
            enabled = true;
            return true;
        }
        if(args[0].equals("off")){
            sender.sendMessage("オフにしました");
            enabled = false;
            return true;
        }
        return true;
    }
}