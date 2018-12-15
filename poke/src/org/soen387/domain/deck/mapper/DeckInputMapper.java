package org.soen387.domain.deck.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.soen387.domain.deck.IDeck;
import org.soen387.domain.deck.Deck;
import org.soen387.domain.deck.DeckFactory;
import org.soen387.domain.deck.DeckProxy;
import org.soen387.service.DeckFinder;

public class DeckInputMapper {

	public static Deck findById(Long id) throws SQLException, MapperException, DomainObjectCreationException {
		try {
			return IdentityMap.get(id, Deck.class);
		} catch(DomainObjectNotFoundException e) {
		} catch(ObjectRemovedException e) {
		}
		
		ResultSet rs = DeckFinder.find(id);
		if(!rs.next()) {
			throw new MapperException("Can not find any deck with given id");
		} 
		Deck deck = getDeck(rs);
		return deck;
	}
	
	public static List<IDeck> findByUserId(Long userId) throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = DeckFinder.find(userId);
//		if(!rs.next()) {
//			throw new MapperException("Can not find any deck with given userId");
//		} 
		List<IDeck> decksList = buildCollection(rs);
		return decksList;
	}
	
	
	public static List<IDeck> findAll() throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = DeckFinder.findAll();
		List<IDeck> decksList = buildCollection(rs);
		return decksList;
	}
	
	
	private static Deck getDeck(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		long id = rs.getLong("id");
		long version = rs.getLong("version");
		long userId = rs.getLong("user_id");
		Deck deck = DeckFactory.createClean(id, version, userId);
		return deck;
	}
	
	private static List<IDeck> buildCollection(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		List<IDeck> decksList = new ArrayList<IDeck>();
		while(rs.next()) {
			Long id = rs.getLong("id");
			IDeck deckProxy = new DeckProxy(id);
			decksList.add(deckProxy);
		}
		return decksList; 
	}
}
