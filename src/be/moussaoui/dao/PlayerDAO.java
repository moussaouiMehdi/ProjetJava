package be.moussaoui.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import be.moussaoui.pojo.Booking;
import be.moussaoui.pojo.Player;
import be.moussaoui.pojo.VideoGame;

public class PlayerDAO implements DAO<Player>{
	private Connection conn;
	
	public PlayerDAO() {
		this.conn = DataBaseConnection.getInstance();
	}

	@Override
	public boolean insert(Player obj) {
		boolean success=false;
		boolean fullySuccess=false;
		String password=obj.getPassword();
		String username= obj.getUsername();
		String pseudo = obj.getPseudo();
		LocalDate dob = obj.getDateOfBirth();
		int idCreated = 0;
		
		String query="INSERT INTO user (username, password, type) VALUES(?,?,?)";
		try {
			PreparedStatement pstmt = (PreparedStatement) this.conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        pstmt.setString(3, "Player");
	        pstmt.executeUpdate(); 
	        ResultSet res = pstmt.getGeneratedKeys();
			while (res.next()) {
				idCreated = res.getInt(1);
			}
	        success=true;
	        if(success && idCreated != 0) {
				query= "INSERT INTO player (pseudo, registrationDate, dateOfBirth, credit, userId) VALUES (?,?,?,?,?)";
	        	pstmt = (PreparedStatement) this.conn.prepareStatement(query);
	        	pstmt.setString(1, pseudo);
		        pstmt.setDate(2, Date.valueOf(LocalDate.now()));
		        pstmt.setDate(3, Date.valueOf(dob));
		        pstmt.setInt(4, 10);
		        pstmt.setInt(5, idCreated);
		        pstmt.executeUpdate(); 
		        pstmt.close();
		        fullySuccess=true;
	        }
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return fullySuccess;
	}

	@Override
	public boolean delete(Player obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Player obj) {
		boolean success=false;
		String query="UPDATE player SET credit='"+obj.getCredit()+"' WHERE playerId='"+obj.getPlayerId()+"'";
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
	public Player find(int id) {
		Player player = null;
		String query="SELECT user.userId, player.pseudo, player.registrationDate, player.dateOfBirth, player.credit, player.playerId, user.username, user.password"
				+ " FROM user INNER JOIN player ON user.userId = player.userId WHERE user.userId='"+id+"'";
		try{
			ResultSet result = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			while(result.next()){
				String username = result.getString("username");
				String pseudo = result.getString("pseudo");
				String password = result.getString("password");
				LocalDate registrationDate = result.getDate("registrationDate").toLocalDate();
				LocalDate dob = result.getDate("dateOfBirth").toLocalDate();
				int credit= result.getInt("credit");
				int playerId = result.getInt("playerId");
				player = new Player(id,playerId, username, password,credit, pseudo, registrationDate, dob);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return player;
	}

	@Override
	public ArrayList<Player> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static boolean check(String pseudo) {
		Connection conn = DataBaseConnection.getInstance();
		String query="SELECT playerId FROM player WHERE pseudo='"+pseudo+"'";
		try{
			ResultSet result = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if(result.first()){
				return true;
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public Player getAllInfos(int playerId) {
		Player player=null;
		int cpt=0;
		String query=
				"SELECT player.playerId, player.pseudo, player.registrationDate, player.dateOfBirth, player.credit, player.userId, booking.bookingId, booking.bookingDate, "
				+ "booking.gameNumber, videogame.gameName, videogame.creditCost, videogame.consoleId, console.consoleName, console.consoleVersion, user.username, user.password "
				+ "FROM user INNER JOIN ((console INNER JOIN videogame ON console.consoleId = videogame.consoleId) "
				+ "INNER JOIN (player INNER JOIN booking ON player.playerId = booking.playerId) ON videogame.gameNumber = booking.gameNumber) "
				+ "ON user.userId = player.userId WHERE player.playerId='"+playerId+"'";
				
		try{
			ResultSet result = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			while(result.next()){
				if(cpt==0) {
					String pseudo = result.getString("pseudo");
					LocalDate registrationDate = result.getDate("dateOfBirth").toLocalDate();
					LocalDate dateOfBirth = result.getDate("dateOfBirth").toLocalDate();
					int credit= result.getInt("credit");
					int userId = result.getInt("userId");
					String username = result.getString("username");
					String password = result.getString("password");
					player = new Player(userId,playerId,username,password,credit,pseudo,registrationDate,dateOfBirth);
					cpt++;
				}
				int bookingId = result.getInt("bookingId");
				LocalDate bookingDate = result.getDate("bookingDate").toLocalDate();
				int gameNumber = result.getInt("gameNumber");
				String gameName = result.getString("gameName");
				int creditCost = result.getInt("creditCost");
				int consoleId = result.getInt("consoleId");
				String consoleName = result.getString("consoleName");
				String consoleVersion = result.getString("consoleVersion");
				VideoGame game = new VideoGame(gameNumber,gameName,String.valueOf(creditCost),consoleName + " " +consoleVersion);
				Booking booking = new Booking(bookingId,bookingDate,player, game);
				
				player.addBooking(booking);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return player;
	}
	


}
