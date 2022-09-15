import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class ProductInv extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldSuppplier;
	private JTextField textFieldBonus;
	private JTextField textFieldWPrice;
	private JTextField textFieldExp;
	private JTextField textFieldName;
	private JTextField textFieldQty;
	private JTextField textFieldBoxQty;
	private JTextField textFieldRPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInv frame = new ProductInv();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;

	/**
	 * Create the frame.
	 */
	public ProductInv() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(109, 23, 116, 22);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldSuppplier = new JTextField();
		textFieldSuppplier.setColumns(10);
		textFieldSuppplier.setBounds(109, 73, 116, 22);
		contentPane.add(textFieldSuppplier);
		
		textFieldBonus = new JTextField();
		textFieldBonus.setColumns(10);
		textFieldBonus.setBounds(109, 130, 116, 22);
		contentPane.add(textFieldBonus);
		
		textFieldWPrice = new JTextField();
		textFieldWPrice.setColumns(10);
		textFieldWPrice.setBounds(109, 186, 116, 22);
		contentPane.add(textFieldWPrice);
		
		textFieldExp = new JTextField();
		textFieldExp.setColumns(10);
		textFieldExp.setBounds(109, 238, 116, 22);
		contentPane.add(textFieldExp);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(384, 23, 116, 22);
		contentPane.add(textFieldName);
		
		textFieldQty = new JTextField();
		textFieldQty.setColumns(10);
		textFieldQty.setBounds(384, 73, 116, 22);
		contentPane.add(textFieldQty);
		
		textFieldBoxQty = new JTextField();
		textFieldBoxQty.setColumns(10);
		textFieldBoxQty.setBounds(384, 130, 116, 22);
		contentPane.add(textFieldBoxQty);
		
		textFieldRPrice = new JTextField();
		textFieldRPrice.setColumns(10);
		textFieldRPrice.setBounds(384, 186, 116, 22);
		contentPane.add(textFieldRPrice);
		
		JLabel lblId = new JLabel("Product ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId.setBounds(12, 26, 85, 16);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(316, 26, 56, 16);
		contentPane.add(lblName);
		
		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSupplier.setBounds(30, 76, 56, 16);
		contentPane.add(lblSupplier);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantity.setBounds(304, 76, 56, 16);
		contentPane.add(lblQuantity);
		
		JLabel lblBonus = new JLabel("Bonus");
		lblBonus.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBonus.setBounds(41, 133, 56, 16);
		contentPane.add(lblBonus);
		
		JLabel lblBoxQuantity = new JLabel("Box Quantity");
		lblBoxQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBoxQuantity.setBounds(281, 133, 91, 16);
		contentPane.add(lblBoxQuantity);
		
		JLabel lblWprice = new JLabel("W.Price");
		lblWprice.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblWprice.setBounds(30, 189, 56, 16);
		contentPane.add(lblWprice);
		
		JLabel lblRprice = new JLabel("R.Price");
		lblRprice.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRprice.setBounds(316, 189, 56, 16);
		contentPane.add(lblRprice);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblExpiryDate.setBounds(12, 241, 85, 16);
		contentPane.add(lblExpiryDate);
		
		JButton btnInsert = new JButton("Insert Product");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveDB();
			}
		});
		btnInsert.setBounds(292, 237, 138, 25);
		contentPane.add(btnInsert);
	}

	
	private void saveDB() {
		try {
			Connection conn = connection.dbConnector();
			int Qty,Bonus,Stock,BoxQty;
			Qty = Integer.parseInt(textFieldQty.getText());
			Bonus = Integer.parseInt(textFieldBonus.getText());
			BoxQty = Integer.parseInt(textFieldBoxQty.getText());
			Stock = (Qty * BoxQty) + (Bonus * BoxQty);
			String Query = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(Query);
			ps.setString(1, textFieldID.getText());
			ps.setString(2, textFieldName.getText());
			ps.setString(3, textFieldSuppplier.getText());
			ps.setString(4, textFieldQty.getText());
			ps.setString(5, textFieldBonus.getText());
			ps.setString(6, textFieldBoxQty.getText());
			ps.setString(7, textFieldWPrice.getText());
			ps.setString(8, textFieldRPrice.getText());
			ps.setString(9, textFieldExp.getText());
			ps.setString(10, Integer.toString(Stock));
			
	
			ps.execute();
			
			JOptionPane.showMessageDialog(null, "Product  saved successfully");
			dispose();
				
			}catch (Exception e){
				System.out.println("Error: " +e);
			}
		}
	

}
