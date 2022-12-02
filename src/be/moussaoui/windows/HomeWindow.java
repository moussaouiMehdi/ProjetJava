package be.moussaoui.windows;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;

import be.moussaoui.pojo.Administrator;
import be.moussaoui.pojo.Loan;
import be.moussaoui.pojo.Player;
import be.moussaoui.pojo.User;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class HomeWindow{
	private JFrame frame;
	private User connectedUser;
	private JLabel jlCreditAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeWindow window = new HomeWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeWindow() {
		initialize();
	}
	
	public HomeWindow(User user) {
		this.connectedUser= user;
		initialize();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Accueil");
		lblNewLabel.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(172, 24, 119, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogout = new JButton("Se déconnecter");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		btnLogout.setFont(new Font("Century", Font.PLAIN, 12));
		btnLogout.setBounds(292, 24, 119, 23);
		frame.getContentPane().add(btnLogout);
		
		if(connectedUser instanceof Player) {
			Player player= (Player)connectedUser;
			jlCreditAmount = new JLabel();
			jlCreditAmount.setText("Credit : " + player.getCredit() );
			jlCreditAmount.setFont(new Font("Century", Font.PLAIN, 12));
			jlCreditAmount.setBounds(10, 32, 136, 14);
			frame.getContentPane().add(jlCreditAmount);
			
			JButton btnReservation = new JButton("Effectuer une réservation de jeu");
			btnReservation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameReservation();
				}
			});
			btnReservation.setBounds(115, 101, 220, 42);
			frame.getContentPane().add(btnReservation);
			
			JButton btnOnLoan = new JButton("Mettre un jeu en prêt");
			btnOnLoan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					putGameOnLoan();
				}
			});
			btnOnLoan.setBounds(140, 154, 156, 42);
			frame.getContentPane().add(btnOnLoan);
			
			JButton btnConsultGameOnLoan = new JButton("Consulter vos jeux en prêts");
			btnConsultGameOnLoan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					consultGamesOnLoan();
				}
			});
			btnConsultGameOnLoan.setBounds(115, 201, 220, 42);
			frame.getContentPane().add(btnConsultGameOnLoan);
		
		}
		if(connectedUser instanceof Administrator) {
			JButton btnNewButton = new JButton("Déterminer les unités de jeux");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					determineUnits();
				}
			});
			btnNewButton.setBounds(115, 101, 210, 42);
			frame.getContentPane().add(btnNewButton);
			
			JButton btnAddGame = new JButton("Ajouter un nouveau jeu");
			btnAddGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addNewGame();
				}
			});
			btnAddGame.setBounds(115, 155, 210, 42);
			frame.getContentPane().add(btnAddGame);
		}

	}
	private void logout() {
		connectedUser=null;
		AuthenticationWindow newWindow = new AuthenticationWindow();
		newWindow.getFrame().setVisible(true);
		frame.dispose();
	}
	
	private void gameReservation() {
		SelectGameWindow newWindow=new SelectGameWindow(connectedUser,true);
		changeWindow(newWindow.getFrame(), frame);
	}
	private void addNewGame() {
		AddNewGameWindow newWindow=new AddNewGameWindow(connectedUser);
		changeWindow(newWindow.getFrame(), frame);
	}
	private void determineUnits() {
		SelectGameWindow newWindow=new SelectGameWindow(connectedUser);
		changeWindow(newWindow.getFrame(), frame);
	}
	
	private void putGameOnLoan() {
		SelectGameWindow newWindow=new SelectGameWindow(connectedUser);
		changeWindow(newWindow.getFrame(), frame);
	}
	
	private void consultGamesOnLoan() {
		//charger les loans
		Player player =(Player)connectedUser;
		ArrayList<Loan> loans = Loan.findAllLenderLoans(player.getPlayerId());
		player.setLenderLoans(loans);
		ConsultGamesOnLoan newWindow=new ConsultGamesOnLoan(connectedUser, loans);
		changeWindow(newWindow.getFrame(), frame);
	}
	private void changeWindow(JFrame newWindow, JFrame oldWindow) {
		oldWindow.dispose();
		newWindow.setVisible(true);
	}
	
}
