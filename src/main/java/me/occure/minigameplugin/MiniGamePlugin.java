package me.occure.minigameplugin;

//import me.occure.minigameplugin.Command.MinigameCommand;
//import me.occure.minigameplugin.GUI.GUIListener;
import me.occure.minigameplugin.ArcheryGame.ArheryGameListener;
import me.occure.minigameplugin.ArcheryGame.GameProgress;
import me.occure.minigameplugin.Command.MinigameCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiniGamePlugin extends JavaPlugin {
    private GameProgress gameProgress;

    @Override
    public void onEnable() {
        gameProgress = new GameProgress();

        getLogger().warning("MiniGame Plugin has been enabled!");
        Bukkit.getCommandMap().register("minigame", new MinigameCommand("minigame",gameProgress));
        Bukkit.getPluginManager().registerEvents(new ArheryGameListener(gameProgress),this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().warning("MiniGame Plugin has been disabled!");

    }
}
