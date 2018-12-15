package org.soen387.domain.game;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.game.Game;
import org.soen387.service.GameTDG;

public class GameFactory {

	public static Game createNew(Long challenger, Long challengee, Long challengerDeck, Long challengeeDeck) throws SQLException {
		
		Long id = GameTDG.getMaxId();
		Long version = (long) 1;
		
		Game game = new Game(id, version, challenger, challengee, challengerDeck, challengeeDeck);
		try {
			UoW.getCurrent().registerNew(game);
		} catch (MissingMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return game;	
	}
	
	public static Game createClean(Long id, Long version, Long challenger, Long challengee, Long challengerDeck, Long challengeeDeck) {
		
		Game game = new Game(id, version, challenger, challengee, challengerDeck, challengeeDeck);
		UoW.getCurrent().registerClean(game);
		return game;
	}
	
	public static Game createDirty(Long id, Long version, Long challenger, Long challengee, Long challengerDeck, Long challengeeDeck) {
		Game game = new Game(id, version, challenger, challengee, challengerDeck, challengeeDeck);
		try {
			UoW.getCurrent().registerDirty(game);
		} catch (MissingMappingException | MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return game;
	}
}
