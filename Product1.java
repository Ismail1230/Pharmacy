import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Product1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product1 frame = new Product1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JTextField textFieldName;
	private JTextField textFieldBonus;
	private JTextField textFieldRPrice;
	private JTextField textFieldSupplier;
	private JTextField textFieldBoxQty;
	private JTextField textFieldExpiry;
	private JTextField textFieldQuantity;
	private JTextField textFieldWPrice;
	private JTextField textFieldID;
	private JLabel lblProductId;
	private JTable table;
	private JButton btnDelete;
	private JTextField textFieldDelete;
	private JLabel lblSelectAProduct;
	private JLabel lblToUpdateIt;
	

	/**
	 * Create the frame.
	 */
	public Product1() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				ShowData();
			}
		});
		conn = connection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(304, 43, 56, 16);
		contentPane.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(356, 40, 116, 22);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblBonus = new JLabel("Bonus");
		lblBonus.setBounds(304, 93, 56, 16);
		contentPane.add(lblBonus);
		
		JLabel lblRPrice = new JLabel("R.Price");
		lblRPrice.setBounds(304, 143, 74, 16);
		contentPane.add(lblRPrice);
		
		textFieldBonus = new JTextField();
		textFieldBonus.setBounds(356, 90, 116, 22);
		contentPane.add(textFieldBonus);
		textFieldBonus.setColumns(10);
		
		textFieldRPrice = new JTextField();
		textFieldRPrice.setBounds(356, 140, 116, 22);
		contentPane.add(textFieldRPrice);
		textFieldRPrice.setColumns(10);
		
		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setBounds(578, 43, 56, 16);
		contentPane.add(lblSupplier);
		
		JLabel lblBoxQty = new JLabel("Box Quantity");
		lblBoxQty.setBounds(560, 93, 74, 16);
		contentPane.add(lblBoxQty);
		
		JLabel lblExpiry = new JLabel("Expiry Date");
		lblExpiry.setBounds(560, 143, 74, 16);
		contentPane.add(lblExpiry);
		
		textFieldSupplier = new JTextField();
		textFieldSupplier.setBounds(646, 40, 116, 22);
		contentPane.add(textFieldSupplier);
		textFieldSupplier.setColumns(10);
		
		textFieldBoxQty = new JTextField();
		textFieldBoxQty.setBounds(646, 90, 116, 22);
		contentPane.add(textFieldBoxQty);
		textFieldBoxQty.setColumns(10);
		
		textFieldExpiry = new JTextField();
		textFieldExpiry.setBounds(646, 140, 116, 22);
		contentPane.add(textFieldExpiry);
		textFieldExpiry.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(24, 93, 56, 16);
		contentPane.add(lblQuantity);
		
		JLabel lblWPrice = new JLabel("W.Price");
		lblWPrice.setBounds(24, 143, 56, 16);
		contentPane.add(lblWPrice);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(92, 90, 116, 22);
		contentPane.add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		textFieldWPrice = new JTextField();
		textFieldWPrice.setBounds(92, 140, 116, 22);
		contentPane.add(textFieldWPrice);
		textFieldWPrice.setColumns(10);
		
		JButton btnAddProduct = new JButton("Add product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveToDatabase();
				ShowData();
			}
		});
		btnAddProduct.setBackground(new Color(255, 255, 255));
		btnAddProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddProduct.setForeground(Color.BLACK);
		btnAddProduct.setBounds(24, 211, 128, 25);
		contentPane.add(btnAddProduct);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(92, 40, 116, 22);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(12, 43, 68, 16);
		contentPane.add(lblProductId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(199, 188, 563, 289);
		contentPane.add(scrollPane);
		scrollPane.getViewport().setBackground(new Color(255,255,255));
		
		table = new JTable();
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DisplayFromTable();
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateDatabase();
				ShowData();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBounds(24, 294, 128, 25);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteData();
				ShowData();
			}
		});
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(24, 423, 128, 25);
		contentPane.add(btnDelete);
		
		textFieldDelete = new JTextField();
		textFieldDelete.setBounds(24, 379, 128, 22);
		contentPane.add(textFieldDelete);
		textFieldDelete.setColumns(10);
		
		JLabel lblEnterIdOf = new JLabel("Enter ID of product");
		lblEnterIdOf.setBounds(24, 334, 118, 16);
		contentPane.add(lblEnterIdOf);
		
		JLabel lblToBeDeleted = new JLabel("   to be deleted");
		lblToBeDeleted.setBounds(14, 350, 128, 16);
		contentPane.add(lblToBeDeleted);
		
		lblSelectAProduct = new JLabel("Select a product");
		lblSelectAProduct.setBounds(24, 249, 151, 16);
		contentPane.add(lblSelectAProduct);
		
		lblToUpdateIt = new JLabel("to update it");
		lblToUpdateIt.setBounds(24, 265, 74, 16);
		contentPane.add(lblToUpdateIt);
	
	}
	
	
	private void saveToDatabase() {
		Connection conn = connection.dbConnector();
		int Qty,Bonus,Stock,BoxQty;
		try {
			Qty = Integer.parseInt(textFieldQuantity.getText());
			Bonus = Integer.parseInt(textFieldBonus.getText());
			BoxQty = Integer.parseInt(textFieldBoxQty.getText());
			Stock = (Qty * BoxQty) + (Bonus * BoxQty);
			String Query = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(Query);
			ps.setString(1, textFieldID.getText());
			ps.setString(2, textFieldName.getText());
			ps.setString(3, textFieldSupplier.getText());
			ps.setString(4, textFieldQuantity.getText());
			ps.setString(5, textFieldBonus.getText());
			ps.setString(6, textFieldBoxQty.getText());
			ps.setString(7, textFieldWPrice.getText());
			ps.setString(8, textFieldRPrice.getText());
			ps.setString(9, textFieldExpiry.getText());
			ps.setString(10, Integer.toString(Stock));
			
	
			ps.execute();
			
			JOptionPane.showMessageDialog(null, "Product  saved successfully");
			
		}catch (Exception e){
			System.out.println("Error: " +e);
		}
	}
	
	private void ShowData() {
		Connection conn = connection.dbConnector();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Supplier");
		model.addColumn("Qty");
		model.addColumn("Bonus");
		model.addColumn("BoxQty");
		model.addColumn("W.Price");
		model.addColumn("R.Price");
		model.addColumn("Expiry");
		model.addColumn("Stock");
		
		try {
			
			String query = "SELECT * FROM product";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				
				model.addRow(new Object[] {
						rs.getString("ItemNO"),
						rs.getString("Name"),
						rs.getString("Supplier"),
						rs.getString("QtyBought"),
						rs.getString("Bonus"),
						rs.getString("BoxQty"),
						rs.getString("WPrice"),
						rs.getString("RPrice"),
						rs.getString("Exp"),
						rs.getString("Stock"),
						
						
				});
			}
			rs.close();
			st.close();
			conn.close();
			
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(40);
			table.getColumnModel().getColumn(4).setPreferredWidth(40);
			table.getColumnModel().getColumn(5).setPreferredWidth(40);
			table.getColumnModel().getColumn(6).setPreferredWidth(55);
			table.getColumnModel().getColumn(7).setPreferredWidth(55);
			table.getColumnModel().getColumn(8).setPreferredWidth(40);
			table.getColumnModel().getColumn(9).setPreferredWidth(50);
			
			
			
			
		} catch(Exception e1) {
			System.out.println("Error: " + e1);
		}
	}
	private void UpdateDatabase() {
		Connection conn = connection.dbConnector();
		int Qty,Bonus,Stock,BoxQty;
		try {
			Qty = Integer.parseInt(textFieldQuantity.getText());
			Bonus = Integer.parseInt(textFieldBonus.getText());
			BoxQty = Integer.parseInt(textFieldBoxQty.getText());
			Stock = (Qty * BoxQty) + (Bonus * BoxQty);
			String Query = "UPDATE product SET Name=?,Supplier=?,QtyBought=?,Bonus=?,BoxQty=?,WPrice=?,RPrice=?,Exp=?,Stock=? WHERE ItemNO =? ";
			
			PreparedStatement ps = conn.prepareStatement(Query);
			
			ps.setString(1, textFieldName.getText());
			ps.setString(2, textFieldSupplier.getText());
			ps.setString(3, textFieldQuantity.getText());
			ps.setString(4, textFieldBonus.getText());
			ps.setString(5, textFieldBoxQty.getText());
			ps.setString(6, textFieldWPrice.getText());
			ps.setString(7, textFieldRPrice.getText());
			ps.setString(8, textFieldExpiry.getText());
			ps.setString(9, Integer.toString(Stock));
			ps.setString(10, textFieldID.getText());

			
			
			
			
	
			ps.execute();
			
			
			JOptionPane.showMessageDialog(null, "Product  updated successfully");
			
		}catch (Exception e){
			System.out.println("Error: " +e);
		}
	}
	private void DeleteData() {
		try {
			
			String Query = "DELETE FROM product WHERE ItemNO = '"+textFieldDelete.getText()+" ' ";
			PreparedStatement ps = conn.prepareStatement(Query);
			
			ps.execute();
			
			
			JOptionPane.showMessageDialog(null, "Product Deleted");
			
		}catch (Exception e){
			System.out.println("Error: " +e);
		}
	}
	private void DisplayFromTable() {
		try {
			int row = table.getSelectedRow();
			String PID = (table.getModel().getValueAt(row, 0)).toString();
			
			String query = "SELECT * FROM product WHERE ItemNO='" +PID+ "' ";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery(query);
			while (rs.next()) {
				textFieldID.setText(rs.getString("ItemNO"));
				textFieldName.setText(rs.getString("Name"));
				textFieldSupplier.setText(rs.getString("Supplier"));
				textFieldQuantity.setText(rs.getString("QtyBought"));
				textFieldBonus.setText(rs.getString("Bonus"));
				textFieldBoxQty.setText(rs.getString("BoxQty"));
				textFieldWPrice.setText(rs.getString("WPrice"));
				textFieldRPrice.setText(rs.getString("RPrice"));
				textFieldExpiry.setText(rs.getString("Exp"));
				
			}
			ps.close();
			
			
		} catch(Exception e1) {
			System.out.println("Error: " + e1);
		}
		
	}
}
