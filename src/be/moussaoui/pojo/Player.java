package be.moussaoui.pojo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Player extends User{
	private static final long serialVersionUID = -6463616663056283500L;
	private int credit;
	private String pseudo;
	private LocalDate registrationDate;
	private LocalDate dateOfBirth;
	private ArrayList<Booking> bookings;
	private ArrayList<Copy> copies;
	private ArrayList<Loan> lenderLoans;
	private ArrayList<Loan> borrowerLoans;
	
	public Player() {
		
	}
	
	public Player(int id, String username, String password, int credit, String pseudo, LocalDate registrationDate, LocalDate DoB) {
		super(id,username, password);
		setCredit(credit);
		setPseudo(pseudo);
		setRegistrationDate(registrationDate);
		setDateOfBirth(DoB);	
	}
	
	public Player(int id, String username, String password, int credit, String pseudo, LocalDate registrationDate, LocalDate DoB, ArrayList<Booking> bookings, ArrayList<Copy> copies, ArrayList<Loan> lenderLoans, ArrayList<Loan> borrowerLoans ) {
		this(id,username, password, credit, pseudo, registrationDate, DoB);
		setBookings(bookings);
		setCopies(copies);
		setLenderLoans(lenderLoans);
		setBorrowerLoans(borrowerLoans);
	}
	
	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	

	public ArrayList<Copy> getCopies() {
		return copies;
	}

	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}
	
	public ArrayList<Loan> getLenderLoans() {
		return lenderLoans;
	}

	public void setLenderLoans(ArrayList<Loan> lenderLoans) {
		this.lenderLoans = lenderLoans;
	}

	public ArrayList<Loan> getBorrowerLoans() {
		return borrowerLoans;
	}

	public void setBorrowerLoans(ArrayList<Loan> borrowerLoans) {
		this.borrowerLoans = borrowerLoans;
	}

	
	//methods 
	
	
	public void addBooking(Booking booking) {
		if(bookings==null) {
			bookings=new ArrayList<Booking>();
		}
		bookings.add(booking);
	}
	
	public void addCopy(Copy copy) {
		if(copies==null) {
			copies=new ArrayList<Copy>();
		}
		copies.add(copy);
	}
	
	public void addLenderLoan(Loan loan) {
		if(lenderLoans==null) {
			lenderLoans=new ArrayList<Loan>();
		}
		lenderLoans.add(loan);
	}
	
	public void addBorrowerLoan(Loan loan) {
		if(borrowerLoans==null) {
			borrowerLoans=new ArrayList<Loan>();
		}
		borrowerLoans.add(loan);
	}

	public boolean LoanAllowed() {
		return false;
	}
	public void AddBirthdayBonus() {
		this.credit += 10;
	}

}
