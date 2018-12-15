package org.soen387.domain.card.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.card.Card;
import org.soen387.service.CardTDG;

public class CardOutputMapper extends GenericOutputMapper<Long, Card>{

	@Override
	public void insert(Card card) throws MapperException {
		// TODO Auto-generated method stub
		Long id = card.getId();
	    Long version = card.getVersion();
	    Long deckId = card.getDeckId();
	    String type = card.getType();
	    String name = card.getName();
	    String basic = card.getBasic();
	    
	    try {
			int affectedRow = CardTDG.insert(id, version, deckId, type, name, basic);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException("Something wrong, can not insert card with id: " + id, e);
		}
	}

	@Override
	public void update(Card card) throws MapperException {
		// TODO Auto-generated method stub
		Long id = card.getId();
	    Long version = card.getVersion();
	    Long deckId = card.getDeckId();
	    String type = card.getType();
	    String name = card.getName();
	    String basic = card.getBasic();
	    
	    try {
			int affectedRow = CardTDG.update(id, version, deckId, type, name, basic);
			if(affectedRow == 0) {
				throw new LostUpdateException("LostUpdate: id " + id + " version " + version);
			} else {
				card.setVersion(card.getVersion()+1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException("Something wrong, can not update card with id: " + id, e);
		}
	}

	@Override
	public void delete(Card card) throws MapperException {
		// TODO Auto-generated method stub
		Long id = card.getId();
		Long version = card.getVersion();
		try {
			int affectedRow = CardTDG.delete(id, version);
			if(affectedRow == 0) {
				throw new LostUpdateException("LostUpdate: id " + id + " version " + version);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException("Something wrong, can not delete card with id: " + id, e);
		}
	}

	

}
