package org.soen387.domain.deck;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.domain.deck.IDeck;
import org.soen387.domain.deck.mapper.DeckInputMapper;
import org.soen387.domain.deck.Deck;

public class DeckProxy extends DomainObjectProxy<Long, Deck> implements IDeck{

	public DeckProxy(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getUserId() {
		// TODO Auto-generated method stub
		return getInnerObject().getUserId();
	}

	@Override
	public void setUserId(Long userId) {
		// TODO Auto-generated method stub
		getInnerObject().setUserId(userId);
		
	}

	@Override
	protected Deck getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		// TODO Auto-generated method stub
		try {
			return DeckInputMapper.findById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException(e);
		}
	}

}
