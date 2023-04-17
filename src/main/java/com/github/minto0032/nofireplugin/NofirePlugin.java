package com.github.minto0032.nofireplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;

public class NofirePlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();
        Material blockType = event.getBlock().getType();

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