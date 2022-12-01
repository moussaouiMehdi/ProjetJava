package be.moussaoui.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import be.moussaoui.pojo.Player;
import be.moussaoui.pojo.User;

import javax.swing.JPasswordField;

public class SignUpWindow {
	private JFrame frame;
	private JLabel jlUsername;
	private JLabel jlPassword;
	private JLabel jlPseudo;
	private JLabel jlDoB;
	private JTextField tfUsername;
	private JTextField tfPseudo;
	private JPasswordField pfPassword;
	private JTextField tfDoB;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lbUsernameError;
	private JLabel lbUsernameError2;
	private JLabel lbPasswordError;
	private JLabel lbPseudoError;
	private JLabel lbPseudoError2;
	private JLabel lbDoBError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpWindow window = new SignUpWindow();
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
	public SignUpWindow() {
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
	
		JLabel lblNewLabel = new JLabel("Formulaire d'inscription");
		lblNewLabel.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(83, 11, 265, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogin = new JButton("S'inscrire");
		
		
		btnLogin.setBackground(new Color(240, 240, 240));
		btnLogin.setBounds(163, 184, 124, 28);
		frame.getContentPane().add(btnLogin);
		
		JButton btnBack = new JButton("Retour");
		
		btnBack.setBounds(163, 218, 124, 28);
		frame.getContentPane().add(btnBack);
		
		jlUsername = new JLabel("Nom d'utilisateur");
		jlUsername.setFont(new Font("Century", Font.PLAIN, 14));
		jlUsername.setBounds(41, 49, 112, 17);
		frame.getContentPane().add(jlUsername);
		
		jlPassword = new JLabel("Mot de passe");
		jlPassword.setFont(new Font("Century", Font.PLAIN, 14));
		jlPassword.setBounds(70, 82, 83, 17);
		frame.getContentPane().add(jlPassword);
		
		jlPseudo = new JLabel("Pseudo du joueur");
		jlPseudo.setFont(new Font("Century", Font.PLAIN, 14));
		jlPseudo.setBounds(40, 116, 123, 14);
		frame.getContentPane().add(jlPseudo);
		
		jlDoB = new JLabel("Date de naissance");
		jlDoB.setFont(new Font("Century", Font.PLAIN, 14));
		jlDoB.setBounds(37, 150, 124, 14);
		frame.getContentPane().add(jlDoB);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(167, 48, 140, 20);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		tfPseudo = new JTextField();
		tfPseudo.setBounds(167, 116, 140, 20);
		frame.getContentPane().add(tfPseudo);
		tfPseudo.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(167, 82, 140, 20);
		frame.getContentPane().add(pfPassword);
		
		tfDoB = new JTextField();
		tfDoB.setBounds(167, 150, 140, 20);
		frame.getContentPane().add(tfDoB);
		tfDoB.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Le pseudo est visible par tous les joueurs");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lblNewLabel_1.setBounds(10, 132, 153, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Le nom d'utilisateur n'est pas public");
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lblNewLabel_2.setBounds(10, 65, 143, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lbUsernameError = new JLabel("Nom d'utilisateur déjà utilisé");
		lbUsernameError.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lbUsernameError.setForeground(Color.RED);
		lbUsernameError.setBounds(163, 68, 153, 14);
		lbUsernameError.setVisible(false);
		frame.getContentPane().add(lbUsernameError);
		
		lbUsernameError2 = new JLabel("Nom d'utilisateur doit avoir une taille minimum de 5.");
		lbUsernameError2.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lbUsernameError2.setForeground(Color.RED);
		lbUsernameError2.setBounds(163, 68, 211, 14);
		lbUsernameError2.setVisible(false);
		frame.getContentPane().add(lbUsernameError2);
		
		lbPasswordError = new JLabel("Le mot de passe doit avoir une taille minimum de 5 ");
		lbPasswordError.setForeground(Color.RED);
		lbPasswordError.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lbPasswordError.setBounds(163, 102, 211, 14);
		lbPasswordError.setVisible(false);
		frame.getContentPane().add(lbPasswordError);
		
		lbPseudoError = new JLabel("Pseudo déjà utilisé");
		lbPseudoError.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lbPseudoError.setForeground(Color.RED);
		lbPseudoError.setBounds(163, 136, 185, 14);
		lbPseudoError.setVisible(false);
		frame.getContentPane().add(lbPseudoError);
		
		lbPseudoError2 = new JLabel("Pseudo doit avoir une taille minimum de 5.");
		lbPseudoError2.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lbPseudoError2.setForeground(Color.RED);
		lbPseudoError2.setBounds(163, 136, 211, 14);
		lbPseudoError2.setVisible(false);
		frame.getContentPane().add(lbPseudoError2);
		
		lbDoBError = new JLabel("Format incorrect : xx/xx/xxxx");
		lbDoBError.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lbDoBError.setForeground(Color.RED);
		lbDoBError.setBounds(161, 170, 171, 14);
		lbDoBError.setVisible(false);
		frame.getContentPane().add(lbDoBError);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToAuthenticationWindow();
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetErrorMessages();
				boolean check =  allFieldsCorrect();
				if(check) {
					try {
						String password=new String(pfPassword.getPassword());
						String username= tfUsername.getText();
						String pseudo = tfPseudo.getText();
						String dobString = tfDoB.getText();
						LocalDate date = convertStringToLocalDate(dobString);
						
						
						Player newPlayer = new Player(username, password, pseudo, date);
						//si annif le même jour +2 crédit
						newPlayer.addBirthdayBonus();
						boolean success = newPlayer.create();
						if(success) {
							
							JOptionPane.showMessageDialog(frame,"Félicitation " +username + "! Votre compte a été crée avec succès! Connectez-vous dès maintenant.");
							backToAuthenticationWindow();
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(frame, "Impossible de créer un nouveau compte, veuillez contacter l'admin");
					}
				}
			}
		});
	}
	
	private boolean allFieldsCorrect() {
		String password=new String(pfPassword.getPassword());
		String username= tfUsername.getText();
		String pseudo = tfPseudo.getText();
		String dobString = tfDoB.getText();
		
		if(username.isBlank() || username.length() < 3) {
			lbUsernameError2.setVisible(true);
			return false;
		}
		if(password.length() < 5) {
			lbPasswordError.setVisible(true);
			return false;
		}
		
		if(pseudo.isBlank() || pseudo.length() < 3) {
			lbPseudoError2.setVisible(true);
			return false;
		}
		
		boolean dateValid = dateIsValid(dobString);
		if(dateValid) {
			LocalDate date = convertStringToLocalDate(dobString);
		}else {
			lbDoBError.setVisible(true);
			return false;
		}
		
		if(checkUsernameUsed(username)) {
			lbUsernameError.setVisible(true);
			return false;
		}
		if(checkPseudoUsed(pseudo)) {
			lbPseudoError.setVisible(true);
			return false;
		}
		
		return true;
	}
	
	private boolean dateIsValid(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			LocalDate.parse(dateString, formatter);	
		}catch(DateTimeParseException e) {
			return false;
		}
		return true;
	}
	
	private LocalDate convertStringToLocalDate(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dateString, formatter);
		return date;
	}
	
	private void resetErrorMessages() {
		lbDoBError.setVisible(false);
		lbPseudoError.setVisible(false);
		lbPseudoError2.setVisible(false);
		lbPasswordError.setVisible(false);
		lbUsernameError.setVisible(false);
		lbUsernameError2.setVisible(false);
	}
	private boolean checkUsernameUsed(String usernameToTest) {
		return User.check(usernameToTest);
	}
	private boolean checkPseudoUsed(String pseudoToTest) {
		return Player.check(pseudoToTest);
	}
	
	private void backToAuthenticationWindow() {
		AuthenticationWindow newWindow = new AuthenticationWindow();
		newWindow.getFrame().setVisible(true);
		frame.dispose();
	}

}
