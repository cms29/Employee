package com.employee.dao;



	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

	import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
	import com.employee.model.Employee;

	
	public class EmployeeDAO {
		private String jdbcURL = "jdbc:mysql://localhost:3306/EmployeeDB?useSSL=false";
		private String jdbcUsername = "admin";
		private String jdbcPassword = "admin";

		private static final String INSERT_Emp_SQL = "INSERT INTO Employee" + "  (name, addres, phonenum) VALUES "
				+ " (?, ?, ?);";

		private static final String SELECT_USER_BY_ID = "select id,name,addres,phonenum from Employee where id =?";
		private static final String SELECT_ALL_USERS = "select * from Employee";
		private static final String DELETE_USERS_SQL = "delete from Employee where id = ?;";
		private static final String UPDATE_USERS_SQL = "update Employee set name = ?,addres= ?, phonenum =? where id = ?;";

		public EmployeeDAO() {
		}

		protected Connection getConnection() {
			Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		}

		public void insertEmployee(Employee employee) throws SQLException {
			System.out.println(INSERT_Emp_SQL);
			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Emp_SQL)) {
				preparedStatement.setString(1, employee.getName());
				preparedStatement.setString(2, employee.getAddres());
				preparedStatement.setInt(3, employee.getPhonenum());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				printSQLException(e);
			}
		}

		public Employee selectUser(int id) {
			Employee employee = null;
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					String name = rs.getString("name");
					String addres = rs.getString("addres");
					int  phonenum = rs.getInt("phonenum");
					employee = new Employee(id, name, addres, phonenum);
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return employee;
		}

		public List<Employee> selectAllUsers() {

			// using try-with-resources to avoid closing resources (boiler plate code)
			List<Employee> users = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String addres = rs.getString("addres");
					int phonenum = rs.getInt("phonenum");
					users.add(new Employee(id, name, addres, phonenum));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return users;
		}

		public boolean deleteUser(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}

		public boolean updateUser(Employee employee) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
				statement.setString(1, employee.getName());
				statement.setString(2, employee.getAddres());
				statement.setInt(3, employee.getPhonenum());
				statement.setInt(4, employee.getId());

				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}

		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}

	}


