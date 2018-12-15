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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String pwd = request.getParameter("pass");
		if(user == null || user.isEmpty() || pwd == null || pwd.isEmpty()) {
			request.setAttribute("message", "Please enter your user name and password.");
			request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
		} else {
		    try {
				UserRDG userRdg = UserRDG.find(user);
				if(userRdg != null) {
					request.setAttribute("status", "fail");
					request.setAttribute("message", "This user name has already been registerd, please pick another one.");
					request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
				} else {
					long newId = UserRDG.getMaxId();
					userRdg = new UserRDG(newId, user, pwd);
					userRdg.insert();
					long userId = userRdg.getId();
					request.getSession(true).setAttribute("userId", userId);
					request.setAttribute("status", "success");
					request.setAttribute("message", "User " + user + " has been registered successfully.");
					request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBConnection.closeConnection();
			}
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
