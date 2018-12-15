package org.soen387.domain.card.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.soen387.domain.card.Card;
import org.soen387.domain.card.CardFactory;
import org.soen387.domain.card.CardProxy;
import org.soen387.domain.card.ICard;
import org.soen387.service.CardFinder;


public class CardInputMapper {
	
	public static Card findById(Long id) throws SQLException, MapperException, DomainObjectCreationException {
		try {
			return IdentityMap.get(id, Card.class);
		} catch(DomainObjectNotFoundException e) {
		} catch(ObjectRemovedException e) {
		}
		
		ResultSet rs = CardFinder.find(id);
		if(!rs.next()) {
			throw new MapperException("Can not find any card with given id");
		} 
		Card card = getCard(rs);
		return card;
	}
	
	public static List<ICard> findByDeckId(Long deckId) throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = CardFinder.findByDeckId(deckId);
//		if(!rs.next()) {
//			throw new MapperException("Can not find any card with given deckId");
//		} 
		List<ICard> cardsList = buildCollection(rs);
		return cardsList;
	}
	
	private static Card getCard(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		long id = rs.getLong("id");
		long version = rs.getLong("version");
		long deckId = rs.getLong("deck_id");
		String type = rs.getString("type");
		String name = rs.getString("name");
		String basic = rs.getString("basic");
		Card card = CardFactory.createClean(id, version, deckId, type, name, basic);
		return card;
	}
	
	private static List<ICard> buildCollection(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		List<ICard> cardsList = new ArrayList<ICard>();
		while(rs.next()) {
			Long id = rs.getLong("id");
			ICard cardProxy = new CardProxy(id);
			cardsList.add(cardProxy);
		}
		return cardsList; 
	}

}
