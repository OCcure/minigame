package me.occure.minigameplugin.ArcheryGame;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class GameProgress {
    private Player player;
    private long startTime;
    private  int arrowScore;

    public void startGame(Player player){
        this.player = player;
        this.startTime = System.currentTimeMillis();
        this.arrowScore = 50;


        ItemStack[] items = {
                ItemArrow.Type1Arrow(30),
                ItemArrow.Type2Arrow(10),
                ItemArrow.Type3Arrow(10),
                new ItemStack(Material.BOW,1)
        };
        player.getInventory().addItem(items);
        TargetCreate.Target(player);
    }

    public void stopGame(){
        long playTime = (System.currentTimeMillis() - startTime)/ 1000;

        player.getInventory().remove(Material.BOW);
        player.getInventory().remove(Material.ARROW);
        removeOffHand(player);

        int score = score();

        player.sendMessage("경과 시간 : " + playTime + "초");
        player.sendMessage("남은 화살 수 :" + arrowScore);
        player.sendMessage("파괴된 타겟 블록 수 : " + score);
        player.sendMessage("남은 타겟 블록 수 : " + (49 - score));

        TargetCreate.clearTarget(player);
    }

    public void ArrowScore(){
        arrowScore--;
    }

    public int getArrowScore(){
        return arrowScore;
    }
    public int score() {

        int score =0;

        Map<Location, Material> target = TargetCreate.getTargetBlocks();
        for(Map.Entry<Location , Material> entry : target.entrySet()){
            Location location = entry.getKey();
            Material material = location.getBlock().getType();
            if (material == Material.AIR){
                score++;
            }
        }
        return score;
    }
    public void updateTarget(Location location, Material material) {
        TargetCreate.updateTarget(location, material);
    }
    private void removeOffHand(Player player){
        ItemStack offHand = player.getInventory().getItemInOffHand();
        if(offHand != null && offHand.getType() == Material.ARROW){
            player.getInventory().setItemInOffHand(null);
        }
    }
}
