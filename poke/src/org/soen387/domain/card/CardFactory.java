package org.soen387.domain.card;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.service.CardTDG;

public class CardFactory {

	public static Card createNew(Long deckId, String type, String name, String basic) throws SQLException {
		Long id = CardTDG.getMaxId();
		Long version = (long) 1;
		Card newCard = new Card(id, version, deckId, type, name, basic);
		try {
			UoW.getCurrent().registerNew(newCard);
		} catch (MissingMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newCard;
	}
	
	public static Card createClean(Long id, Long version, Long deckId, String type, String name, String basic) {
		Card newCard = new Card(id, version, deckId, type, name, basic);
		UoW.getCurrent().registerClean(newCard);
		return newCard;
	}
}
