package com.peter.citizen.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import com.peter.citizen.Citizen;



// When a creature is spawned, check to see if it's a Citizen candidate.  If so, make it a Citizen.
// No need to check if it's already a Citizen since it's newly spawned... we know it's not.
//
public class EventOnLivingEntitySpawn implements Listener
{
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onLivingEntitySpawn(CreatureSpawnEvent spawnEvent)
	{
		LivingEntity entity = (LivingEntity) spawnEvent.getEntity();

		if( Citizen.isEligible(entity) )
		{
			Citizen c = new Citizen( entity );
			c.write();
		}
	}

}
