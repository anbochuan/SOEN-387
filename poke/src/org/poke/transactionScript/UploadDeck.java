package org.poke.transactionScript;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poke.rowDataGateway.CardRDG;
import org.poke.rowDataGateway.DBConnection;

/**
 * Servlet implementation class UploadDeck
 */
@WebServlet("/UploadDeck")
public class UploadDeck extends HttpServlet {
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
		Object res = request.getSession(true).getAttribute("userId");
		if(res != null) { // user has already logged in
			try {
				long userId = (Long)res;
				String deck = request.getParameter("deck");
				long deckId = CardRDG.getMaxDeckId();
				List<String> cards = Arrays.asList(deck.split("\\n"));
				if(cards.size() == 40) {
					for(int i = 0; i < cards.size(); i++) {
						String card = cards.get(i);
						String[] cardInfo = card.split(" "); 
						String cardType = cardInfo[0];
						String cardName = cardInfo[1].replace("\"", "");
						long cardId = CardRDG.getMaxCardId();
						CardRDG cardRdg = new CardRDG(cardId, cardType, cardName, deckId, userId);
						cardRdg.insert();
					}
					request.setAttribute("status", "success");
					request.setAttribute("message", cards.size() + " cards has been added successfully.");
					request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
				} else {
					if(cards.size() > 40) {
						request.setAttribute("status", "fail");
						request.setAttribute("message", cards.size() + " cards is more than 40.");
						request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
					} else {
						request.setAttribute("status", "fail");
						request.setAttribute("message", cards.size() + " cards is less than 40.");
						request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
					}
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBConnection.closeConnection();
			}
		} else {
			request.setAttribute("status", "fail");
			request.setAttribute("message", "Whoops, something goes wrong! You can not upload deck without log in first.");
			request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
		}
	}

}
