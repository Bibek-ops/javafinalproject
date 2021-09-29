package employeeData;

import java.sql.*;
import javax.swing.*;

public class EmployeeData {
	
	public static Connection ConnectDB()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:F:\\java_projects\\employeeData\\employee.db");
					JOptionPane.showMessageDialog(null,"connection Made");
					return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"connection Error");
			return null;
		}
	}
}
