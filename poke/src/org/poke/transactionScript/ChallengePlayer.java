package org.poke.transactionScript;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poke.rowDataGateway.CardRDG;
import org.poke.rowDataGateway.ChallengeRDG;
import org.poke.rowDataGateway.DBConnection;
import org.poke.rowDataGateway.UserRDG;

/**
 * Servlet implementation class ChallengePlayer
 */
@WebServlet("/ChallengePlayer")
public class ChallengePlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String player = request.getParameter("player");
		long challengeeId = Long.parseLong(player);
		Object res = request.getSession(true).getAttribute("userId");
		if(res != null) { // user has already logged in
			try {
				long challengerId = (Long)res;
				CardRDG cardRdg = CardRDG.find(challengerId);
				UserRDG userRdg = UserRDG.find(challengeeId);
				if(cardRdg == null) {
					request.setAttribute("status", "fail");
					request.setAttribute("message", "You can not make a challenge without uploading your deck first.");
					request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
				} else if(challengerId == challengeeId) {
						request.setAttribute("status", "fail");
						request.setAttribute("message", "You can not challenge yourself.");
						request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
					} else if(userRdg == null) {
						request.setAttribute("status", "fail");
						request.setAttribute("message", "We can not find any challengee based on the challengeeId.");
						request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
					} else {
						long id = ChallengeRDG.getMaxId();
						ChallengeRDG challengeRdg = new ChallengeRDG(id, challengerId, challengeeId, 0);
						challengeRdg.insert();
						request.setAttribute("status", "success");
						request.setAttribute("message", "The user with userId " + challengerId + "has already challenged the user with userId " + challengeeId + " successfully.");
						request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBConnection.closeConnection();
			}
		} else {
			request.setAttribute("status", "fail");
			request.setAttribute("message", "Whoops, something goes wrong! You can not make a challenge without log in first.");
			request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
		}
		
	}

}
