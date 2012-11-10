package se.fronix.quake;

import mc.alk.arena.BattleArena;
import mc.alk.arena.util.Log;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
public class Quake extends JavaPlugin{
	static Quake plugin;
	
	@Override
	public void onEnable(){
		plugin = this;
		BattleArena.registerMatchType(this, "Quake", "quake", QuakeArena.class);

		/// Allow the damage to be set through the config.yml, if it exists and has the section: 'damage: <value>'
		/// Like 'damage: 15'
		FileConfiguration config = getConfig();
		QuakeArena.damage = config.getInt("damage", 20);
		Log.info("[" + getName()+ "] v" + getDescription().getVersion()+ " enabled!");
	}

	@Override
	public void onDisable(){
		Log.info("[" + getName()+ "] v" + getDescription().getVersion()+ " stopping!");
	}

}
