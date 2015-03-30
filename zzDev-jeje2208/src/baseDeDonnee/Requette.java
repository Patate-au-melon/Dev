package baseDeDonnee;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;

public class Requette {
	
	public static ResultSet sendRequette(String requette){
		if(Connexion.co){
			Statement st = null;
			ResultSet rs=null;
			try {
				st = Connexion.cn.createStatement();
				rs = st.executeQuery(requette);
				return rs;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			Bukkit.getLogger().warning("Erreur de connexion a la base de donnee");
		}
		return null;
	}
	
	public static HashMap<String, ArrayList<String>> send(String requette){
		HashMap<String, ArrayList<String>> listFinal = new HashMap<String, ArrayList<String>>();
		if(Connexion.co){
			Statement st = null;
			ResultSet rs = null;
			try {
				st = Connexion.cn.createStatement();
				rs = st.executeQuery(requette);
				ResultSetMetaData meta = rs.getMetaData();
				rs.last();
				int nbLigne = rs.getRow();
				rs.beforeFirst();
				for(int i = 0 ; i<nbLigne; i++){
					listFinal.put(i+"", new ArrayList<String>());
				}
				
				while(rs.next()){
					ArrayList<String> list = new ArrayList<String>();
					for(int i = 1;i<=meta.getColumnCount();i++){
						list.add(rs.getString(i));
					}
					listFinal.put(rs.getRow()+"", list);
				}
				return listFinal;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			Bukkit.getLogger().warning("Erreur de connexion a la base de donnee");
		}
		return null;
	}
	
}
