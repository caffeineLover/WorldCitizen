package com.peter.citizen.listeners;

import com.peter.citizen.CitizenPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.peter.citizen.Citizen;



// When an entity dies, check to see if it's a citizen.  If so, log the event and remove from the census.
//
public class EntityDeathListener implements Listener
{
	private CitizenPlugin plugin;

	
	
	public EntityDeathListener(CitizenPlugin plugin)
	{
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	
	
	@EventHandler(priority = EventPriority.HIGH)
    public void onEntityDeathEvent(EntityDeathEvent e)
	{
        System.out.println("onEntityDeathEvent event");

        Citizen.remove(e.getEntity());
    }

	
	
}
