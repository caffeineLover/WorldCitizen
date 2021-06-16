package com.peter.citizen;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.peter.citizen.listeners.ListenerEntityDeathEvent;
import com.peter.citizen.listeners.ListenerOnChunkLoad;
import com.peter.citizen.listeners.ListenerOnLivingEntitySpawn;
import com.peter.citizen.listeners.ListenerOnPlayerInteract;



public class Main extends JavaPlugin implements Listener
{

	// The file "census" keeps track of all the citizens in the game.
	File dataFolder = getDataFolder();
	File census = new File(dataFolder, "census.yml");
	YamlConfiguration censusFile = YamlConfiguration.loadConfiguration(this.census);



	@Override public void onEnable()
	{
		System.out.println("Plugin enabled.");
		getServer().getPluginManager().registerEvents(new ListenerEntityDeathEvent(), this);
		getServer().getPluginManager().registerEvents(new ListenerOnChunkLoad(), this);
		getServer().getPluginManager().registerEvents(new ListenerOnLivingEntitySpawn(), this);
		getServer().getPluginManager().registerEvents(new ListenerOnPlayerInteract(), this);
		 
	}
	
	
	
}
