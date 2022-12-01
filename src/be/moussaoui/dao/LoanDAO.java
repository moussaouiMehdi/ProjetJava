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

}
