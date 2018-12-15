package org.soen387.domain.board.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.soen387.domain.board.Board;
import org.soen387.domain.game.Game;
import org.soen387.domain.game.GameProxy;
import org.soen387.service.GameFinder;

public class BoardInputMapper {
	public static Board find(Long id) throws SQLException, MapperException, DomainObjectCreationException {
		
		ResultSet rs = GameFinder.find(id);
		if(!rs.next()) {
			throw new MapperException("Can not find any game with given id");
		} 
		Board board = getBoard(rs);
		return board;
	}
	
	private static Board getBoard(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		Long id = rs.getLong("id");
		Long version = rs.getLong("version");
		Long challenger = rs.getLong("challenger");
		Long challengee = rs.getLong("challengee");
		Long challengerDeck = rs.getLong("challenger_deck");
		Long challengeeDeck = rs.getLong("challengee_deck");
		// current attribute and the afters are not correct, need to change later,
		Board board = new Board(new GameProxy(id), challenger, "playing", "playing", 1, 39, 0, 0, 40, 0, null, null);
		return board;
	}
	
}
