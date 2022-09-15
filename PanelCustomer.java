import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelCustomer extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelCustomer() {
		
		setBounds(0, 0, 670, 630);
		setLayout(null);
		
		JLabel labelThisiscustomer = new JLabel("Customer");
		labelThisiscustomer.setHorizontalAlignment(SwingConstants.CENTER);
		labelThisiscustomer.setFont(new Font("Tahoma", Font.PLAIN, 48));
		labelThisiscustomer.setBounds(72, 173, 501, 219);
		add(labelThisiscustomer);

	}

}
