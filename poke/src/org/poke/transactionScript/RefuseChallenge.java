package org.poke.transactionScript;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poke.rowDataGateway.ChallengeRDG;
import org.poke.rowDataGateway.DBConnection;

/**
 * Servlet implementation class RefuseChallenge
 */
@WebServlet("/RefuseChallenge")
public class RefuseChallenge extends HttpServlet {
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
		String challenge = request.getParameter("challenge");
		long challengeId = Long.parseLong(challenge);
		Object res = request.getSession(true).getAttribute("userId");
		if(res != null) { // user has already logged in
			try {
				long userId = (Long)res;
				ChallengeRDG challengeRdg = ChallengeRDG.find(challengeId);
				if(challengeRdg != null) {
					if(challengeRdg.getChallengee() != userId && challengeRdg.getChallenger() != userId) {
						request.setAttribute("status", "fail");
						request.setAttribute("message", "Whoops, something goes wrong! You can not refuse the challege which has nothing to do with you.");
						request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
						
					} else if(challengeRdg.getChallengee() == userId && challengeRdg.getChallenger() != userId){
						challengeRdg.setStatus(1);
						challengeRdg.update();
						request.setAttribute("status", "success");
						request.setAttribute("message", "You just refuse No. " + challengeId + " challenge, and the challenger is No. " + challengeRdg.getChallenger() + " .");
						request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
					} else if(challengeRdg.getChallengee() != userId && challengeRdg.getChallenger() == userId){
						challengeRdg.setStatus(2);
						challengeRdg.update();
						request.setAttribute("status", "success");
						request.setAttribute("message", "You just withdraw No. " + challengeId + " challenge, and the challengee is No. " + challengeRdg.getChallengee() + " .");
						request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
					} else {
						request.setAttribute("status", "fail");
						request.setAttribute("message", "Whoops, something goes wrong!");
						request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("status", "fail");
					request.setAttribute("message", "Whoops, something goes wrong! Can not find any challenge based on the given id.");
					request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBConnection.closeConnection();
			}
		} else {
			request.setAttribute("status", "fail");
			request.setAttribute("message", "Whoops, something goes wrong! You can not refuse challenge without log in first.");
			request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
		}
	}

}
