package be.moussaoui.pojo;

import java.time.LocalDate;
import java.util.ArrayList;

import be.moussaoui.dao.PlayerDAO;

public class Player extends User{
	private int playerId;
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
	
	public Player(String username, String password, String pseudo, LocalDate dob) {
		this.setUsername(username);
		this.setPassword(password);
		this.pseudo = pseudo;
		this.dateOfBirth=dob;
	}
	
	public Player(int userId, int playerId, String username, String password, int credit, String pseudo, LocalDate registrationDate, LocalDate DoB) {
		super(userId,username, password);
		this.setPlayerId(playerId);
		setCredit(credit);
		setPseudo(pseudo);
		setRegistrationDate(registrationDate);
		setDateOfBirth(DoB);	
	}
	
	public Player(int id , int playerId, String username, String password, int credit, String pseudo, LocalDate registrationDate, LocalDate DoB, ArrayList<Booking> bookings, ArrayList<Copy> copies, ArrayList<Loan> lenderLoans, ArrayList<Loan> borrowerLoans ) {
		this(id,playerId, username, password, credit, pseudo, registrationDate, DoB);
		setBookings(bookings);
		setCopies(copies);
		setLenderLoans(lenderLoans);
		setBorrowerLoans(borrowerLoans);
	}
	
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
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
	
	@Override
	public String toString() {
		return super.toString()+ "Joueur: ["+ pseudo + " - " + dateOfBirth + " - " + registrationDate + " - " + credit + "]";
	}

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

	public boolean loanAllowed(VideoGame game) {
			return this.credit >= Integer.valueOf(game.getCreditCost()); 
	}
	public void addBirthdayBonus() {
		LocalDate now = LocalDate.now();
		//créer même jour que annif
		if(this.getDateOfBirth().getDayOfMonth() == now.getDayOfMonth() && this.getDateOfBirth().getMonthValue() == now.getMonthValue()) {
			this.credit += 2;
		}
		//cas
		
	}
	
	public static boolean check(String pseudo) {
		return PlayerDAO.check(pseudo);
	}
	
	public boolean create() {
		PlayerDAO playerDAO = new PlayerDAO();
		return playerDAO.insert(this);
	}

	public Player getInfos() {
		Player player = null;
		PlayerDAO playerDAO = new PlayerDAO();
		player = playerDAO.find(this.getUserId());
		return player;
	}

	public ArrayList<Loan> findAllLenderLoans() {
		PlayerDAO playerDAO = new PlayerDAO();
		return playerDAO.findAllLenderLoans(this.getPlayerId());
	}
	
	public boolean update() {
		PlayerDAO playerDAO = new PlayerDAO();
		return playerDAO.update(this);
	}

}
