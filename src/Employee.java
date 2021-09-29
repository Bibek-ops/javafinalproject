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
				{
					pst = conn.prepareStatement(sql);
					pst.setString(1, jtxtEmployeeID.getText());
					pst.setString(2, jtxtNINumber.getText());
					pst.setString(3, jtxtFirstname.getText());
					pst.setString(4, jtxtSurname.getText());
					pst.setString(5, jtxtGender.getText());
					pst.setString(6, jtxtDOB.getText());
					pst.setString(7, jtxtAge.getText());
					pst.setString(8, jtxtSalary.getText());

					pst.execute();

					rs.close();
					pst.close();
				}
				catch(Exception ev)
				{
					JOptionPane.showMessageDialog(null, "System Update COmpleted");
				}

				DefaultTableModel model =(DefaultTableModel) table.getModel();
				model .addRow(new Object[] {

						jtxtEmployeeID.getText(),
						jtxtNINumber.getText(),
						jtxtFirstname.getText(),
						jtxtSurname.getText(),
						jtxtGender.getText(),
						jtxtDOB.getText(),
						jtxtAge.getText(),
						jtxtSalary.getText(),

				});
				if(table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Membership UPdate confirmed","Employee Database System",
								JOptionPane.OK_OPTION);
					}

				}
			}

		});


		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(115, 471, 164, 39);
		frame.getContentPane().add(btnNewButton);

		jtxtEmployeeID = new JTextField();
		jtxtEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtEmployeeID.setBounds(343, 99, 219, 34);
		frame.getContentPane().add(jtxtEmployeeID);
		jtxtEmployeeID.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(681, 31, 686, 350);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{"", null, null, null, null, null, null, null},
				},
				new String[] {
						"EmpID", "NINumber", "Firstname", "Surname", "Gender", "DOB", "Age", "Salary"
				}
		));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);

		JLabel lblNinumber = new JLabel("NINumber");
		lblNinumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNinumber.setBounds(150, 139, 141, 26);
		frame.getContentPane().add(lblNinumber);

		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFirstname.setBounds(150, 175, 141, 26);
		frame.getContentPane().add(lblFirstname);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSurname.setBounds(150, 211, 141, 26);
		frame.getContentPane().add(lblSurname);

		JLabel lblSurname_1 = new JLabel("Gender");
		lblSurname_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSurname_1.setBounds(150, 247, 141, 26);
		frame.getContentPane().add(lblSurname_1);

		JLabel lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDob.setBounds(150, 283, 141, 26);
		frame.getContentPane().add(lblDob);

		JLabel Age = new JLabel("Age");
		Age.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Age.setBounds(150, 319, 141, 26);
		frame.getContentPane().add(Age);

		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSalary.setBounds(150, 355, 141, 26);
		frame.getContentPane().add(lblSalary);

		jtxtNINumber = new JTextField();
		jtxtNINumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtNINumber.setColumns(10);
		jtxtNINumber.setBounds(343, 135, 219, 34);
		frame.getContentPane().add(jtxtNINumber);

		jtxtFirstname = new JTextField();
		jtxtFirstname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtFirstname.setColumns(10);
		jtxtFirstname.setBounds(343, 171, 219, 34);
		frame.getContentPane().add(jtxtFirstname);

		jtxtSurname = new JTextField();
		jtxtSurname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtSurname.setColumns(10);
		jtxtSurname.setBounds(343, 207, 219, 34);
		frame.getContentPane().add(jtxtSurname);

		jtxtGender = new JTextField();
		jtxtGender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtGender.setColumns(10);
		jtxtGender.setBounds(343, 243, 219, 34);
		frame.getContentPane().add(jtxtGender);
		
		jtxtDOB = new JTextField();
		jtxtDOB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtDOB.setColumns(10);
		jtxtDOB.setBounds(343, 279, 219, 34);
		frame.getContentPane().add(jtxtDOB);
		
		jtxtAge = new JTextField();
		jtxtAge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtAge.setColumns(10);
		jtxtAge.setBounds(343, 315, 219, 34);
		frame.getContentPane().add(jtxtAge);
		
		jtxtSalary = new JTextField();
		jtxtSalary.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtSalary.setColumns(10);
		jtxtSalary.setBounds(343, 351, 219, 34);
		frame.getContentPane().add(jtxtSalary);
		
		JButton btnExit = new JButton("Print");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageFormat header = new MessageFormat("printing in Progress");
				MessageFormat footer = new MessageFormat("page {0, number, integer}");
				
				try
				{
					table.print();
				}
				catch(java.awt.print.PrinterException ev) {
					System.err.format("No printer found",ev.getMessage());
				}
			}
			
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(427, 471, 164, 39);
		frame.getContentPane().add(btnExit);
		
		JButton btnPrint = new JButton("Reset");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jtxtEmployeeID.setText(null);
				jtxtNINumber.setText(null);
				jtxtFirstname.setText(null);
				jtxtSurname.setText(null);
				jtxtGender.setText(null);
				jtxtDOB.setText(null);
				jtxtAge.setText(null);
				jtxtSalary.setText(null);
			}
		});