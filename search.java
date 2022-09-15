import java.awt.BorderLayout;
import java.sql.*;

//import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class search extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblShowTotal;
	private JLabel lblShowNetTotal;
	private JLabel lblDiscount;
	private double total;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					search frame = new search();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JTextField textFieldSearch;
	private JTable table_1;
	private JTable table_2;
	private JLabel lblSearch;
	private JScrollPane scrollPane_1;
	private JButton btnAdd;
	private JTextField textFieldQty;
	private JButton btnProduct;
	private JTextField textFieldDiscount;
	private JButton btnOk;
	private JButton btnNewButton;
	/**
	 * Create the frame.
	 */
	public search() {
		/*addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				showData();
				
				
			}
		})*/
		;
		conn = connection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,872,630);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setForeground(new Color(255, 255, 255));
		scrollPane.setBounds(12, 23, 709, 191);
		scrollPane.getViewport().setBackground(new Color(255,255,255));
		
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		table.setBackground(new Color(245, 222, 179));
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		scrollPane.setViewportView(table); 
		
		ArrayList<Item> product = new ArrayList<>();  
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String query = "SELECT * from product where Name = ? ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldSearch.getText() );
					
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					
				}catch (Exception Ex) {
					Ex.printStackTrace();
				}
				
				
			}
		});
		textFieldSearch.setBounds(115, 236, 130, 22);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		table_1 = new JTable();
		table_1.setBounds(148, 322, 1, 1);
		contentPane.add(table_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setForeground(new Color(255, 255, 255));
		scrollPane_1.setBounds(12, 282, 709, 126);
		scrollPane_1.getViewport().setBackground(new Color(255,255,255));
		contentPane.add(scrollPane_1);
		
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		table_2.setBackground(new Color(255, 255, 255));
		table_2.setShowGrid(false);
		table_2.setShowHorizontalLines(false);
		table_2.setShowVerticalLines(false);
		
		lblSearch = new JLabel("Name :");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearch.setBounds(49, 218, 54, 56);
		contentPane.add(lblSearch);
		
		textFieldQty = new JTextField();
		textFieldQty.setBounds(396, 236, 116, 22);
		contentPane.add(textFieldQty);
		textFieldQty.setColumns(10);
		
		JLabel lblQty = new JLabel("Qty :");
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQty.setBounds(346, 237, 38, 16);
		contentPane.add(lblQty);
		
		
		btnAdd = new JButton("add");
		btnAdd.setBounds(622, 234, 97, 25);
		contentPane.add(btnAdd);
		
		btnProduct = new JButton("New Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductInv product = new ProductInv();
				product.setVisible(true);
				showData();
				
			}
		});
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to save and return to homepage ?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					saveDB();
				try{

					  int rows=table_2.getRowCount();
					  
					  for(int row = 0; row<rows; row++)
					  {   
					    Integer Qty = (Integer)table_2.getValueAt(row, 0);
					    String Name = (String) table_2.getValueAt(row, 1);
					    Double uPrice = (Double)table_2.getValueAt(row, 2);
					    Double ItemTotal = (Double)table_2.getValueAt(row, 3);
					    
					    String queryco = "INSERT INTO `invoicerecords`(`Qty`, `Name`, `UPrice`, `Item Total`) VALUES ('"+Qty+"','"+Name+"','"+uPrice+"','"+ItemTotal+"')";

					    PreparedStatement pst = conn.prepareStatement(queryco);
					    pst.execute();     
					  }
					  
					}
					catch(SQLException exe){
						exe.printStackTrace();
					}
				
				Homescreen hom = new Homescreen();
				hom.setVisible(true);
				search.this.dispose();
			}
			
			}

			private void saveDB() {
				Connection conn = connection.dbConnector();
				double discount,total;
				try {
					java.util.Date date=new java.util.Date();
					
					java.sql.Date sqlDate=new java.sql.Date(date.getTime());
					java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
					discount = Double.parseDouble(textFieldDiscount.getText());
					total = Double.parseDouble(lblShowNetTotal.getText());
				
					String Query = "INSERT INTO transaction(Discount,Total,Date,Time) VALUES(?,?,?,?)";
					
					PreparedStatement ps = conn.prepareStatement(Query);
					
					ps.setString(1, textFieldDiscount.getText());
					ps.setString(2, lblShowNetTotal.getText());
					ps.setDate(3,sqlDate);
					ps.setTimestamp(4,sqlTime);

					
					
					
					
			
					ps.execute();
					
					
					JOptionPane.showMessageDialog(null, "Product  updated successfully");
					
				}catch (Exception e){
					System.out.println("Error: " +e);
				}
				
				
			}

			
			
		});
		btnNewButton_1.setBounds(115, 502, 89, 23);
		contentPane.add(btnNewButton_1);
		btnProduct.setBounds(733, 94, 110, 37);
		contentPane.add(btnProduct);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal.setBounds(581, 437, 56, 16);
		contentPane.add(lblTotal);
		
		lblDiscount = new JLabel("Discount");
		lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDiscount.setBounds(562, 464, 56, 16);
		contentPane.add(lblDiscount);
		
		lblShowTotal = new JLabel("");
		lblShowTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblShowTotal.setBounds(642, 437, 121, 16);
		contentPane.add(lblShowTotal);
		
		textFieldDiscount = new JTextField();
		textFieldDiscount = new JTextField("0.0");
		textFieldDiscount.setFont(new Font("Tahoma", Font.BOLD, 13));
		textFieldDiscount.setBounds(647, 463, 116, 22);
		contentPane.add(textFieldDiscount);
		textFieldDiscount.setColumns(10);
		
		JLabel lblNetTotal = new JLabel("Net Total");
		lblNetTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNetTotal.setBounds(560, 505, 73, 16);
		contentPane.add(lblNetTotal);
		
		lblShowNetTotal = new JLabel("");
		lblShowNetTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblShowNetTotal.setBounds(642, 505, 121, 16);
		contentPane.add(lblShowNetTotal);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showNetTotal(total);
			}
		});
		btnOk.setBounds(49, 433, 97, 25);
		contentPane.add(btnOk);
		
		btnNewButton = new JButton("Cancel");
		btnNewButton.setBounds(201, 433, 97, 25);
		contentPane.add(btnNewButton);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int Quantity; //To store the value in the textbox
				double UPrice;
				try {
					Quantity = Integer.parseInt(textFieldQty.getText());
					String query = "SELECT * from product where Name = ? ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldSearch.getText() );
					
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						Item item = new Item();
						
						item.setQty(Quantity);
						item.setName(rs.getString("Name"));
						item.setRPrice(rs.getDouble("RPrice"));
						item.setBoxQty(rs.getInt("BoxQty"));
						
						product.add(item);
				
						
					}
					
					
					
					pst.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				showData();
				textFieldSearch.setText("");
				textFieldQty.setText("");
				ArrayList <Object[]> inv = new ArrayList<Object[]>();
				total =0;
				for (int i =0;i<product.size();i++) {
					inv.add(new Object[] {
							product.get(i).getQuantity(),
							product.get(i).getName(),
							product.get(i).getUPrice(),
							product.get(i).getPrice()
					});
					total += product.get(i).getPrice();
					showTotal (total);
					//showNetTotal(total);
				}
				
				
				
				table_2.setModel(new DefaultTableModel(inv.toArray(new Object[][] {}),
						new String[] {"Qty", "Name", "UPrice", "Item Total"}));
				
				
			}
			
			
		});
		
	}
	
	private void showTotal(double total) { //method to show total
		//Integer.parseInt
		String totalStr = Double.toString(total); //converting a double to string 
		lblShowTotal.setText(totalStr); 
		
	}
	
	private void showNetTotal(double total) {
		double discount;
		double netTotal;
		String netTotalStr;
		discount = Double.parseDouble(textFieldDiscount.getText());
		netTotal = total - discount;
		netTotalStr = Double.toString(netTotal);
		lblShowNetTotal.setText(netTotalStr); 
		
		
	}
	private void showData() { //method to show current contents of database 
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
			while (rs.next()) { //getting each row from database
				
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
			table.getColumnModel().getColumn(0).setPreferredWidth(50); //setting width of each individual columns
			table.getColumnModel().getColumn(1).setPreferredWidth(120);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(50);
			table.getColumnModel().getColumn(4).setPreferredWidth(50);
			table.getColumnModel().getColumn(5).setPreferredWidth(60);
			table.getColumnModel().getColumn(6).setPreferredWidth(60);
			table.getColumnModel().getColumn(7).setPreferredWidth(60);
			table.getColumnModel().getColumn(8).setPreferredWidth(60);
			table.getColumnModel().getColumn(9).setPreferredWidth(60);
			
			
			
			
		} catch(Exception e1) {
			System.out.println("Error: " + e1);
		}
	}
}
