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
import org.poke.rowDataGateway.GameRDG;

/**
 * Servlet implementation class AcceptChallenge
 */
@WebServlet("/AcceptChallenge")
public class AcceptChallenge extends HttpServlet {
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
					if(challengeRdg.getChallengee() == userId && challengeRdg.getChallenger() != userId) {
						challengeRdg.setStatus(3);
						challengeRdg.update();
						CardRDG cardRdgChallengee = CardRDG.find(challengeRdg.getChallengee());
						CardRDG cardRdgChallenger = CardRDG.find(challengeRdg.getChallenger());
						long challengeeDeckId = cardRdgChallengee.getDeckId();
						long challengerDeckId = cardRdgChallenger.getDeckId();
						long id = GameRDG.getMaxId();
						GameRDG gameRdg = new GameRDG(id, challengeRdg.getChallenger(), challengeRdg.getChallengee(), challengerDeckId, challengeeDeckId);
						gameRdg.insert();
						request.setAttribute("status", "success");
						request.setAttribute("message", "You just accept No. " + challengeId + " challenge, and the challengee is No. " + challengeRdg.getChallengee() + " .");
						request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
					} else {
						request.setAttribute("status", "fail");
						request.setAttribute("message", "Whoops, something goes wrong! You can not accept your own challenge. This challenge has nothing to do with you.");
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
			request.setAttribute("message", "Whoops, something goes wrong! You can not accept challenge without log in first.");
			request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
		}
	}

}
