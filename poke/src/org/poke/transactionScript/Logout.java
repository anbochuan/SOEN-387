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
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object res = request.getSession(true).getAttribute("userId");
		if(res != null) {
		  long id = (Long)res;
		  try {
			UserRDG userRdg = UserRDG.find(id);
			  String username = userRdg.getUsername();
			  request.getSession(true).invalidate();
			  request.setAttribute("status", "success");
			  request.setAttribute("message", "User " + username + " has been successfully logged out.");
			  request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    } finally {
		    	DBConnection.closeConnection();
		      }
		} else {
			request.setAttribute("status", "fail");
			request.setAttribute("message", "Whoops, something goes wrong! You can not log out without log in first.");
			request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
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
