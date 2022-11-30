package be.moussaoui.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class VideoGame implements Serializable {
	private static final long serialVersionUID = 973231249309477836L;
	private int id;
	private String name;
	private String creditCost;
	private String console;
	private ArrayList<Booking> bookings;
	private ArrayList<Copy> copies;
	
	
	
	public VideoGame(int id, String name, String creditCost, String console) {
		this.id = id;
		this.name = name;
		this.creditCost = creditCost;
		this.console = console;
	}
	public VideoGame(int id, String name, String creditCost, String console, ArrayList<Booking> bookings, ArrayList<Copy> copies) {
		this(id, name, creditCost, console);
		setBookings(bookings);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCost() {
		return creditCost;
	}

	public void setCreditCost(String creditCost) {
		this.creditCost = creditCost;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public void addBooking(Booking booking) {
		if(bookings==null) {
			bookings=new ArrayList<Booking>();
		}
		bookings.add(booking);
	}

	public ArrayList<Copy> getCopies() {
		return copies;
	}
	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}
	
	public void addCopy(Copy copy) {
		if(copies==null) {
			copies=new ArrayList<Copy>();
		}
		copies.add(copy);
	}
	
	//methods
	
	public Copy CopyAvailable() {
		return new Copy();
	}
	
	public void SelectBooking() {
		
	}

}
