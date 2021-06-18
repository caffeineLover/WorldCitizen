package com.peter.citizen;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;



public class Citizen
{
	UUID       uuid;
	EnumGender gender;
	String     name;
	String     profession;

	File config, census;
	YamlConfiguration configFile, censusFile;
	
	
	public Citizen( Entity entity )
	{
		if( entity instanceof LivingEntity )
			CitizenBackEnd( (LivingEntity) entity );
	}
	
	
	
	public Citizen( LivingEntity lentity )
	{
		CitizenBackEnd( lentity );
	}

	

	private void CitizenBackEnd( LivingEntity lentity )
	{
		System.out.println("CitizenBackEnd Start");
		File pluginFolder = Bukkit.getPluginManager().getPlugin("WorldCitizen").getDataFolder();
		config = new File( pluginFolder, "config.yml");
		census = new File( pluginFolder, "census.yml");

		System.out.println("Check 1");
		configFile = YamlConfiguration.loadConfiguration(config);	
		censusFile = YamlConfiguration.loadConfiguration(census);	

		System.out.println("Check 2");
		this.uuid   = lentity.getUniqueId();

		// The gender determines the name type, so we need to set gender first.
		this.gender = (Math.random() <= 0.5) ? EnumGender.male : EnumGender.female;
		this.name   = getName( this.gender );

		// A witch is not a Villager.
		if( lentity.getType() == EntityType.WITCH )
			this.profession = "Witch";
		else
			this.profession = ((Villager) lentity).getProfession().toString();

		System.out.println("Check 3");
	}
	
	
	
	
	
	public static boolean isEligible( final LivingEntity le )
	{
		List<String> candidateTypes = Bukkit.getPluginManager().getPlugin("WorldCitizen").getConfig().getStringList("CitizenTypes");

		// If the LE is not a villager, witch, evoker, etc., they're not eligible to be a citizen.
		if( candidateTypes.contains(le.getType().toString()) )
			return true;
	
		return false;
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
	


	// Write the citizen to the census file.
	public void write()
	{
		censusFile.set("citizens." + uuid + ".name", name);
		censusFile.set("citizens." + uuid + ".name", gender);
		censusFile.set("citizens." + uuid + ".title", "the " + profession);
		try {
			censusFile.save(census);
		} catch ( IOException e ) {
			e.printStackTrace();
		}

		System.out.println("Citizen::write() is done.");
	}
	
	

	public static void remove( LivingEntity entity)
	{
		// STUB: Implement me.
	}
	


	public String getName(EnumGender gender)
	{
	    List<String> nList = configFile.getStringList("names." + this.gender.toString());
	    int index = (new Random()).nextInt(nList.size());
	    return nList.get(index);
	}
	
	
	
	
	public enum EnumGender
	{
		male,
		female
	}
	
}
