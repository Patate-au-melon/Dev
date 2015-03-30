package baseDeDonnee;

import java.sql.DriverManager;

import org.bukkit.Bukkit;

public class Connexion{
	static java.sql.Connection cn;
	static boolean co = false;
	private static String url = "jdbc:mysql://mysql-hbct.alwaysdata.net/hbct_plugin";
	private static String user = "hbct_plugin";
	private static String password = "jeje";
	
	public static void connect(){
		if(!co){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn =  DriverManager.getConnection(url, user, password);
				co = true;
				Bukkit.getLogger().info("Connexion a la base de donnee effectue");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
