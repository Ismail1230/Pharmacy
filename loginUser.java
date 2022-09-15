import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
public class loginUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUN;
	private JPasswordField passwordField;
	private JLabel lblPassword;
	private JLabel lblLogin;
	private JLabel lblUsername;
	private JLabel lblLogin_1;
	private JLabel lblX;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginUser frame = new loginUser();
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
	public loginUser() {
		
		
		conn = connection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUN = new JTextField();
		textFieldUN.setBounds(251, 193, 278, 37);
		textFieldUN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(textFieldUN);
		textFieldUN.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(251, 271, 278, 37);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(passwordField);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.setBounds(408, 349, 118, 31);
		btnClose.setForeground(new Color(255, 255, 255));
		btnClose.setBackground(new Color(255, 0, 0));
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		/*btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});*/
		btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClose.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnClose);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(251, 349, 118, 28);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(135, 206, 235));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				PreparedStatement pst = null;
				ResultSet rs = null;
				try {
					String query = "select * from users where Username =? and password=? ";
					pst = conn.prepareStatement(query); //parsing query to prepared statement
					pst.setString(1,textFieldUN.getText() );
					pst.setString(2,passwordField.getText() );
					
					rs = pst.executeQuery(); //execute query
					
					int count =0;
					while (rs.next()) {
						count++;
					}
					if (count ==1) { //if only 1 result is obtained
						JOptionPane.showMessageDialog(null, "Username and password is correct");
						dispose();
						Homescreen home = new Homescreen();
						home.setVisible(true);
					}
					else { //if no result
						JOptionPane.showMessageDialog(null, "Username and password is incorrect");
					}
					
				
				rs.close();
				pst.close();
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null, E);
					
				}
				
			}
		});
		contentPane.add(btnLogin);
		lblUsername = new JLabel("");
		lblUsername.setBounds(176, 182, 61, 57);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setIcon(new ImageIcon(loginUser.class.getResource("Images/user.png")));
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("");
		lblPassword.setBounds(176, 263, 61, 57);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setIcon(new ImageIcon(loginUser.class.getResource("Images/password.png")));
		contentPane.add(lblPassword);
		
		lblLogin = new JLabel("");
		lblLogin.setBounds(232, 115, 126, 65);
		lblLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setIcon(new ImageIcon(loginUser.class.getResource("Images/pill.png")));
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblLogin);
		
		lblLogin_1 = new JLabel("LOGIN");
		lblLogin_1.setBounds(356, 141, 112, 45);
		lblLogin_1.setForeground(new Color(255, 255, 255));
		lblLogin_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblLogin_1);
		
		lblX = new JLabel("X");
		lblX.setBounds(744, 13, 56, 16);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblX.setForeground(new Color(255, 255, 255));
		contentPane.add(lblX);
		
		
		setUndecorated(true); //To remove the frame outline
	}

}
