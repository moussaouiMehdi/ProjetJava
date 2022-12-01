package be.moussaoui.windows;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import be.moussaoui.pojo.User;
import be.moussaoui.pojo.VideoGame;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class DetermineUnitsWindow {

	private JFrame frame;
	private User connectedUser;
	private VideoGame game;
	private JTable table;
	private String newValue;
	private JLabel jlMessageError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetermineUnitsWindow window = new DetermineUnitsWindow();
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
	public DetermineUnitsWindow() {
		initialize();
	}
	
	public DetermineUnitsWindow(User user, VideoGame game) {
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
		
		JLabel lblNewLabel = new JLabel("Modification de crédit");
		lblNewLabel.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(152, 11, 261, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 72, 391, 106);
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
				{game.getId(), game.getName(),game.getConsole(), game.getCreditCost()},
			},
			new String[] {
				"Identifiant", "Nom du jeu", "Console", "Valeur credit"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			@Override
			   public boolean isCellEditable(int row, int column) {
			       
			       return column == 3 && row == 0;
			   }
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(61);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(144);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(77);

		JButton btnModify = new JButton("Modifier");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getCellEditor() != null) {
	                DefaultCellEditor cellEditor = (DefaultCellEditor) table.getCellEditor();
	                newValue = ((JTextField) cellEditor.getComponent()).getText();
	            }
				modifyCredit();
			}
		});
		btnModify.setBounds(212, 242, 132, 42);
		frame.getContentPane().add(btnModify);
		
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToSelectionWindow();
			}
		});
		btnBack.setBounds(212, 295, 132, 42);
		frame.getContentPane().add(btnBack);
		
		jlMessageError = new JLabel("Veuillez entrer une nouvelle valeur comprise entre 0 et 11");
		jlMessageError.setForeground(Color.RED);
		jlMessageError.setFont(new Font("Century", Font.PLAIN, 14));
		jlMessageError.setBounds(89, 200, 419, 14);
		jlMessageError.setVisible(false);
		frame.getContentPane().add(jlMessageError);
		
		
		
	}
	
	private void backToSelectionWindow() {
		SelectGameWindow newWindow = new SelectGameWindow(connectedUser);
		newWindow.getFrame().setVisible(true);
		frame.dispose();
	}
	
	private void modifyCredit() {
		boolean valueOK= isInt(newValue);
		
		if(valueOK) {
			String oldValue= game.getCreditCost();
			game.setCreditCost(newValue);
			boolean success = game.update();
			if(success) {
				JOptionPane.showMessageDialog(frame,"Crédit du jeu "+ game.getName() + " modifié! " + oldValue + "->"+ game.getCreditCost());
				backToHomeWindow();
			}else {
				JOptionPane.showMessageDialog(frame,"Impossible de mettre à jour!");
			}
		}else {
			jlMessageError.setVisible(true);
		}
	}
	
	private boolean isInt(String value) {
		try {
			int nbr = Integer.valueOf(value);
			if(nbr == 0 || nbr <=0 || nbr >= 11) {
				return false;
			}
			return true;
			
		}catch(Exception e) {
			return false;
		}
	}
	private void backToHomeWindow() {
		HomeWindow newWindow = new HomeWindow(connectedUser);
		newWindow.getFrame().setVisible(true);
		frame.dispose();
	}
}
