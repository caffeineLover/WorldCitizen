package com.peter.citizen.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.peter.citizen.Citizen;
import com.peter.citizen.CitizenHandler;
import com.peter.citizen.CitizenPlugin;



// When a player interacts with something, check to see if it's a Citizen candidate that isn't already a Citizen.
// If we find candidates who are not already Citizens, make them into a Citizen.
//
public class OnPlayerInteractListener implements Listener
{
	@SuppressWarnings("unused")
	private CitizenPlugin plugin;
	
	

	public OnPlayerInteractListener(CitizenPlugin plugin)
	{
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEntityEvent e)
	{
		System.out.println("onLivingEntitySpawn event");

		LivingEntity entity = (LivingEntity) e.getRightClicked();

		if( ! CitizenHandler.isEligible(entity) )
			return;

		if( CitizenHandler.isCitizen(entity) )
			return;

		Citizen c = new Citizen( entity );
		CitizenHandler.write( c );
	}

	
	
}
