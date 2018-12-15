package org.soen387.domain.game;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IGame extends IDomainObject<Long>{
	
	public abstract Long getChallenger();
	
	public abstract Long getChallengee();
	
	public abstract Long getChallengerDeck();
	
	public abstract Long getChallengeeDeck();
	
	public abstract void setChallenger(Long challengerId);
	
	public abstract void setChallengee(Long challengeeId);
	
	public abstract void setChallengerDeck(Long challengerDeckId);
	
	public abstract void setChallengeeDeck(Long challengeeDeckId);

}
