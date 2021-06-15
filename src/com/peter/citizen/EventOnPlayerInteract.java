package com.peter.citizen;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;



// When a player interacts with something, check to see if it's a Citizen candidate that isn't already a Citizen.
// If we find candidates who are not already Citizens, make them into a Citizen.
//
public class EventOnPlayerInteract implements Listener
{
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEntityEvent e)
	{
		LivingEntity entity = (LivingEntity) e.getRightClicked();

		if( ! Citizen.isEligible(entity) )
			return;

		if( Citizen.isCitizen(entity) )
			return;

		Citizen c = new Citizen( entity );
		c.write();
	}
	
}
