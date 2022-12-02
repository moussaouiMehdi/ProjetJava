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

public class LoanDAO implements DAO<Loan> {
	private Connection conn;
	
	public LoanDAO() {
		this.conn = DataBaseConnection.getInstance();
	}

	@Override
	public boolean insert(Loan obj) {
		boolean success=false;
		boolean secondSuccess=false;
		boolean fullySuccess= false;
		int idCreated = 0;
		Copy copy = obj.getCopy();
		try {
			String query="INSERT INTO copy (gameNumber, playerId) VALUES(?,?)";
			PreparedStatement pstmt = (PreparedStatement) this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, copy.getGame().getId());
	        pstmt.setInt(2, copy.getOwner().getPlayerId());
	        pstmt.executeUpdate(); 
	        ResultSet res = pstmt.getGeneratedKeys();
			if (res.next()) {
				idCreated = res.getInt(1);
			}
	        success=true;
	        if(success && idCreated != 0) {
	        	int loanIdCreated=0;
				query= "INSERT INTO loan (ongoing, copyId) VALUES (?,?)";
	        	pstmt = (PreparedStatement) this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        	pstmt.setBoolean(1, false);
		        pstmt.setInt(2, idCreated);
		        pstmt.executeUpdate(); 
		        res = pstmt.getGeneratedKeys();
				if (res.next()) {
					loanIdCreated = res.getInt(1);
				}
		        secondSuccess=true;
		        if(secondSuccess && loanIdCreated != 0) {
		        	query= "INSERT INTO player_loan_details (loanId, playerId, type) VALUES (?,?,?)";
		        	pstmt = (PreparedStatement) this.conn.prepareStatement(query);
		        	pstmt.setInt(1, loanIdCreated);
			        pstmt.setInt(2, copy.getOwner().getPlayerId());
			        pstmt.setString(3, "lender");
			        pstmt.executeUpdate(); 
			        pstmt.close();
			        fullySuccess=true;
		        }
	        }

		}catch(SQLException e){
			e.printStackTrace();
		}
		return fullySuccess;
	}

	@Override
	public boolean delete(Loan obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Loan obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Loan find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Loan> findAll() {
		// TODO Auto-generated method stub
		return null;
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
