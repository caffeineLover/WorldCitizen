package com.peter.citizen;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.peter.citizen.listeners.EntityDeathListener;
import com.peter.citizen.listeners.OnChunkLoadListener;
import com.peter.citizen.listeners.OnLivingEntitySpawnListener;
import com.peter.citizen.listeners.OnPlayerInteractListener;


public class CitizenPlugin extends JavaPlugin { // TODO: Renamed to CitizenPlugin as it's normally pluginname + Plugin

    // The file "census" keeps track of all the citizens in the game.
    File dataFolder = getDataFolder();
    File census = new File(dataFolder, "census.yml");
    YamlConfiguration censusFile = YamlConfiguration.loadConfiguration(this.census);


    @Override
    public void onEnable() {
        System.out.println("Plugin enabled.");

        getConfig().options().copyDefaults(true);
        saveDefaultConfig(); // TODO: Always do this, this makes sure your config actually loads

        new EntityDeathListener(this); // TODO: This is the edited listener class, which will register itself.

        getServer().getPluginManager().registerEvents(new OnChunkLoadListener(), this);
        getServer().getPluginManager().registerEvents(new OnLivingEntitySpawnListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteractListener(), this);

    }


}
