package be.moussaoui.pojo;

import java.io.Serializable;

public class Copy implements Serializable{
	private static final long serialVersionUID = -3215371805744917157L;
	private int copyNumber;
	private Player owner;
	private VideoGame game;
	private Loan loan;
	
	public Copy() {
		
	}
	
	public Copy(int copyNumber, Player owner, VideoGame game) {
		this.copyNumber= copyNumber;
		setOwner(owner);
		setGame(game);
	}
	
	public Copy(int copyNumber, Player owner, VideoGame game, Loan loan) {
		this(copyNumber, owner, game);
		this.setLoan(loan);
	}
	
	public int getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public VideoGame getGame() {
		return game;
	}

	public void setGame(VideoGame game) {
		this.game = game;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	//methods

	public void realeaseCCopy() {
		
	}
	
	public void borrow() {
		
	}
	
	public boolean isAvailable() {
		return false;
	}

}
