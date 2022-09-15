import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;


public class InvoiceRecords {

	private JFrame frmInvoiceRecords;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoiceRecords window = new InvoiceRecords();
					window.frmInvoiceRecords.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;
	protected Object DISPOSE_ON_CLOSE;
	private JTable table_1;

	/**
	 * Create the application.
	 */
	public InvoiceRecords() {
		initialize();
		conn = connection.dbConnector();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInvoiceRecords = new JFrame();
		frmInvoiceRecords.setIconImage(Toolkit.getDefaultToolkit().getImage(InvoiceRecords.class.getResource("/Images/Invoice.png")));
		frmInvoiceRecords.setTitle("Invoice Records");
		frmInvoiceRecords.getContentPane().setBackground(Color.WHITE);
		frmInvoiceRecords.setBounds(100, 100, 883, 476);
		frmInvoiceRecords.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInvoiceRecords.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.GREEN));
		scrollPane.setBounds(10, 231, 847, 168);
		frmInvoiceRecords.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Click to show records");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowData();
				try {
					String query = "select * from invoicerecords";
					PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		btnNewButton.setBounds(358, 13, 141, 23);
		frmInvoiceRecords.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Print");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String printdata = scrollPane.getUIClassID();
				PrinterJob job = PrinterJob.getPrinterJob();
				boolean doprint = job.printDialog();
				if(doprint) {
					try {
						
						job.print();
						
					}catch(PrinterException e2){
						
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewButton_1.setBounds(768, 410, 89, 23);
		frmInvoiceRecords.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 48, 843, 145);
		frmInvoiceRecords.getContentPane().add(scrollPane_1);
		scrollPane_1.getViewport().setBackground(new Color(255,255,255));
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
	}


	public void setVisible(boolean b) {
		main(null);
		
	}
	private void ShowData() {
		Connection conn = connection.dbConnector();
		DefaultTableModel model1 = new DefaultTableModel();
		model1.addColumn("Transaction ID");
		model1.addColumn("Name");
		model1.addColumn("Discount");
		model1.addColumn("Total");
		model1.addColumn("Date");
		model1.addColumn("Time");
	
		
		try {
			
			String query = "SELECT * FROM transaction";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				
				model1.addRow(new Object[] {
						rs.getString("ID"),
						rs.getString("Name"),
						rs.getString("Discount"),
						rs.getString("Total"),
						rs.getString("Date"),
						rs.getString("Time")
						
						
				});
			}
			rs.close();
			st.close();
			conn.close();
			
			table_1.setModel(model1);
			table_1.setAutoResizeMode(0);
			table_1.getColumnModel().getColumn(0).setPreferredWidth(100);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(160);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(160);
			table_1.getColumnModel().getColumn(4).setPreferredWidth(160);
			table_1.getColumnModel().getColumn(5).setPreferredWidth(160);
			
			
			
			
			
		} catch(Exception e1) {
			System.out.println("Error: " + e1);
		}
	}
}
