package org.poke.transactionScript;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poke.rowDataGateway.CardRDG;
import org.poke.rowDataGateway.DBConnection;

/**
 * Servlet implementation class ViewDeck
 */
@WebServlet("/ViewDeck")
public class ViewDeck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object res = request.getSession(true).getAttribute("userId");
		if(res != null) { // user has already logged in
			try {
				long userId = (Long)res;
				CardRDG cardRdg = CardRDG.find(userId);
				if(cardRdg != null) {
					long deckId = cardRdg.getDeckId();
					List<CardRDG> cardsList = CardRDG.findAllCards(deckId);
					request.setAttribute("status", "success");
					request.setAttribute("cards", cardsList);
					request.setAttribute("deckId", deckId);
					request.getRequestDispatcher("WEB-INF/listCards.jsp").forward(request, response);
				} else {
					request.setAttribute("status", "fail");
					request.setAttribute("message", "Can not find any deck based on the given user id.");
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
			request.setAttribute("message", "Whoops, something goes wrong! You can not view deck without log in first.");
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
