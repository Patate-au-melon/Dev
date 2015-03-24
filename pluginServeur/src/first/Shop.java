package first;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Shop implements Listener{
	
	public static ArrayList<String> list = new ArrayList<String>();
	
	//lecture de la config a chaque fois que l'on en a besoin
	public static FileConfiguration config(){
		File conf = new File("plugins/plug/Shop.yml");
		FileConfiguration configShop = YamlConfiguration.loadConfiguration(conf);
		return configShop;
	}
	
	@SuppressWarnings("unchecked")
	public static void recup(){
		list = (ArrayList<String>) config().get("Liste");
	}
	
	//Fonction principale
	public static void main(CommandSender sender, Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("shop")){
			if(sender instanceof Player){
				if(args.length == 0){
					add(sender, cmd, label, args);
				}else if(args.length == 1 && (args[0].equalsIgnoreCase("liste") || args[0].equalsIgnoreCase("listes"))){
					list(sender, cmd, label, args);
				}else if(args.length == 2 && args[0].equalsIgnoreCase("delete")){
					delete(sender, cmd, label, args);
				}
			}else{
				sender.sendMessage("Cette commande est reserve aux joueurs");
			}
		}
	}
	
	//Commande "/shop"  
	public static void add(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		String name = p.getName();
		if(!list.contains(name)){
			list.add(name);
			prevent(p);
			p.sendMessage(config().getString("Message.fait"));
			FileConfiguration config = config();
			config.set("Liste", list);
			try {
				config.save("plugins/plug/Shop.yml");
			} catch (IOException e) {
				e.printStackTrace();
				Bukkit.getLogger().info("Erreur de sauvegarde");
			}
		}else{
			p.sendMessage(config().getString("Message.dejaFait"));
		}
	}
	
	//Previent le joueur si il est sur le serveur
	public static void prevent(Player p){
		Iterator<? extends Player> pl = Bukkit.getOnlinePlayers().iterator();
		while(pl.hasNext()){
			Player pm = pl.next();
			if(pm.hasPermission("plug.prevent")){
				String msg = config().getString("Message.Prevent1") + " " + p.getName() + " " + config().getString("Message.Prevent2");
				pm.sendMessage(msg);
			}
		}
	}
	
	public static void list(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;
			if(p.hasPermission("plug.liste")){
				if(list.isEmpty()){
					p.sendMessage(config().getString("Message.listeVide"));
				}else{
					p.sendMessage(config().getString("Message.Liste"));
					p.sendMessage(" ");
					for(int i = 0;i<list.size(); i++){
						String name = list.get(i);
						p.sendMessage(config().getString("Message.couleur") + "  -" + name);
					}
			}
		}else{
			p.sendMessage(config().getString("Message.pbPermission"));
		}
	}

	public static void delete(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;
		if(p.hasPermission("plug.delete")){
			if(list.contains(args[1])){
				list.remove(args[1]);
				p.sendMessage(config().getString("Message.deleteOk"));
			}else{
				p.sendMessage(config().getString("Message.deleteNo"));
			}
		}else{
			p.sendMessage(config().getString("Message.pbPermission"));
		}
	}
}
