package com.peter.citizen.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import com.peter.citizen.Citizen;
import com.peter.citizen.CitizenPlugin;



// When a creature is spawned, check to see if it's a Citizen candidate.  If so, make it a Citizen.
// No need to check if it's already a Citizen since it's newly spawned... we know it's not.
//
public class OnLivingEntitySpawnListener implements Listener
{
	private CitizenPlugin plugin;
	
	

	public OnLivingEntitySpawnListener(CitizenPlugin plugin)
	{
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onLivingEntitySpawn(CreatureSpawnEvent spawnEvent)
	{
		LivingEntity entity = (LivingEntity) spawnEvent.getEntity();

		if( Citizen.isEligible(entity) )
		{
			System.out.println("onLivingEntitySpawn Eligible: " + entity.getType() + " Creating...");

			Citizen c = new Citizen( entity );
			c.write();
			System.out.println("Wrote citizen.");
		}
	}

}
