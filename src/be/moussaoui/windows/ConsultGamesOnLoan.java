package be.moussaoui.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


import be.moussaoui.pojo.Loan;

import be.moussaoui.pojo.User;

public class ConsultGamesOnLoan  {
	private JFrame frame;
	private User connectedUser;
	private JTable table;
	private ArrayList<Loan> loans;
	private JButton btnCancelLoan;
	private String loanId;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultGamesOnLoan window = new ConsultGamesOnLoan();
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
	public ConsultGamesOnLoan() {
		initialize();
	}
	
	public ConsultGamesOnLoan(User user, ArrayList<Loan> loans) {
		this.connectedUser= user;
		this.loans= loans;
		initialize();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 734, 584);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Historique des jeux en prêts");
		lblNewLabel.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(172, 24, 338, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 72, 541, 235);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(25);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = table.getSelectedRow();
				loanId =  String.valueOf(table.getValueAt(rowSelected, 0)) ;
				String startState = String.valueOf(table.getValueAt(rowSelected, 1)) ;
				String ongoing = String.valueOf(table.getValueAt(rowSelected, 3)) ;
				if(startState.equals("Aucune") && ongoing.equals("Non")) {
					btnCancelLoan.setEnabled(true);
				}else {
					btnCancelLoan.setEnabled(false);
				}
			}
		});
		

		scrollPane.setViewportView(table);
		//
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] columns = { "Identifiant", "Date début", "Date de fin", "En cours ?", " Nom du jeu", "Nom de la console"};
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumnModel cModel= table.getColumnModel();
		cModel.getColumn(0).setPreferredWidth(200);
		cModel.getColumn(1).setPreferredWidth(50);
		cModel.getColumn(2).setPreferredWidth(50);
		
		for (Loan loan : loans) {
			String start="Aucune";
			String end="Aucune";
			if(loan.getStartDate() !=null) {
				start = loan.getStartDate().toString();
			}
			if(loan.getStartDate() !=null) {
				 end = loan.getEndDate().toString();
			}
			String ongoing = new Boolean(loan.isOngoing()).toString();
			String gameName = loan.getCopy().getGame().getName();
			String console = loan.getCopy().getGame().getConsole();
			model.addRow(new String[] {String.valueOf(loan.getId()),start,end,boolTrad(ongoing),gameName, console}); 
		}		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(250);
		
		JButton btnBack = new JButton("Retour");
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToMenu();
			}
		});
		btnBack.setBounds(262, 368, 165, 42);
		frame.getContentPane().add(btnBack);
		
		btnCancelLoan = new JButton("Annuler le prêt");
		btnCancelLoan.setEnabled(false);
		btnCancelLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelLoan();
			}
		});
		btnCancelLoan.setBackground(Color.LIGHT_GRAY);
		btnCancelLoan.setBounds(262, 318, 165, 42);
		frame.getContentPane().add(btnCancelLoan);
	}
	
	private String boolTrad(String bool) {
		if(bool =="false") {
			return "Non";
		}else {
			return "Oui";
		}
		
	}
	
	private void backToMenu() {
		HomeWindow newWindow = new HomeWindow(connectedUser);
		newWindow.getFrame().setVisible(true);
		frame.dispose();
	}
	
	private void cancelLoan() {
		/*Player playerCast = (Player)connectedUser;
		Player player = playerCast.getAllInfos();
		Loan loan = new Loan();
		loan.setId(Integer.valueOf(loanId));
		if(loan.delete()) {
			player.removeLoan(Integer.valueOf(loanId));
			player.update();
			JOptionPane.showMessageDialog(frame,"Prêt annulé");
			backToMenu();
		}else {
			JOptionPane.showMessageDialog(frame,"Impossible d'annuler le prêt");
		}*/
		
	}
}
