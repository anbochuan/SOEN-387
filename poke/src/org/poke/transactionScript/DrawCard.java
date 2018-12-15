package org.poke.transactionScript;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poke.rowDataGateway.BenchRDG;
import org.poke.rowDataGateway.CardRDG;
import org.poke.rowDataGateway.DBConnection;
import org.poke.rowDataGateway.GameRDG;
import org.poke.rowDataGateway.HandsRDG;
import org.poke.util.Board;
import org.poke.util.Player;

/**
 * Servlet implementation class DrawCard
 */
@WebServlet("/DrawCard")
public class DrawCard extends HttpServlet {
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
		String game = request.getParameter("game");
		long gameId = Long.parseLong(game);
		Object res = request.getSession(true).getAttribute("userId");
		if(res != null) { // user has already logged in
			try {
				long userId = (Long)res;
				GameRDG gameRdg = GameRDG.find(gameId);
				if(gameRdg.getChallengeeId() != userId && gameRdg.getChallengerId() != userId) {
					request.setAttribute("status", "fail");
					request.setAttribute("message", "Can not draw card since this game has nothing to do with you.");
					request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
				} else {
					CardRDG cardRdg = CardRDG.drawCard(userId);
					cardRdg.delete();
					HandsRDG handsRdg = new HandsRDG(HandsRDG.getMaxId(), cardRdg.getUserId(), gameId, cardRdg.getCardId(), cardRdg.getCardType(), cardRdg.getCardName());
					handsRdg.insert();
					request.setAttribute("status", "success");
					request.setAttribute("mesage", "Draw card done successfully.");
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

}
