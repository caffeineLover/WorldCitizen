package com.peter.citizen.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import com.peter.citizen.Citizen;
import com.peter.citizen.CitizenHandler;
import com.peter.citizen.CitizenPlugin;



// Upon loading a new chunk, scan all LivingEntities, looking for Citizen candidates which are not already Citizens.
// If we find candidates who are not already Citizens, make them into a Citizen.
//
public class OnChunkLoadListener implements Listener
{
	@SuppressWarnings("unused")
	private CitizenPlugin plugin;
	
	

	public OnChunkLoadListener(CitizenPlugin plugin)
	{
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}	

	
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onChunkLoad(ChunkLoadEvent e)
	{

		for( Entity entity : e.getChunk().getEntities() )
		{
			if( ! (entity instanceof LivingEntity) )
				return;


			if( ! CitizenHandler.isEligible( (LivingEntity) entity ) ) {
				return;
			}

			if( CitizenHandler.isCitizen( (LivingEntity) entity) ) {
				System.out.println("onChunkLoad: LivingEntity already citizen");
				return;
			}

			System.out.println("   Creating new Citizen.");
			Citizen c = new Citizen( (LivingEntity) entity );
			CitizenHandler.write( c );
		}
	}

	
	
}