package org.soen387.domain.card;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.domain.card.Card;
import org.soen387.domain.card.ICard;
import org.soen387.domain.card.mapper.CardInputMapper;

public class CardProxy extends DomainObjectProxy<Long, Card> implements ICard{

	public CardProxy(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getDeckId() {
		// TODO Auto-generated method stub
		return getInnerObject().getDeckId();
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return getInnerObject().getType();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return getInnerObject().getName();
	}

	@Override
	public String getBasic() {
		// TODO Auto-generated method stub
		return getInnerObject().getBasic();
	}

	@Override
	public void setDeckId(Long deckId) {
		// TODO Auto-generated method stub
		getInnerObject().setDeckId(deckId);
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		getInnerObject().setType(type);
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		getInnerObject().setName(name);
	}

	@Override
	public void setBasic(String basic) {
		// TODO Auto-generated method stub
		getInnerObject().setBasic(basic);
	}

	@Override
	protected Card getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		// TODO Auto-generated method stub
		try {
			return CardInputMapper.findById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException(e);
		}
		
	}

}
