package me.occure.minigameplugin.ArcheryGame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


public class TargetCreate {

    private static final Map<Location, Material> blocks = new HashMap<>();
    private static final Map<Location, Material> target = new HashMap<>();

    public static void Target(Player player) {
        //플레이어 위치
        Location playerlocation = player.getLocation().clone();
        playerlocation.add(0,3,20);
        //과녁 높이 조절

        //7*7과녁 생성
        for(int x = -3; x <= 3; x++){
            for(int y = -3; y <= 3; y++) {

                Location blockLocation = playerlocation.clone().add(x, y, 1);
                Block block = blockLocation.getBlock();

                blocks.put(blockLocation, block.getType());

                block.setType(Material.TARGET);

                target.put(blockLocation, Material.TARGET);
            }
        }
        for (int z = 0; z <= 19; z++) {
            for (int x = -3; x <= 3; x++) {
                for (int y = -3; y <= 3; y++) {
                    Location location = playerlocation.clone().add(x, y, z-19);
                    Block block = location.getBlock();

                    blocks.put(location, block.getType());

                    block.setType(Material.AIR);
                }
            }
        }
    }

    public static void clearTarget(Player player){

        for(Map.Entry<Location, Material> entry : blocks.entrySet()){
            entry.getKey().getBlock().setType(entry.getValue());
        }

        blocks.clear();
        target.clear();
    }

    public static Map<Location, Material> getTargetBlocks(){
        return target;
    }

    public static void updateTarget(Location location, Material material){
        if (target.containsKey(location)){
            target.put(location,material);
        }
    }

}
