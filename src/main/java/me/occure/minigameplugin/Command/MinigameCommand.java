package me.occure.minigameplugin.Command;

import me.occure.minigameplugin.ArcheryGame.GameProgress;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MinigameCommand extends BukkitCommand {
    private final GameProgress gameProgress;

    public MinigameCommand(String name, GameProgress gameProgress) {
        super(name);
        this.gameProgress = gameProgress;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if(args.length == 0){
                return false;
            }
            switch (args[0]) {
                case "start" -> {
                    gameProgress.startGame(player);
                    player.sendMessage("게임을 시작했습니다!");

                    return true;
                }

                case "stop" ->{
                    gameProgress.stopGame();
                    player.sendMessage("게임을 종료했습니다!");

                    return true;
                }
            }
        }
        return false;
    }
}

