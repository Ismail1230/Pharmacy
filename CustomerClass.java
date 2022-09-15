

import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import javax.swing.AbstractButton;
import javax.swing.DropMode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerClass {

	private JFrame frame;
	private JTextField surname;
	private JTextField name;
	private JTextField address;
	private JTextField medical_issues;
	private JTextField id;
	protected AbstractButton sex;
	private JTextField sexx;
	private JTextField date_of_birth;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerClass window = new CustomerClass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JTextField textFieldPhone;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Create the application.
	 */
	public CustomerClass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().setBackground(new Color(211, 211, 211));
		frame.setBounds(100, 100, 526, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		surname = new JTextField();
		surname.setDropMode(DropMode.INSERT);
		surname.setBounds(180, 51, 172, 22);
		frame.getContentPane().add(surname);
		surname.setColumns(10);
		
		name = new JTextField();
		name.setDropMode(DropMode.INSERT);
		name.setBounds(180, 86, 172, 22);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setDropMode(DropMode.INSERT);
		address.setBounds(180, 121, 172, 22);
		frame.getContentPane().add(address);
		address.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Surname");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setBounds(35, 53, 99, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(35, 88, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(35, 123, 82, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone Number");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(34, 158, 147, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sex");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(35, 187, 56, 22);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblMedicalIssues = new JLabel("Medical Issues");
		lblMedicalIssues.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMedicalIssues.setBounds(34, 273, 147, 16);
		frame.getContentPane().add(lblMedicalIssues);
		
		medical_issues = new JTextField();
		medical_issues.setDropMode(DropMode.INSERT);
		medical_issues.setBounds(180, 257, 172, 48);
		frame.getContentPane().add(medical_issues);
		medical_issues.setColumns(10);
		
		JButton insertButton = new JButton("INSERT");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			try {
				conn = connection.dbConnector();

				String sql ="insert into customer values(?, ?, ?, ?, ?, ?, ?, ?)";
		        PreparedStatement pstmt =conn.prepareStatement(sql);
		        pstmt.setString(1, id.getText()) ;
		        pstmt.setString(2, surname.getText()) ;
		        pstmt.setString(3, name.getText()) ;
		        pstmt.setString(4, address.getText()) ;
		        pstmt.setString(5, textFieldPhone.getText()) ;
		        pstmt.setString(7, date_of_birth.getText()) ;
		        pstmt.setString(6, sexx.getText()) ;
		        pstmt.setString(8, medical_issues.getText()) ;
		        
		        pstmt.execute();
		        JOptionPane.showMessageDialog(null, "Inserted successfully");
				
				
			}catch(Exception e1) {
				 System.out.print(e1);
				}
			}
		});
		insertButton.setBounds(335, 311, 82, 25);
		frame.getContentPane().add(insertButton);
		
		JLabel lblNewLabel_9 = new JLabel("ID");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_9.setBounds(35, 18, 56, 16);
		frame.getContentPane().add(lblNewLabel_9);
		
		id = new JTextField();
		id.setBounds(180, 16, 172, 22);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		sexx = new JTextField();
		sexx.setBounds(180, 187, 172, 22);
		frame.getContentPane().add(sexx);
		sexx.setColumns(10);
		
		date_of_birth = new JTextField();
		date_of_birth.setText("");
		date_of_birth.setBounds(180, 224, 172, 22);
		frame.getContentPane().add(date_of_birth);
		date_of_birth.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDateOfBirth.setBounds(35, 226, 120, 16);
		frame.getContentPane().add(lblDateOfBirth);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(180, 156, 172, 22);
		frame.getContentPane().add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		btnNewButton = new JButton("DELETE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String Query = "DELETE FROM customer WHERE ID = '"+id.getText()+" ' ";
					PreparedStatement ps = conn.prepareStatement(Query);
					
					

					ps.execute();
					
					
					JOptionPane.showMessageDialog(null, "Record Deleted");
					
				}catch (Exception ex){
					System.out.println("Error: " +ex);
				}
			}
		});
		btnNewButton.setBounds(211, 311, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = connection.dbConnector();
					String value1= id.getText() ;
					String value2= surname.getText() ;
	            	String value3= name.getText() ;
	            	String value4= address.getText() ;
	            	String value5= textFieldPhone.getText() ;
	            	String value7= date_of_birth.getText() ;
	            	String value6= sexx.getText() ;
			        String value8= medical_issues.getText() ;
					String sql ="Update customer SET Surname =?,Name =?, Address = ?,Number=?,Sex=?,DOB=?,MedicalIssues=? WHERE ID = ? "; 
			       
					PreparedStatement ps = conn.prepareStatement(sql);
					
					ps.setString(1, value2);
					ps.setString(2, value3);
					ps.setString(3, value4);
					ps.setString(4, value5);
					ps.setString(5, value6);
					ps.setString(6, value7);
					ps.setString(7, value8);
					ps.setString(8, value1);
					
			        
			        
					ps.execute();
			        JOptionPane.showMessageDialog(null, "UPDATED successfully");
			        ps.close();
					
				}catch(Exception e1) {
					 System.out.print(e1);
				}
			}
		});
		btnNewButton_1.setBounds(90, 311, 97, 25);
		frame.getContentPane().add(btnNewButton_1);
		//frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{surname, name, address}));
	}
}
