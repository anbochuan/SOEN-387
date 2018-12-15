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
 * Servlet implementation class ViewBoard
 */
@WebServlet("/ViewBoard")
public class ViewBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String game = request.getParameter("game");
		long gameId = Long.parseLong(game);
		Object res = request.getSession(true).getAttribute("userId");
		if(res != null) { // user has already logged in
			try {
				long userId = (Long)res;
				GameRDG gameRdg = GameRDG.find(gameId);
				if(gameRdg.getChallengeeId() != userId && gameRdg.getChallengerId() != userId) {
					request.setAttribute("status", "fail");
					request.setAttribute("message", "Can not view board since this game has nothing to do with you.");
					request.getRequestDispatcher("WEB-INF/failure.jsp").forward(request, response);
				} else {
					long challengerId = gameRdg.getChallengerId();
					long challengeeId = gameRdg.getChallengeeId();
					ArrayList<Long> players = new ArrayList();
					players.add(challengerId);
					players.add(challengeeId);
					ArrayList<Long> decks = new ArrayList();
					decks.add(gameRdg.getChallengerDeckId());
					decks.add(gameRdg.getChallengeeDeckId());
					int handSizeChallenger = HandsRDG.countHandSize(challengerId);
					int handSizeChallengee = HandsRDG.countHandSize(challengeeId);
					int deckSizeChallenger = CardRDG.countDeckSize(challengerId);
					int deckSizeChallengee = CardRDG.countDeckSize(challengeeId);
					List<BenchRDG> cardsOnBenchChallenger = BenchRDG.findCardOnBench(challengerId);
					ArrayList<Long> cardIdListChallenger = new ArrayList();
					for(int i=0; i < cardsOnBenchChallenger.size(); i++) {
						long cardId = cardsOnBenchChallenger.get(i).getCardId();
						cardIdListChallenger.add(cardId);
					}
					List<BenchRDG> cardsOnBenchChallengee = BenchRDG.findCardOnBench(challengeeId);
					ArrayList<Long> cardIdListChallengee = new ArrayList();
					for(int i=0; i < cardsOnBenchChallengee.size(); i++) {
						long cardId = cardsOnBenchChallengee.get(i).getCardId();
						cardIdListChallengee.add(cardId);
					}
					Player challenger = new Player("playing", handSizeChallenger, deckSizeChallenger, 0, cardIdListChallenger);
					Player challengee = new Player("playing", handSizeChallengee, deckSizeChallengee, 0, cardIdListChallengee);
					Board board = new Board(gameId, players, decks, challenger, challengee);
					request.setAttribute("board", board);
					request.getRequestDispatcher("WEB-INF/viewBoard.jsp").forward(request, response);
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
