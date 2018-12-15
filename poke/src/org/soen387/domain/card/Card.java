package org.soen387.domain.card;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.domain.card.ICard;

public class Card extends DomainObject<Long> implements ICard{
	
	private Long deckId;
	private String type;
	private String name;
	private String basic;

	public Card(Long id, Long version, Long deckId, String type, String name, String basic) {
		super(id, version);
		// TODO Auto-generated constructor stub
		this.deckId = deckId;
		this.type = type;
		this.name = name;
		this.basic = basic;
	}

	@Override
	public Long getDeckId() {
		// TODO Auto-generated method stub
		return deckId;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getBasic() {
		// TODO Auto-generated method stub
		return basic;
	}

	@Override
	public void setDeckId(Long deckId) {
		// TODO Auto-generated method stub
		this.deckId = deckId;
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		this.type = type;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setBasic(String basic) {
		// TODO Auto-generated method stub
		this.basic = basic;
	}

}
