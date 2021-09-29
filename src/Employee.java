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