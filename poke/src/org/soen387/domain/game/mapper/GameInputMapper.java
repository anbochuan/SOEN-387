package org.soen387.domain.game.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.soen387.domain.challenge.Challenge;
import org.soen387.domain.game.Game;
import org.soen387.domain.game.GameFactory;
import org.soen387.domain.game.GameProxy;
import org.soen387.domain.game.IGame;
import org.soen387.service.ChallengeFinder;
import org.soen387.service.GameFinder;


public class GameInputMapper {
	
	public static Game find(Long id) throws SQLException, MapperException, DomainObjectCreationException {
		
		ResultSet rs = GameFinder.find(id);
		if(!rs.next()) {
			throw new MapperException("Can not find any game with given id");
		} 
		Game game = getGame(rs);
		return game;
	}
	
	public static List<IGame> findAll() throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = GameFinder.findAll();
		List<IGame> gamesList = buildCollection(rs);
		return gamesList;
	}
	
	public static Game findDuplicate(Long challengeeId, Long challengerId, Long challengeeDeck) throws SQLException, MapperException {
		
		ResultSet rs = GameFinder.findDuplicate(challengeeId, challengerId, challengeeDeck);
		if(!rs.next()) {
			throw new MapperException("Can not find any game with given challengeeId and challengerId");
		}
		Game game = getGame(rs);
		return game;
	}
	
	private static Game getGame(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		Long id = rs.getLong("id");
		Long version = rs.getLong("version");
		Long challenger = rs.getLong("challenger");
		Long challengee = rs.getLong("challengee");
		Long challengerDeck = rs.getLong("challenger_deck");
		Long challengeeDeck = rs.getLong("challengee_deck");
		Game game = GameFactory.createClean(id, version, challenger, challengee, challengerDeck, challengeeDeck);
		return game;
	}
	
	private static List<IGame> buildCollection(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		List<IGame> gamesList = new ArrayList<IGame>();
		while(rs.next()) {
			Long id = rs.getLong("id");
			IGame gameProxy = new GameProxy(id);
			gamesList.add(gameProxy);
		}
		return gamesList; 
	}

}
