package se.fronix.quake;

import mc.alk.arena.objects.arenas.Arena;
import mc.alk.arena.objects.events.MatchEventHandler;
import mc.alk.arena.serializers.Persist;
import mc.alk.arena.util.Log;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class QuakeArena extends Arena{
	static int damage = 3;
	static int missileInt = 76;
	
	@Persist
	String worldName;
	
	World world;
	
	@Override
	public void onOpen(){
		world = Bukkit.getWorld(worldName);

		if (world == null){
			Log.err("[Quake] worldName was null in arena " + getName());
			getMatch().cancelMatch();
			return;
		}
	}
	
	@Override
	public void onFinish(){
		
	}
	
	@Override
	public void onStart(){

	}

	
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
	@MatchEventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (event.isCancelled())
			return;
		if (event.getDamager().getType() != EntityType.FIREBALL)
			return;
		event.setDamage(damage);
	}
	
	@MatchEventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR){
			if(event.getPlayer().getItemInHand().equals(Material.REDSTONE_LAMP_ON) || event.getPlayer().getItemInHand().equals(Material.REDSTONE_LAMP_OFF)){
		          Fireball fireball = (Fireball)player.launchProjectile(Fireball.class);
		          fireball.setIsIncendiary(true);
		          fireball.setShooter(player);
		          fireball.teleport(player.getLocation().add(0.0D, 1.0D, 0.0D));
		          player.chat(ChatColor.RED + "I'M A' FIRIN' MAH " + ChatColor.AQUA + " LAZER!!");
			}
		}
		
	}
	
}