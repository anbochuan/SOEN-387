package org.soen387.domain.game;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.domain.game.IGame;

public class Game extends DomainObject<Long> implements IGame{

	private Long challenger;
	private Long challengee;
	private Long challengerDeck;
	private Long challengeeDeck;
	
	protected Game(Long id, Long version, Long challenger, Long challengee, Long challengerDeck, Long challengeeDeck) {
		super(id, version);
		// TODO Auto-generated constructor stub
		this.challenger = challenger;
		this.challengee = challengee;
		this.challengerDeck = challengerDeck;
		this.challengeeDeck = challengeeDeck;
	}

	@Override
	public Long getChallenger() {
		// TODO Auto-generated method stub
		return challenger;
	}

	@Override
	public Long getChallengee() {
		// TODO Auto-generated method stub
		return challengee;
	}

	@Override
	public Long getChallengerDeck() {
		// TODO Auto-generated method stub
		return challengerDeck;
	}

	@Override
	public Long getChallengeeDeck() {
		// TODO Auto-generated method stub
		return challengeeDeck;
	}

	@Override
	public void setChallenger(Long challengerId) {
		// TODO Auto-generated method stub
		this.challenger = challengerId;
	}

	@Override
	public void setChallengee(Long challengeeId) {
		// TODO Auto-generated method stub
		this.challengee = challengeeId;
	}

	@Override
	public void setChallengerDeck(Long challengerDeckId) {
		// TODO Auto-generated method stub
		this.challengerDeck = challengerDeckId;
	}

	@Override
	public void setChallengeeDeck(Long challengeeDeckId) {
		// TODO Auto-generated method stub
		this.challengeeDeck = challengeeDeckId;
	}
	
	

}
