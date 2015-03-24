package first;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
//private PluginManager pm = getServer().getPluginManager();
	
	//Liste des classes
	
	
	@Override
	public void onEnable(){
		Config.main();
		Shop.recup();
		
		//Liste des events
		
		
	}
	
	public void onDisable(){
		
	
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Shop.main(sender, cmd, label, args);
		return false;
	}

}
