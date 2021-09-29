import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import employeeData.EmployeeData;

import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class Employee {

	private JFrame frame;
	private JTextField jtxtEmployeeID;
	private JTable table;
	private JTextField jtxtNINumber;
	private JTextField jtxtFirstname;
	private JTextField jtxtSurname;
	private JTextField jtxtGender;
	private JTextField jtxtDOB;
	private JTextField jtxtAge;
	private JTextField jtxtSalary;

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;


	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */

	public void updateTable()
	{

		conn = EmployeeData.ConnectDB();
		if (conn !=null)
		{
			String sql =" Select EmpID, NINumber,Firstname,Surname,Gender,DOB,Age,Salary";

			try
			{
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				Object[]columnData = new Object[8];

				while(rs.next()) {
					columnData [0] = rs.getString("EmpID");
					columnData [1] = rs.getString("NINumber");
					columnData [2] = rs.getString("Firstname");
					columnData [3] = rs.getString("Surname");
					columnData [4] = rs.getString("Gender");
					columnData [5] = rs.getString("DOB");
					columnData [6] = rs.getString("Age");
					columnData [7] = rs.getString("Salary");

					model.addRow(columnData);
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e);
			}
		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

/**
 * Create the application.
 */