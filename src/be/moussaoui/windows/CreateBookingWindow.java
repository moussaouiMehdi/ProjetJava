package be.moussaoui.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import be.moussaoui.pojo.Booking;
import be.moussaoui.pojo.Player;
import be.moussaoui.pojo.User;
import be.moussaoui.pojo.VideoGame;
import be.moussaoui.windows.CreateBookingWindow;

public class CreateBookingWindow  {

	private JFrame frame;
	private User connectedUser;
	private VideoGame game;
	private JTable table;
	private JButton btnConfirm;
	private JButton btnCancelBooking;
	private JLabel jlMessage;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateBookingWindow window = new CreateBookingWindow();
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
	public CreateBookingWindow() {
		initialize();
	}
	
	public CreateBookingWindow(User user, VideoGame game) {
		this.connectedUser= user;
		this.game=game;
		initialize();
		checkAlreadyBook();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 591, 490);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Création d'une réservation");
		lblNewLabel.setFont(new Font("Century", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(124, 11, 339, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel jlBookingMessage = new JLabel("Voulez-vous réserver ce jeu ?");
		jlBookingMessage.setFont(new Font("Century", Font.PLAIN, 18));
		jlBookingMessage.setBounds(174, 56, 268, 54);
		frame.getContentPane().add(jlBookingMessage);
		
		
		btnConfirm = new JButton("Confirmer");
		btnConfirm.setBackground(Color.GREEN);
		btnConfirm.setBounds(212, 242, 132, 42);
		frame.getContentPane().add(btnConfirm);
		
		btnCancelBooking = new JButton("Annuler cette réservation");
		btnCancelBooking.setBackground(Color.GREEN);
		btnCancelBooking.setVisible(false);
		btnCancelBooking.setBounds(170, 242, 220, 42);
		frame.getContentPane().add(btnCancelBooking);
		
		
		JButton btnReturn = new JButton("Retour");
		btnReturn.setBackground(Color.RED);
		btnReturn.setBounds(212, 295, 132, 42);
		frame.getContentPane().add(btnReturn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 107, 391, 106);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(25);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		scrollPane.setViewportView(table);
		String credit =game.getCreditCost() + " crédits"; 
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{game.getName(), game.getConsole(), credit}, 
			},
			new String[] {
				"Nom du jeu", "Console", "Prix"
			}
		) {

			private static final long serialVersionUID = -7642328475774990266L;
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			@Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
		});
		Player player = (Player)connectedUser;
		JLabel jlCreditAmount = new JLabel("Vous possédez " + player.getCredit() + " crédits");
		if(!player.loanAllowed(game)){
			jlMessage.setText("Pas assez de crédit");
			btnConfirm.setEnabled(false);
		}
		jlCreditAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		jlCreditAmount.setBounds(152, 213, 217, 14);
		frame.getContentPane().add(jlCreditAmount);
		
		jlMessage = new JLabel();
		jlMessage.setForeground(Color.RED);
		jlMessage.setVisible(false);
		jlMessage.setFont(new Font("Tahoma", Font.ITALIC, 13));
		jlMessage.setBounds(152, 45, 311, 14);
		frame.getContentPane().add(jlMessage);
		table.getColumnModel().getColumn(0).setPreferredWidth(164);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(189);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToHomeWindow();
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createBooking();
				
			}
		});
		btnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelBooking();
			}
		});
		
		
	}
	
	private void backToHomeWindow() {
		HomeWindow newWindow = new HomeWindow(connectedUser);
		newWindow.getFrame().setVisible(true);
		frame.dispose();
	}
	private void createBooking() {
		try {
			Player player = (Player)connectedUser;
			Booking booking = new Booking(LocalDate.now(), player, game);
			boolean success = booking.createBooking();
			if(success) {
				player.addBooking(booking);
				player.reduceCredit(game.getCreditCost());
				player.update();
				connectedUser = player;
				JOptionPane.showMessageDialog(frame,"Votre réservation a bien été prise en compte");
				backToHomeWindow();
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(frame, "Impossible de réserver");
		}
	}
	
	private void cancelBooking() {
		int bookingId=0;
		Player playerCast = (Player)connectedUser;
		Player player = playerCast.getAllInfos();
		for(Booking booking: player.getBookings()){
			if(booking.getGame().getId() == game.getId()) {
				bookingId = booking.getBookingNumber();
			}
		}
		if(bookingId!=0) {
			Booking booking = new Booking(bookingId,LocalDate.now(), player, game);
			if(booking.delete()) {
				player.removeBooking(bookingId);
				player.increaseCredit(game.getCreditCost());
				player.update();
				connectedUser = player;
				JOptionPane.showMessageDialog(frame,"Votre réservation a bien été annulée, vos crédits ont été restitué");
				backToHomeWindow();
			}else {
				JOptionPane.showMessageDialog(frame,"Impossible d'annuler la réservation");
			}
		}
	}
	
	private void checkAlreadyBook() {
		Player playerCast =(Player)connectedUser;
		Player player = playerCast.getAllInfos();
		for(Booking booking: player.getBookings()){
			if(booking.getGame().getId() == game.getId()) {
				btnConfirm.setVisible(false);
				btnCancelBooking.setVisible(true);
				jlMessage.setText("Vous avez déjà placé une réservation sur ce jeu");
				jlMessage.setVisible(true);
			}
		}
	}

	
	

}
