package org.soen387.domain.card;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface ICard extends IDomainObject<Long> {

	public abstract Long getDeckId();
	
	public abstract String getType();
	
	public abstract String getName();
	
	public abstract String getBasic();
	
	public abstract void setDeckId(Long deckId);
	
	public abstract void setType(String type);
	
	public abstract void setName(String name);
	
	public abstract void setBasic(String basic);
}
