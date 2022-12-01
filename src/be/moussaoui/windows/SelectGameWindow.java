package be.moussaoui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import be.moussaoui.pojo.Administrator;
import be.moussaoui.pojo.Player;
import be.moussaoui.pojo.User;
import be.moussaoui.pojo.VideoGame;

import javax.swing.JList;

public class SelectGameWindow  {

	private JFrame frame;
	private User connectedUser;
	private String[] consoles;
	private JPanel formPanel;
	private String console;
	private JList consolesList;
	private JList gamesList;
	private ArrayList<VideoGame> games;
	private boolean borrower;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectGameWindow window = new SelectGameWindow();
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
	public SelectGameWindow() {
		initialize();
	}
	
	public SelectGameWindow(User user) {
		this.connectedUser= user;
		initialize();
	}
	public SelectGameWindow(User user, boolean bool) {
		this.connectedUser= user;
		this.borrower=bool;
		initialize();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 591, 490);
		frame.getContentPane().setLayout(null);
		
		formPanel = new JPanel(new GridLayout(6, 2, 40, 10));
		formPanel.setBounds(71, 50, 444, 741);
		
		JLabel lblNewLabel = new JLabel("Game selection");
		lblNewLabel.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(174, 16, 294, 23);
		frame.getContentPane().add(lblNewLabel);

		JPanel listPanel=new JPanel();
		listPanel.setOpaque(true);
		listPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel jlConsole = new JLabel("Select console : ");
		jlConsole.setFont(new Font("Century", Font.PLAIN, 14));
		listPanel.add(jlConsole);
		
		consoles = VideoGame.getAllConsoles();
		consolesList = new JList(consoles);
		consolesList.setBackground(Color.WHITE);
		consolesList.setForeground(Color.BLACK);
		consolesList.setFixedCellWidth(200);
		consolesList.setFont(new Font("Century", Font.BOLD, 12));
		consolesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		consolesList.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scroll = new JScrollPane(consolesList);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(200, 100));
		listPanel.add(scroll);
		formPanel.add(listPanel);
		
		gamesList = new JList();
		gamesList.setBackground(Color.WHITE);
		gamesList.setForeground(Color.BLACK);
		gamesList.setFixedCellWidth(200);
		gamesList.setFont(new Font("Century", Font.BOLD, 12));
		gamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gamesList.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel listPanel2=new JPanel();
		listPanel2.setOpaque(true);
		listPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel jlGame = new JLabel("Select the game : ");
		jlGame.setFont(new Font("Century", Font.PLAIN, 14));
		listPanel2.add(jlGame);
		
		JScrollPane scroll2 = new JScrollPane();
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll2.setPreferredSize(new Dimension(200, 100));
		listPanel2.add(scroll2);
		formPanel.add(listPanel2);
		
		JPanel btnPanel = new JPanel();
		formPanel.add(btnPanel);
		btnPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNext = new JButton("Suivant");
		
		btnNext.setBounds(163, 174, 124, 33);
		btnPanel.add(btnNext);
		
		JButton btnBack = new JButton("Retour");
		btnBack.setBounds(163, 218, 124, 28);
		btnPanel.add(btnBack);
		
		frame.getContentPane().add(formPanel);
		
		
		consolesList.getSelectionModel().addListSelectionListener(e->{
			console = (String)consolesList.getSelectedValue();
			if(console != null) {
				games = VideoGame.getAllGamesByConsole(console);
				String [] gamesString= new String[games.size()];
				for(int i=0;i<games.size();i++) {
					if(connectedUser instanceof Administrator || (connectedUser instanceof Player && borrower)) {
						gamesString[i]=games.get(i).getName() + " - crédit : " + games.get(i).getCreditCost();
					}
					if(connectedUser instanceof Player && !borrower) {
						gamesString[i]=games.get(i).getName() + " - ";
					}
					
				}
				gamesList.setListData(gamesString);
				scroll2.setViewportView(gamesList);
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = checkSelection();
				if(check) {
					if(connectedUser instanceof Administrator) {
						modifyUnits();
					}
					if(connectedUser instanceof Player && !borrower) {
						createLoan();
					}
					if(connectedUser instanceof Player && borrower) {
						
					}
					
				}
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToHomeWindow();
			}
		});
		
		
		
	}
	
	private void backToHomeWindow() {
		HomeWindow newWindow = new HomeWindow(connectedUser);
		newWindow.getFrame().setVisible(true);
		frame.dispose();
	}
	private boolean checkSelection() {
		if((String)consolesList.getSelectedValue() != null && (String)gamesList.getSelectedValue() != null) {
			return true;
		}
		return false;
	}
	private void modifyUnits() {

		DetermineUnitsWindow newWindow=new DetermineUnitsWindow(connectedUser, getGame());
		frame.dispose();
		newWindow.getFrame().setVisible(true);
	}
	
	private void createLoan() {
		CreateLoanWindow newWindow=new CreateLoanWindow(connectedUser, getGame());
		frame.dispose();
		newWindow.getFrame().setVisible(true);
	}
	
	
	
	private VideoGame getGame() {
		String gameComp = gamesList.getSelectedValue().toString();
		String [] gameCompCut = gameComp.split("-");
		String gameName = gameCompCut[0];
		
		VideoGame game = VideoGame.find(gameName);
		return game;
	}
}
