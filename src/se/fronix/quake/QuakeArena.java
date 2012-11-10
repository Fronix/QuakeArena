package se.fronix.quake;

import mc.alk.arena.objects.arenas.Arena;
import mc.alk.arena.objects.events.MatchEventHandler;
import mc.alk.arena.serializers.Persist;
import mc.alk.arena.util.Log;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class QuakeArena extends Arena{
	static int damage = 3;
	
	/**
	 * This is how you create customized events.  You specify a method as a @MatchEventHandler
	 * and give it at least one bukkit event as the first argument.  In this case its EntityDamageByEntityEvent
	 * These events will ONLY be called when a match is ongoing
	 * If the event returns a player (in this case it does) then the event only gets called when
	 * 1) match is ongoing
	 * 2) player is still alive in the match
	 *
	 * @param event: Which bukkit event are we listening to
	 */	
	@MatchEventHandler(suppressCastWarnings=true)
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (event.isCancelled())
			return;
		if (event.getDamager().getType() != EntityType.FIREBALL)
			return;
		event.setDamage(damage);
	}
	
	@MatchEventHandler(suppressCastWarnings=true)
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
	
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_AIR)){
			if(player.getItemInHand().getType().equals(Material.REDSTONE_TORCH_ON)){
				final Vector direction = player.getPlayer().getEyeLocation().getDirection().multiply(2);
				final Fireball fireball = player.getPlayer().getWorld().spawn(player.getPlayer().getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()), SmallFireball.class);
				fireball.setShooter(player.getPlayer());
			}
		}
		
	}
	
}