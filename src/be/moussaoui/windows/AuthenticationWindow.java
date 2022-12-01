package be.moussaoui.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;

import be.moussaoui.pojo.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AuthenticationWindow {
	private JFrame frame;
	private JLabel jlUsername;
	private JLabel jlPassword;
	private JLabel jlMessageError;
	private final JTextField tfUsername = new JTextField();
	private JPasswordField pfPassword;
	private User connectedUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthenticationWindow window = new AuthenticationWindow();
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
	public AuthenticationWindow() {
		initialize();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);

		tfUsername.setBounds(137, 94, 157, 23);
		tfUsername.setColumns(10);
		frame.getContentPane().add(tfUsername);
		
		JLabel lblNewLabel = new JLabel("Connexion");
		lblNewLabel.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(152, 11, 119, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogin = new JButton("Se connecter");
		
		btnLogin.setBackground(new Color(240, 240, 240));
		btnLogin.setBounds(152, 159, 130, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnSignUp = new JButton("S'inscrire");
		btnSignUp.setBounds(152, 193, 130, 23);
		frame.getContentPane().add(btnSignUp);
		
		jlUsername = new JLabel("Nom d'utilisateur");
		jlUsername.setFont(new Font("Century", Font.PLAIN, 14));
		jlUsername.setBounds(10, 96, 119, 14);
		frame.getContentPane().add(jlUsername);
		
		jlPassword = new JLabel("Mot de passe");
		jlPassword.setFont(new Font("Century", Font.PLAIN, 14));
		jlPassword.setBounds(10, 127, 89, 14);
		frame.getContentPane().add(jlPassword);
		
		jlMessageError = new JLabel("");
		jlMessageError.setForeground(Color.RED);
		jlMessageError.setBounds(10, 57, 245, 14);

		frame.getContentPane().add(jlMessageError);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(137, 125, 157, 23);
		frame.getContentPane().add(pfPassword);
		
		
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SignUpWindow newWindow=new SignUpWindow();
				newWindow.getFrame().setVisible(true);
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jlMessageError.setText("");
				boolean check = allFieldsCorrect();
				if(check) {
					authentication();
				}
				else {
					jlMessageError.setText("Veuillez remplir tous les champs");
				}	
			}
		});
	}
	private boolean allFieldsCorrect() {
		String password=new String(pfPassword.getPassword());
		
		if(tfUsername.getText().isBlank() || password.isBlank() || tfUsername.getText().length() < 3 || password.length()<3) {
			return false;
		}
		return true;
	}
	
	private void authentication() {
		String username = tfUsername.getText();
		String password=new String(pfPassword.getPassword());
		connectedUser = User.login(username, password);
		if(connectedUser == null) {
			jlMessageError.setText("Identifiant et/ou mot de passe incorrect");
		}else {
			HomeWindow home=new HomeWindow(connectedUser);
			JOptionPane.showMessageDialog(frame, username + ", bienvenue, vous vous êtes connecté avec succès!",null,JOptionPane.INFORMATION_MESSAGE,null);
			frame.dispose();
			home.getFrame().setVisible(true);
		}
		
	}
}
