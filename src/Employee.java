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
public Employee() {
	initialize();

	conn = EmployeeData.ConnectDB();
	Object col[] = {"EmpID", "NINumber","Firstname","Surname","Gender","DOB","Age","Salary"};
	model.setColumnIdentifiers(col);
}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(150, 103, 141, 26);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Add new");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql ="INSERT INTO employee(EmpID,NINumber,Firstname,Surname,Gender,DOB,Age,Salary)VALUES(?,?,?,?,?,?,?,?)";

				try