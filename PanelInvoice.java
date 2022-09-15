import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class PanelInvoice extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	Connection conn = null;
	private JTextField textFieldSearch;
	private JTextField textFieldQty;
	private JTable table_2;
	private JTable table_1;
	
	public PanelInvoice() {
		setForeground(new Color(255, 255, 255));
		setBorder(new LineBorder(new Color(255, 250, 250), 4));
		setBackground(SystemColor.scrollbar);
		conn = connection.dbConnector();
		setBounds(0, 0, 670, 630);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();		
		scrollPane.setBounds(45, 75, 575, 195);		
		scrollPane.setToolTipText("");
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setForeground(new Color(255, 255, 255));
		scrollPane.getViewport().setBackground(new Color(255,255,255));
		add(scrollPane);
		
		ArrayList<Item> product = new ArrayList<>(); 
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		JLabel lblinvoice = new JLabel("INVOICE");
		lblinvoice.setFont(new Font("Arial", Font.BOLD, 18));
		lblinvoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblinvoice.setBounds(230, 25, 203, 49);
		add(lblinvoice);
		
		JLabel lblname = new JLabel("NAME :");
		lblname.setFont(new Font("Arial", Font.BOLD, 13));
		lblname.setHorizontalAlignment(SwingConstants.LEFT);
		lblname.setBounds(68, 315, 69, 25);
		add(lblname);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(123, 315, 120, 25);
		
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try {
					
					String query = "SELECT Name,Supplier,QtyBought,Bonus,BoxQty,RPrice,RPrice/BoxQty As UnitPrice FROM product";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		textFieldSearch.setColumns(10);
		add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblQty = new JLabel("  QTY :");
		lblQty.setFont(new Font("Arial", Font.BOLD, 13));
		lblQty.setHorizontalAlignment(SwingConstants.LEFT);
		lblQty.setBounds(275, 315, 60, 25);
		add(lblQty);
		
		textFieldQty = new JTextField();
		textFieldQty.setBounds(326, 315, 120, 25);
		add(textFieldQty);
		textFieldQty.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(490, 315, 85, 21);
		add(btnAdd);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 386, 575, 188);
		add(scrollPane_1);
		
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		table_2.setBackground(new Color(255, 255, 255));
		table_2.setShowGrid(false);
		table_2.setShowHorizontalLines(false);
		table_2.setShowVerticalLines(false);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollPane_1.setRowHeaderView(scrollBar_1);
		
		JPanel panelbackgroundcolour = new JPanel();
		panelbackgroundcolour.setBackground(SystemColor.controlShadow);
		panelbackgroundcolour.setBounds(26, 25, 623, 583);
		add(panelbackgroundcolour);
		panelbackgroundcolour.setLayout(null);
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
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				ArrayList <Object[]> inv = new ArrayList<Object[]>();
				for (int i =0;i<product.size();i++) {
					inv.add(new Object[] {
							product.get(i).getQuantity(),
							product.get(i).getName(),
							product.get(i).getUPrice(),
							product.get(i).getPrice()
							
							
							
					});
				}
				table_2.setModel(new DefaultTableModel(inv.toArray(new Object[][] {}),
						new String[] {"Qty", "Name", "UPrice", "Item Total"}));
				
			
		
		table_1 = new JTable();
		table_1.setBounds(0, 0, 1, 1);
		add(table_1);
		
		

			}
			
		});
	}
}
