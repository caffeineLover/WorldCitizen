package com.peter.citizen;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.peter.citizen.listeners.EntityDeathListener;
import com.peter.citizen.listeners.OnChunkLoadListener;
import com.peter.citizen.listeners.OnLivingEntitySpawnListener;
import com.peter.citizen.listeners.OnPlayerInteractListener;


public class CitizenPlugin extends JavaPlugin
{
    // The file "census" keeps track of all the citizens in the game.
    File dataFolder = getDataFolder();
    File census = new File(dataFolder, "census.yml");
    YamlConfiguration censusFile = YamlConfiguration.loadConfiguration(this.census);


    @Override
    public void onEnable()
    {
        System.out.println("Plugin enabled.");

        getConfig().options().copyDefaults(true);

        // Always do this, this makes sure your config actually loads
        saveDefaultConfig();

        new EntityDeathListener(this);
        new OnChunkLoadListener(this);
        new OnLivingEntitySpawnListener(this);
        new OnPlayerInteractListener(this);
    }


}
