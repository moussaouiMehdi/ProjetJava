package be.moussaoui.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.moussaoui.dao.LoanDAO;


public class Loan implements Serializable {
	private static final long serialVersionUID = -3709946114376527758L;
	private int id;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean ongoing;
	private Copy copy;
	private Player lender;
	private Player borrower;
	
	public Loan() {
		
	}
	
	public Loan(int id, Copy copy, Player lender, Player borrower){
		this.id=id;
		setCopy(copy);
		setLender(lender);
		setBorrower(borrower);
		this.ongoing = true;
		this.startDate = LocalDate.now();
	}
	
	public Loan(int id, LocalDate startDate, LocalDate endDate, boolean ongoing, Copy copy, Player lender, Player borrower){
		this(id, copy, lender, borrower);
		this.startDate = startDate;
		this.endDate = endDate;
		this.ongoing = ongoing;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isOngoing() {
		return ongoing;
	}

	public void setOngoing(boolean ongoing) {
		this.ongoing = ongoing;
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

	public Player getLender() {
		return lender;
	}

	public void setLender(Player lender) {
		this.lender = lender;
	}

	public Player getBorrower() {
		return borrower;
	}

	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}
	
	//methods
	
	public void calculateBalance() {
		
	}
	
	public void endLoan() {
		this.ongoing=false;
		
	}
	public boolean createLoan() {
		LoanDAO loanDAO = new LoanDAO();
		return loanDAO.insert(this);
	}

	public static ArrayList<Loan> findAllLenderLoans(int playerId) {
		LoanDAO loanDAO = new LoanDAO();
		return loanDAO.findAllLenderLoans(playerId);
	}

}
