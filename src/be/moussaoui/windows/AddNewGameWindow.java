package be.moussaoui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import be.moussaoui.pojo.User;
import be.moussaoui.pojo.VideoGame;
import javax.swing.border.LineBorder;

public class AddNewGameWindow{
	private JFrame frame;
	private User connectedUser;
	private String[] consoles;
	private JList consolesList;
	private JLabel jlGameName;
	private JLabel jlCreditCost;
	private JLabel jlConsoleName;
	private JTextField tfGameName;
	private JTextField tfCreditCost;
	private JLabel jlConsoleError;
	private JLabel jlCreditError;
	private JLabel jlConsoleNameError;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewGameWindow window = new AddNewGameWindow();
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
	private AddNewGameWindow() {
		initialize();
	}
	public AddNewGameWindow(User user) {
		connectedUser=user;
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
		
		JLabel lblNewLabel = new JLabel("Ajout d'un nouveau jeu");
		lblNewLabel.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(83, 11, 265, 27);
		frame.getContentPane().add(lblNewLabel);
		
		jlGameName = new JLabel("Nom du jeu");
		jlGameName.setFont(new Font("Century", Font.PLAIN, 14));
		jlGameName.setBounds(41, 49, 112, 17);
		frame.getContentPane().add(jlGameName);
		
		jlCreditCost = new JLabel("Nombre de crédit");
		jlCreditCost.setFont(new Font("Century", Font.PLAIN, 14));
		jlCreditCost.setBounds(10, 82, 143, 17);
		frame.getContentPane().add(jlCreditCost);
		
		jlConsoleName = new JLabel("Console");
		jlConsoleName.setFont(new Font("Century", Font.PLAIN, 14));
		jlConsoleName.setBounds(40, 116, 123, 14);
		frame.getContentPane().add(jlConsoleName);
			
		tfGameName = new JTextField();
		tfGameName.setBounds(167, 48, 140, 20);
		frame.getContentPane().add(tfGameName);
		tfGameName.setColumns(10);
		
		jlConsoleNameError = new JLabel("Le nom doit être composé de minimum 3 lettres");
		jlConsoleNameError.setForeground(Color.RED);
		jlConsoleNameError.setBounds(154, 67, 245, 14);
		jlConsoleNameError.setVisible(false);
		frame.getContentPane().add(jlConsoleNameError);
		
		tfCreditCost = new JTextField();
		tfCreditCost.setBounds(167, 82, 140, 20);
		frame.getContentPane().add(tfCreditCost);
		tfCreditCost.setColumns(10);
		
		jlCreditError = new JLabel("La valeur doit être comprise entre 0 et 11");
		jlCreditError.setForeground(Color.RED);
		jlCreditError.setBounds(154, 102, 245, 14);
		jlCreditError.setVisible(false);
		frame.getContentPane().add(jlCreditError);
		
		JPanel listPanel=new JPanel();
		listPanel.setLocation(234, 105);
		listPanel.setOpaque(true);
		
		consoles = VideoGame.getAllConsoles();
		listPanel.setLayout(null);
		frame.getContentPane().add(listPanel);
		
		JButton btnNext = new JButton("Suivant");
		btnNext.setBounds(163, 174, 124, 33);
		frame.getContentPane().add(btnNext);
		
		JButton btnBack = new JButton("Retour");
		
		btnBack.setBounds(163, 218, 124, 28);
		frame.getContentPane().add(btnBack);
		
		consolesList = new JList(consoles);
		consolesList.setBackground(Color.WHITE);
		consolesList.setForeground(Color.BLACK);
		consolesList.setFixedCellWidth(200);
		consolesList.setFont(new Font("Century", Font.BOLD, 12));
		consolesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		consolesList.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scroll = new JScrollPane(consolesList);
		scroll.setBounds(165, 115, 142, 44);
		frame.getContentPane().add(scroll);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(210, 50));
		
		jlConsoleError = new JLabel("Veuillez sélectionner une console");
		jlConsoleError.setForeground(Color.RED);
		jlConsoleError.setBounds(154, 161, 245, 14);
		jlConsoleError.setVisible(false);
		frame.getContentPane().add(jlConsoleError);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToHomeWindow();
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetErrorMessages();
				boolean success = formValidation();
				if(success) {
					createNewGame();
				}
			}
		});
			
	}
	
	private void backToHomeWindow() {
		HomeWindow newWindow = new HomeWindow(connectedUser);
		newWindow.getFrame().setVisible(true);
		frame.dispose();
	}
	
	private void createNewGame() {
		try {
			String selectedConsole=(String) consolesList.getSelectedValue();
			String gameName = tfGameName.getText();
			String creditCost= tfCreditCost.getText();
			VideoGame newGame = new VideoGame(gameName, creditCost, selectedConsole);
			boolean success = newGame.createNewGame();
			if(success) {
				JOptionPane.showMessageDialog(frame,"Le jeu "+ gameName + " a bien été ajouté!");
				backToHomeWindow();
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(frame, "Impossible de créer un nouveau jeu");
		}
	}
	
	private boolean formValidation() {
		String gameName = tfGameName.getText();
		String selectedConsole=(String) consolesList.getSelectedValue();
		
		if(gameName.isBlank() || gameName.length() < 3) {
			jlConsoleNameError.setVisible(true);
			return false;
		}
		if(!isInt(tfCreditCost.getText())) {
			jlCreditError.setVisible(true);
			return false;
		}
		int creditCost=Integer.valueOf(tfCreditCost.getText());
		if(creditCost <=0 || creditCost >=11) {
			jlCreditError.setVisible(true);
			return false;
		}
		if(selectedConsole == null) {
			jlConsoleError.setVisible(true);
			return false;
		}
		return true;
		
	}
	
	private void resetErrorMessages() {
		jlConsoleError.setVisible(false);
		jlCreditError.setVisible(false);
		jlConsoleNameError.setVisible(false);
	}
	
	private boolean isInt(String test) {
		try {
			Integer.valueOf(tfCreditCost.getText());
			return true;
			
		}catch(Exception e) {
			return false;
		}
	}
}
