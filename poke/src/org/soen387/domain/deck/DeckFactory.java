package org.soen387.domain.deck;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.service.DeckTDG;

public class DeckFactory {
	
	public static Deck createNew(Long userId) throws SQLException {
		
		Long id = DeckTDG.getMaxId();
		Long version = (long) 1;
		Deck newDeck = new Deck(id, version, userId);
		try {
			UoW.getCurrent().registerNew(newDeck);
		} catch (MissingMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDeck;
	}
	
	public static Deck createClean(Long id, Long version, Long userId) throws SQLException {
		
		Deck deck = new Deck(id, version, userId);
		UoW.getCurrent().registerClean(deck);
		return deck;
	}

}

