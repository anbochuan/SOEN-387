package org.poke.transactionScript;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poke.rowDataGateway.DBConnection;
import org.poke.rowDataGateway.UserRDG;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		try {
			UserRDG userRdg = UserRDG.find(user, pass);
			if(userRdg == null) {
				request.setAttribute("status", "fail");
				request.setAttribute("message", "I do not recognize that username and password combination.");
				request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
			} else {
				long id = userRdg.getId();
				request.getSession(true).setAttribute("userId", id);
				String username = userRdg.getUsername();
				request.setAttribute("status", "success");
				request.setAttribute("message", "User " + username + " has been successfully logged in.");
				request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
