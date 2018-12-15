package org.soen387.domain.challenge;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IChallenge extends IDomainObject<Long>{
	
	public abstract Long getChallenger();
	
	public abstract Long getChallengee();
	
	public abstract int getStatus();
	
	public abstract Long getDeck();
	
	public abstract void setChallenger(Long challengerId);
	
	public abstract void setChallengee(Long challengeeId);
	
	public abstract void setStatus(int status);
	
	public abstract void setDeck(Long deckId);

}
