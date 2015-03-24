package first;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	
	public static void main(){
		File f = new File("plugins/plug/Shop.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		if(config.getString("Message.deleteNo")== null){
			createConfigShop();
		}
	}
	
	private static void createConfigShop(){
		File file = new File("plugins/plug/Shop.yml");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		ArrayList<String> list = new ArrayList<String>();
		list.add("pbPermission");
		list.add("fait");
		list.add("dejaFait");
		list.add("Prevent1");
		list.add("Prevent2");
		list.add("listeVide");
		list.add("Liste");
		list.add("couleur");
		list.add("deleteOk");
		list.add("deleteNo");
		
		for(int i = 0; i<list.size(); i++){
			config.set("Message."+list.get(i), list.get(i));
		}
		config.set("Liste", new ArrayList<String>());
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
