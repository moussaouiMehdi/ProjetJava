package be.moussaoui.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import be.moussaoui.pojo.Copy;
import be.moussaoui.pojo.Loan;
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
		// TODO Auto-generated method stub
				return false;
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

	public ArrayList<Loan> findAllLenderLoans(int playerId) {
		ArrayList<Loan> loans= new ArrayList<Loan>();
		String type = "lender";
		String query=
		"SELECT videogame.gameName, player_loan_details.loanId, player_loan_details.playerId, console.consoleName, console.consoleVersion, loan.startDate, loan.endDate, loan.ongoing, loan.copyId, videogame.creditCost "
		+ "FROM console INNER JOIN (videogame INNER JOIN (player INNER JOIN (copy INNER JOIN (loan INNER JOIN player_loan_details ON loan.loanId = player_loan_details.loanId) ON copy.copyId = loan.copyId) ON (player.playerId = player_loan_details.playerId) "
		+ "AND (player.playerId = copy.playerId)) ON videogame.gameNumber = copy.gameNumber) ON console.consoleId = videogame.consoleId "
		+ "WHERE player_loan_details.type='"+type+"' and player_loan_details.playerId='"+playerId+"'";
		
		try{
			ResultSet result = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			while(result.next()){
				LocalDate startDate =null;
				LocalDate endDate =null;
				String gameName = result.getString(1);
				int loanId = result.getInt(2);
				String consoleName = result.getString(4);
				String consoleVersion = result.getString(5);
				if(result.getDate(6) != null) {
					startDate = result.getDate(6).toLocalDate();
				}
				if(result.getDate(7) != null) {
					endDate = result.getDate(7).toLocalDate();
				}
				
				//boolean ongoing = result.getBoolean(8);
				int copyId = result.getInt(9);
				String creditCost = result.getString(10);
				
				Player lender = new Player();
				lender.setPlayerId(playerId);
				VideoGame game = new VideoGame(gameName, creditCost, consoleName + " " + consoleVersion);
				Copy copy = new Copy(copyId, lender, game);
				Loan loan = new Loan(loanId, startDate, endDate,false, copy, lender, null);
				loans.add(loan);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return loans;
	}
	


}
