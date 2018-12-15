package org.soen387.domain.deck.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.deck.Deck;
import org.soen387.service.DeckTDG;

public class DeckOutputMapper extends GenericOutputMapper<Long, Deck>{

	@Override
	public void insert(Deck deck) throws MapperException {
		// TODO Auto-generated method stub
		Long id = deck.getId();
		Long version = deck.getVersion();
		Long userId = deck.getUserId();
		
		try {
			int affectedRow = DeckTDG.insert(id, version, userId);
		} catch(SQLException e) {
			throw new MapperException("Something wrong, can not insert deck with id: " + id, e);
		}
		
	}

	@Override
	public void update(Deck deck) throws MapperException {
		// TODO Auto-generated method stub
		Long id = deck.getId();
		Long version = deck.getVersion();
		Long userId = deck.getUserId();
		
		try {
			int affectedRow = DeckTDG.update(id, version, userId);
			if(affectedRow == 0) {
				throw new LostUpdateException("LostUpdate: id " + id + " version " + version);
			} else {
				deck.setVersion(deck.getVersion()+1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException("Something wrong, can not update deck with id: " + id, e);
		}
	}

	@Override
	public void delete(Deck deck) throws MapperException {
		// TODO Auto-generated method stub
		Long id = deck.getId();
		Long version = deck.getVersion();
		
		try {
			int affectedRow = DeckTDG.delete(id, version);
			if(affectedRow == 0) {
				throw new LostUpdateException("LostUpdate: id " + id + " version " + version);
			}
			//deck.setVersion(deck.getVersion()+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException("Something wrong, can not delete deck with id: " + id, e);
		}
	}

}
