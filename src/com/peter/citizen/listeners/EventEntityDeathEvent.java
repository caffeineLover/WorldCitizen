package com.peter.citizen.event;

import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.peter.citizen.Citizen;



// When an entity dies, check to see if it's a citizen.  If so, log the event and remove from the census.
//
public class EventEntityDeathEvent implements Listener
{
	
	@EventHandler(priority = EventPriority.HIGH)
	public void EntityDeathEvent(LivingEntity lentity, List<ItemStack> drops)
	{
		Citizen.remove( lentity );
	}
		
}
