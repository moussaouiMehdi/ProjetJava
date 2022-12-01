package be.moussaoui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.moussaoui.pojo.VideoGame;

public class VideoGameDAO implements DAO<VideoGame> {
	private Connection conn;
	
	public VideoGameDAO() {
		this.conn = DataBaseConnection.getInstance();
	}


	@Override
	public boolean insert(VideoGame obj) {
		boolean success=false;
		String console=obj.getConsole();
		String name = obj.getName();
		String credit= obj.getCreditCost();
		try {
			int consoleId = getConsoleId(console);
			if(consoleId != 0) {
				String query="INSERT INTO videogame (gameName, creditCost, consoleId) VALUES(?,?,?)";
				PreparedStatement pstmt = (PreparedStatement) this.conn.prepareStatement(query);
				pstmt.setString(1, name);
		        pstmt.setString(2, credit);
		        pstmt.setInt(3, consoleId);
		        pstmt.executeUpdate(); 
		        pstmt.close();
		        success=true;
			}	
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public boolean delete(VideoGame obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(VideoGame obj) {
		boolean success=false;
		String query="UPDATE videogame SET creditCost='"+obj.getCreditCost()+"'WHERE gameNumber='"+obj.getId()+"'";
		try {
			PreparedStatement pstmt = (PreparedStatement) this.conn.prepareStatement(query);
	        pstmt.executeUpdate();
	        pstmt.close();
	        success=true;       
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public VideoGame find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<VideoGame> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	public static String[] findAllConsoles() {
		Connection conn = DataBaseConnection.getInstance();
		String[] consoles=null;
		int i=0;
		String query="SELECT consoleName, consoleVersion FROM console";
		try{
			ResultSet result = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			result.last();
			consoles= new String[result.getRow()];
			result.beforeFirst();
			while(result.next()){
				String name = result.getString("consoleName");
				String version = result.getString("consoleVersion");
				consoles[i] = name + " " + version;
				i++;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return consoles;
	}
	
	public int getConsoleId(String consoleName) {
		String[] consoleNameCut = consoleName.split(" ");
		String name = consoleNameCut[0];
		String version = consoleNameCut[1];
		int id = 0;
		String query="SELECT consoleId FROM console WHERE consoleName='"+name+"' AND consoleVersion='"+version+"'";
		try{
			ResultSet result = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if(result.next()){
				id = result.getInt("consoleId");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}


	public static ArrayList<VideoGame> findAllGamesByConsole(String consoleName) {
		Connection conn = DataBaseConnection.getInstance();
		String[] consoleNameCut = consoleName.split(" ");
		String name = consoleNameCut[0];
		String version = consoleNameCut[1];
		ArrayList<VideoGame> games= new ArrayList<VideoGame>();
		String query="SELECT videogame.gameNumber, videogame.gameName, videogame.creditCost, console.consoleName, console.consoleVersion "
				+ "FROM console INNER JOIN videogame ON console.consoleId = videogame.consoleId "
				+ "WHERE console.consoleName='"+name+"' AND console.consoleVersion='"+version+"'";
		try{
			ResultSet result = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			int i=0;
			while(result.next()){
				int gameNumber = result.getInt("gameNumber");
				String gameName = result.getString("gameName");
				String creditCost = String.valueOf(result.getInt("creditCost"));
				String consoleNameBD = result.getString("consoleName");
				String consoleVersion = result.getString("consoleVersion");
				String console = consoleNameBD + " " + consoleVersion;
				VideoGame game = new VideoGame(gameNumber,gameName, creditCost,console);
				games.add(game);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return games;
	}


	public static VideoGame findByName(String gameName) {
		Connection conn = DataBaseConnection.getInstance();
		VideoGame game = null;
		String query="SELECT videogame.gameNumber, videogame.gameName, videogame.creditCost, console.consoleName, console.consoleVersion "
				+ "FROM console INNER JOIN videogame ON console.consoleId = videogame.consoleId WHERE videogame.gameName='"+gameName+"'";
		try{
			ResultSet result = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if(result.next()){
				int gameNumber = result.getInt("gameNumber");
				String gameNameDB = result.getString("gameName");
				String creditCost = String.valueOf(result.getInt("creditCost")) ;
				String consoleName = result.getString("consoleName");
				String consoleVersion = result.getString("consoleVersion");
				game = new VideoGame(gameNumber,gameNameDB, creditCost, consoleName + " " +consoleVersion);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return game;
	}

}
