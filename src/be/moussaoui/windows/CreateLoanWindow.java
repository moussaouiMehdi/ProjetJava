package be.moussaoui.windows;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import be.moussaoui.pojo.Copy;
import be.moussaoui.pojo.Loan;
import be.moussaoui.pojo.Player;
import be.moussaoui.pojo.User;
import be.moussaoui.pojo.VideoGame;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateLoanWindow {

	private JFrame frame;
	private User connectedUser;
	private JTable table;
	private VideoGame game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateLoanWindow window = new CreateLoanWindow();
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
	public CreateLoanWindow() {
		initialize();
	}
	
	public CreateLoanWindow(User user, VideoGame game) {
		this.connectedUser= user;
		this.game=game;
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
		
		JLabel lblNewLabel = new JLabel("Mise en prêt jeu");
		lblNewLabel.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(174, 16, 208, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel jlLoanMessage = new JLabel("Mettre ce jeu en prêt ?");
		jlLoanMessage.setFont(new Font("Century", Font.PLAIN, 18));
		jlLoanMessage.setBounds(174, 56, 195, 54);
		frame.getContentPane().add(jlLoanMessage);
		
		JButton btnConfirm = new JButton("Confirmer");
		btnConfirm.setBackground(Color.GREEN);
		btnConfirm.setBounds(212, 242, 132, 42);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Annuler");
		btnCancel.setBackground(Color.RED);
		btnCancel.setBounds(212, 295, 132, 42);
		frame.getContentPane().add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 107, 391, 106);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(25);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
	
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{game.getName(), game.getConsole()},
			},
			new String[] {
				"Nom du jeu", "Console"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			@Override
			   public boolean isCellEditable(int row, int column) {
			       
			       return false;
			   }
		});
		
		JLabel jlLoanInfo = new JLabel("Une fois loué, il vous rapportera ");
		jlLoanInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jlLoanInfo.setBounds(84, 213, 195, 14);
		frame.getContentPane().add(jlLoanInfo);
		
		JLabel jlCreditAmount = new JLabel(game.getCreditCost() + " crédits");
		jlCreditAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		jlCreditAmount.setBounds(276, 213, 93, 14);
		frame.getContentPane().add(jlCreditAmount);
		table.getColumnModel().getColumn(0).setPreferredWidth(164);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(189);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToHomeWindow();
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createLoan();
				
			}
		});
	}
	private void backToHomeWindow() {
		HomeWindow newWindow = new HomeWindow(connectedUser);
		newWindow.getFrame().setVisible(true);
		frame.dispose();
	}
	private void createLoan() {
		try {
			Copy copy = new Copy(); 
			copy.setOwner((Player)connectedUser);
			copy.setGame(game);
			Loan loan = new Loan(); 
			loan.setCopy(copy);
			loan.setLender((Player)connectedUser);
			boolean success = loan.createLoan();
			if(success) {
				Player player= (Player)connectedUser;
				player.addLenderLoan(loan);
				player.addCopy(copy);
				connectedUser = player;
				JOptionPane.showMessageDialog(frame,"Votre jeu a bien été mis en prêt");
				backToHomeWindow();
			}
			//soit le jeu était réserver par un ou plusieurs joueur*/
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(frame, "Impossible de créer un nouveau jeu");
		}
		
	}
}
