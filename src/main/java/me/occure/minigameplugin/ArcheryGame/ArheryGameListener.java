package me.occure.minigameplugin.ArcheryGame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class ArheryGameListener implements Listener {
    private final GameProgress gameProgress;

    public ArheryGameListener(GameProgress gameProgress) {
        this.gameProgress = gameProgress;
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();

            if (arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                ItemStack type = player.getInventory().getItemInOffHand();

                if (type != null && type.getType() == Material.ARROW && type.hasItemMeta()) {
                    String arrowType = type.getItemMeta().getDisplayName();

                    if (event.getHitBlock() != null && event.getHitBlock().getType() == Material.TARGET) {
                        Location hitBlock = event.getHitBlock().getLocation();
                        Location playerLocation = player.getLocation().clone();

                        double distance = playerLocation.distance(hitBlock);

                        if (distance >= 20) {
                            switch (arrowType) {
                                case "일반 화살":
                                    remove(hitBlock);
                                    break;

                                case "세로 화살":
                                    remove(hitBlock);
                                    remove(hitBlock.clone().add(1, 0, 0));
                                    remove(hitBlock.clone().add(-1, 0, 0));
                                    break;

                                case "가로 화살":
                                    remove(hitBlock);
                                    remove(hitBlock.clone().add(0, 1, 0));
                                    remove(hitBlock.clone().add(0, -1, 0));
                                    break;

                                default:
                                    break;
                            }
                            gameProgress.ArrowScore();
                            arrow.remove();

                            if (gameProgress.getArrowScore() <= 0 || gameProgress.score()>=49) {
                                gameProgress.stopGame();
                            }
                        } else {
                            player.sendMessage("타겟과의 거리가 20칸 이상이어야 합니다");
                        }
                    }
                }
            }
        }
    }
    private void remove(Location location) {
        location.getBlock().setType(Material.AIR);
        gameProgress.updateTarget(location,Material.AIR);
    }
}
