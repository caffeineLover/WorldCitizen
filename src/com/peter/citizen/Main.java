package com.peter.citizen;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin implements Listener
{

	// The file "census" keeps track of all the citizens in the game.
	File dataFolder = getDataFolder();
	File census = new File(dataFolder, "census.yml");
	YamlConfiguration censusFile = YamlConfiguration.loadConfiguration(this.census);



	@Override public void onEnable()
	{
		System.out.println("Plugin enabled.");
		getServer().getPluginManager().registerEvents(new EventEntityDeathEvent(), this);
		getServer().getPluginManager().registerEvents(new EventOnChunkLoad(), this);
		getServer().getPluginManager().registerEvents(new EventOnLivingEntitySpawn(), this);
		getServer().getPluginManager().registerEvents(new EventOnPlayerInteract(), this);
		 
	}
	
	
	
}
