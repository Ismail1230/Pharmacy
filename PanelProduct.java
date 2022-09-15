import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelProduct extends JPanel {
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldSupplier;
	private JTextField textFieldBoxQty;
	private JTextField textFieldExpiry;
	private JTextField textFieldRPrice;
	private JTextField textFieldBouns;
	private JTextField textFieldQuantity;
	private JTextField textFieldWPrice;

	/**
	 * Create the panel.
	 */
	public PanelProduct() {
		setBackground(new Color(119, 136, 153));
		setBorder(new LineBorder(new Color(50, 205, 50), 3));
		
		setBounds(0, 0, 670, 630);
		setLayout(null);
		
		JLabel lblProductID = new JLabel("Product ID :");
		lblProductID.setFont(new Font("Arial", Font.BOLD, 12));
		lblProductID.setBounds(55, 100, 67, 20);
		add(lblProductID);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Arial", Font.BOLD, 12));
		lblName.setBounds(260, 98, 56, 20);
		add(lblName);
		
		JLabel lblSupplier = new JLabel("Supplier :");
		lblSupplier.setFont(new Font("Arial", Font.BOLD, 12));
		lblSupplier.setBounds(440, 102, 61, 20);
		add(lblSupplier);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Arial", Font.BOLD, 12));
		lblQuantity.setBounds(55, 160, 56, 20);
		add(lblQuantity);
		
		JLabel lblBouns = new JLabel("Bouns :");
		lblBouns.setFont(new Font("Arial", Font.BOLD, 12));
		lblBouns.setBounds(260, 160, 56, 20);
		add(lblBouns);
		
		JLabel lblBoxQty = new JLabel("Box Quantity :");
		lblBoxQty.setFont(new Font("Arial", Font.BOLD, 12));
		lblBoxQty.setBounds(440, 160, 81, 20);
		add(lblBoxQty);
		
		JLabel lblWPrice = new JLabel("W.Price :");
		lblWPrice.setFont(new Font("Arial", Font.BOLD, 12));
		lblWPrice.setBounds(55, 221, 56, 20);
		add(lblWPrice);
		
		JLabel lblRPrice = new JLabel("R.Price :");
		lblRPrice.setFont(new Font("Arial", Font.BOLD, 12));
		lblRPrice.setBounds(260, 221, 56, 20);
		add(lblRPrice);
		
		JLabel lblExpiry = new JLabel("Expiry Date :");
		lblExpiry.setFont(new Font("Arial", Font.BOLD, 12));
		lblExpiry.setBounds(440, 221, 81, 20);
		add(lblExpiry);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldID.setBounds(126, 100, 96, 19);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldName.setColumns(10);
		textFieldName.setBounds(305, 99, 96, 19);
		add(textFieldName);
		
		textFieldSupplier = new JTextField();
		textFieldSupplier.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldSupplier.setColumns(10);
		textFieldSupplier.setBounds(498, 99, 96, 19);
		add(textFieldSupplier);
		
		textFieldBoxQty = new JTextField();
		textFieldBoxQty.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldBoxQty.setColumns(10);
		textFieldBoxQty.setBounds(525, 160, 96, 19);
		add(textFieldBoxQty);
		
		textFieldExpiry = new JTextField();
		textFieldExpiry.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldExpiry.setColumns(10);
		textFieldExpiry.setBounds(520, 221, 96, 19);
		add(textFieldExpiry);
		
		textFieldRPrice = new JTextField();
		textFieldRPrice.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldRPrice.setColumns(10);
		textFieldRPrice.setBounds(309, 221, 96, 19);
		add(textFieldRPrice);
		
		textFieldBouns = new JTextField();
		textFieldBouns.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldBouns.setColumns(10);
		textFieldBouns.setBounds(305, 160, 96, 19);
		add(textFieldBouns);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(112, 160, 96, 19);
		add(textFieldQuantity);
		
		textFieldWPrice = new JTextField();
		textFieldWPrice.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldWPrice.setColumns(10);
		textFieldWPrice.setBounds(110, 221, 96, 19);
		add(textFieldWPrice);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddProduct.setBackground(new Color(50, 205, 50));
		btnAddProduct.setForeground(new Color(0, 0, 0));
		btnAddProduct.setFont(new Font("Arial", Font.PLAIN, 15));
		btnAddProduct.setBounds(260, 276, 134, 36);
		add(btnAddProduct);
		
		JPanel panelbackground1 = new JPanel();
		panelbackground1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(50, 205, 50), new Color(255, 0, 0)));
		panelbackground1.setBackground(new Color(176, 196, 222));
		panelbackground1.setBounds(29, 60, 610, 273);
		add(panelbackground1);
		panelbackground1.setLayout(null);
		
		
		

	}

}
