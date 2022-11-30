package be.moussaoui.pojo;

import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable{
	private static final long serialVersionUID = 3648845951039787185L;
	private int bookingNumber;
	private LocalDate bookingDate;
	private Player player;
	private VideoGame game;
	
	public Booking(LocalDate bookingDate, Player player, VideoGame game) {
		setBookingDate(bookingDate);
		this.setPlayer(player);
		this.game= game;
	}
	public Booking(int bookingNumber, LocalDate bookingDate, Player player, VideoGame game) {
		this(bookingDate,player, game);
		this.bookingNumber = bookingNumber;
	}
	
	public void setBookingNumber(int bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getBookingNumber() {
		return bookingNumber;
	}

	public VideoGame getGame() {
		return game;
	}
	public void setGame(VideoGame game) {
		this.game = game;
	}
	
	//methods
	
	public void Delete() {
		
	}

}
