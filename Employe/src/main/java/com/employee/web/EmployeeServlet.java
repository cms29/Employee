package com.employee.web;


	
	import java.io.IOException;
	import java.sql.SQLException;
	import java.util.List;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import com.employee.dao.*;
	import com.employee.model.*;

	/**
	 * ControllerServlet.java
	 * This servlet acts as a page controller for the application, handling all
	 * requests from the user.
	 * @email Ramesh Fadatare
	 */

	@WebServlet("/")
	public class EmployeeServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private EmployeeDAO employeeDAO;
		
		public void init() {
			employeeDAO = new EmployeeDAO();
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action = request.getServletPath();

			try {
				switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertUser(request, response);
					break;
				case "/delete":
					deleteUser(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateUser(request, response);
					break;
				default:
					listUser(request, response);
					break;
				}
			} catch (SQLException ex) {
				throw new ServletException(ex);
			}
		}

		private void listUser(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<Employee> listUser = employeeDAO.selectAllUsers();
			request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee-list.jsp");
			dispatcher.forward(request, response);
		}

		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			dispatcher.forward(request, response);
		}

		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			Employee existingUser = employeeDAO.selectUser(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			request.setAttribute("employee", existingUser);
			dispatcher.forward(request, response);

		}

		private void insertUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			String name = request.getParameter("name");
			String addres = request.getParameter("addres");
			int phonenum = request.getParameter("phonenum");
			Employee newEmployee = new Employee(name, addres, phonenum);
			employeeDAO.insertEmployee(newEmployee);
			response.sendRedirect("list");
		}

		private void updateEmployee(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String addres = request.getParameter("addres");
			String phonenum = request.getParameter("phonenum");

			Employee book = new Employee(id, name, addres, phonenum);
			employeeDAO.updateUser(book);
			response.sendRedirect("list");
		}

		private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			employeeDAO.deleteUser(id);
			response.sendRedirect("list");
		}
	

}



