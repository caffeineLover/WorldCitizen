package com.peter.citizen;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;

public class CitizenHandler
{
	private static CitizenHandler single_instance = null;
	
	
    private CitizenHandler()
    {
    }
  


    public static CitizenHandler getInstance()
    {
    	if (single_instance == null)
    		single_instance = new CitizenHandler();
  
        return single_instance;
    }
    
    
    
    
 
	public static boolean isCitizen(final LivingEntity entity)
	{
		File dataFolder = Bukkit.getPluginManager().getPlugin("WorldCitizen").getDataFolder();
		File census = new File(dataFolder, "census.yml");	
		YamlConfiguration censusFile = YamlConfiguration.loadConfiguration(census);

		String entityLookup = "villager." + entity.getUniqueId().toString() + ".name";

		if ( censusFile.getString( entityLookup ) != null)
			return true; 

		return false;
	}
	
	
	
	public static boolean isEligible( final LivingEntity le )
	{
		List<String> candidateTypes = Bukkit.getPluginManager().getPlugin("WorldCitizen").getConfig().getStringList("CitizenTypes");

		// If the LE is not a villager, witch, evoker, etc., they're not eligible to be a citizen.
		if( candidateTypes.contains(le.getType().toString()) )
			return true;
	
		return false;
	}
	

	
	// Write the citizen to the census file.
	public static void write(Citizen citizen)
	{
		File dataFolder = Bukkit.getPluginManager().getPlugin("WorldCitizen").getDataFolder();
		File census = new File(dataFolder, "census.yml");	
		YamlConfiguration censusFile = YamlConfiguration.loadConfiguration(census);

		censusFile.set("citizens." + citizen.uuid + ".name", citizen.name);
		censusFile.set("citizens." + citizen.uuid + ".gender", citizen.gender.toString());
		censusFile.set("citizens." + citizen.uuid + ".title", "the " + citizen.profession);
		try {
			censusFile.save(census);
		} catch ( IOException e ) {
			e.printStackTrace();
		}

		System.out.println("Citizen::write() is done.");
	}
	
	

	public static void remove( LivingEntity entity)
	{
		File dataFolder = Bukkit.getPluginManager().getPlugin("WorldCitizen").getDataFolder();
		File census = new File(dataFolder, "census.yml");
		YamlConfiguration censusFile = YamlConfiguration.loadConfiguration(census);
		censusFile.set("citizens." + entity.getUniqueId(), null);

		try {
			censusFile.save(census);
		} catch ( IOException e ) {
			e.printStackTrace();
		}

		System.out.println("Citizen::remove() is done.");
	}
	


}
