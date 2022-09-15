import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;

public class stock extends JFrame {

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					stock frame = new stock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JPanel contentPane;
	private JTextField textFieldsearch;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public stock() {
		conn = connection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(490, 125, 800, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); 
		contentPane.setLayout(null);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.setFont(new Font("Arial", Font.BOLD, 13));
		btnCustomer.setBounds(25, 22, 106, 21);
		contentPane.add(btnCustomer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 110, 700, 230);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		JLabel lblSearch = new JLabel("Search :");
		lblSearch.setFont(new Font("Arial", Font.BOLD, 14));
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setBounds(38, 65, 93, 28);
		contentPane.add(lblSearch);
		
		JButton btnProduct = new JButton("Product");
		btnProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Product1 product = new Product1();
				product.setVisible(true);
			}
		});
		btnProduct.setFont(new Font("Arial", Font.BOLD, 13));
		btnProduct.setBounds(141, 22, 85, 21);
		contentPane.add(btnProduct);
		
		JButton btnInvoice = new JButton("Invoice");
		btnInvoice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				search srch = new search();
				srch.setVisible(true);
			}
		});
		btnInvoice.setFont(new Font("Arial", Font.BOLD, 13));
		btnInvoice.setBounds(236, 22, 85, 21);
		contentPane.add(btnInvoice);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Arial", Font.BOLD, 13));
		btnInsert.setBounds(50, 360, 85, 21);
		contentPane.add(btnInsert);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Arial", Font.BOLD, 13));
		btnSelect.setBounds(189, 360, 85, 21);
		contentPane.add(btnSelect);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stock.this.dispose();
			}
		});
		btnClose.setFont(new Font("Arial", Font.BOLD, 13));
		btnClose.setBounds(569, 360, 85, 21);
		contentPane.add(btnClose);
		
		textFieldsearch = new JTextField();
		textFieldsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try {
					
					String query = "SELECT ItemNO,Name,Supplier,QtyBought,RPrice/BoxQty,EXP FROM product";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		textFieldsearch.setBounds(115, 65, 240, 25);
		contentPane.add(textFieldsearch);
		textFieldsearch.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(25, 53, 736, 350);
		contentPane.add(panel);
		panel.setLayout(null);
	}
}
