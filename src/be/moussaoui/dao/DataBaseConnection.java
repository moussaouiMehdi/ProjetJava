package be.moussaoui.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DataBaseConnection {
	private static Connection instance = null;

	private DataBaseConnection(){
		try{ 
			//chargement de la classe du driver par la JVM
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String url = "jdbc:ucanaccess://./example.accdb" ; 
			instance = DriverManager.getConnection(url);
		}
		catch(ClassNotFoundException ex){
			JOptionPane.showMessageDialog(null, "Classe de driver introuvable " + ex.getMessage());
			System.exit(0);
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erreur JDBC : " + ex.getMessage());
		}
	}
	
	public static Connection getInstance() { 
		if(instance == null){
			new DataBaseConnection();
		}
		return instance;
	}
}
