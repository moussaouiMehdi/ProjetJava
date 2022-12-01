package be.moussaoui.windows;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;

import be.moussaoui.pojo.Administrator;
import be.moussaoui.pojo.Player;
import be.moussaoui.pojo.User;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeWindow{
	private JFrame frame;
	private User connectedUser;

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
		
		JLabel jlWelcomeMessage = new JLabel("Bienvenue, " + connectedUser.getUsername());
		jlWelcomeMessage.setFont(new Font("Century", Font.PLAIN, 12));
		jlWelcomeMessage.setBounds(143, 58, 281, 14);
		frame.getContentPane().add(jlWelcomeMessage);
		
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
			JButton btnReservation = new JButton("Effectuer une réservation de jeu");
			btnReservation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
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
			btnOnLoan.setBounds(135, 154, 156, 42);
			frame.getContentPane().add(btnOnLoan);
		
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
		//HomeWindow newWindow=new HomeWindow(connectedUser);
		//changeWindow(newWindow.getFrame(), frame);
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
		HomeWindow home=new HomeWindow(connectedUser);
		changeWindow(home.getFrame(), frame);
	}
	
	public void changeWindow(JFrame newWindow, JFrame oldWindow) {
		oldWindow.dispose();
		newWindow.setVisible(true);
	}
	
}
