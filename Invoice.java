import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

public class Invoice {

	private JFrame frmInvoice;
	private JLabel DateLbl;
	private JLabel TimeLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Invoice window = new Invoice();
					window.frmInvoice.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;
	private JPanel panel;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Create the application.
	 */
	public Invoice() {
		initialize();
		showDate();
		showTime();
		conn = connection.dbConnector();		
	}
	
	void showDate(){
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		DateLbl.setText(s.format(d));	
	}

	void showTime(){
		
		new Timer(0, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date d = new Date();
				SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
				TimeLbl.setText(s.format(d)); 
				
			}


		}).start();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInvoice = new JFrame();
		frmInvoice.setIconImage(Toolkit.getDefaultToolkit().getImage(Invoice.class.getResource("/Images/Invoice.png")));
		frmInvoice.setTitle("INVOICE");
		frmInvoice.setForeground(Color.BLACK);
		frmInvoice.setBackground(new Color(0, 0, 255));
		frmInvoice.getContentPane().setBackground(Color.CYAN);
		frmInvoice.setBounds(100, 100, 862, 493);
		frmInvoice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInvoice.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		panel.setBounds(10, 11, 826, 432);
		frmInvoice.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INVOICE");
		lblNewLabel.setBounds(245, 16, 336, 61);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setIcon(new ImageIcon(Invoice.class.getResource("/Images/Invoice.png")));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		DateLbl = new JLabel("New label");
		DateLbl.setBounds(634, 35, 86, 26);
		panel.add(DateLbl);
		DateLbl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		DateLbl.setForeground(Color.BLUE);
		DateLbl.setBackground(Color.WHITE);
		
		TimeLbl = new JLabel("New label");
		TimeLbl.setBounds(730, 35, 86, 26);
		panel.add(TimeLbl);
		TimeLbl.setForeground(Color.BLUE);
		TimeLbl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JButton btnNewButton = new JButton("Create New");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(317, 151, 192, 49);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Invoice Records");
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InvoiceRecords invr = new InvoiceRecords();
				invr.setVisible(true);
				}
			});
		
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.setBounds(317, 269, 192, 61);
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("HOME");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homescreen hom = new Homescreen();
				hom.setVisible(true);
				
			}
		});
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBounds(10, 16, 89, 23);
		panel.add(btnNewButton_2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search sch = new search();
				sch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				sch.setVisible(true);
				
				}
			});
		};
		


	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		main(null);
	}
}

/*

		try {
			String query = "select * from products";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e1){
			e1.printStackTrace();
		}

*/		