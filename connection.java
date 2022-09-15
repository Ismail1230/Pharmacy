import java.sql.*;
import javax.swing.*;

public class connection {
	Connection conn =null;
	
	public static Connection dbConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy","root","");
			//JOptionPane.showMessageDialog(null, "Connection successful");
			return conn;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
			
		}
	}

}
