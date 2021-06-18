package com.peter.citizen.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import com.peter.citizen.Citizen;



// Upon loading a new chunk, scan all LivingEntities, looking for Citizen candidates which are not already Citizens.
// If we find candidates who are not already Citizens, make them into a Citizen.
//
public class ListenerOnChunkLoad implements Listener
{

	@EventHandler(priority = EventPriority.HIGH)
	public void onChunkLoad(ChunkLoadEvent e)
	{

		for( Entity entity : e.getChunk().getEntities() )
		{
			if( ! (entity instanceof LivingEntity) )
				return;


			if( ! Citizen.isEligible( (LivingEntity) entity ) ) {
				return;
			}

			if( Citizen.isCitizen( (LivingEntity) entity) ) {
				System.out.println("onChunkLoad: LivingEntity already citizen");
				return;
			}

			System.out.println("   Creating new Citizen.");
			Citizen c = new Citizen( (LivingEntity) entity );
			c.write();
			System.out.println("   Done.");
		}
	}

}
