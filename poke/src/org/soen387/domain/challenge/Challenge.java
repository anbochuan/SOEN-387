package org.soen387.domain.challenge;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.domain.challenge.IChallenge;

public class Challenge extends DomainObject<Long> implements IChallenge{
	
	private Long challenger;
	private Long challengee;
	private int status;
	private Long deck;

	protected Challenge(Long id, Long version, Long challenger, Long challengee, int status, Long deck) {
		super(id, version);
		// TODO Auto-generated constructor stub
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
		this.deck = deck;
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
	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public Long getDeck() {
		// TODO Auto-generated method stub
		return deck;
	}

	@Override
	public void setChallenger(Long challengerId) {
		// TODO Auto-generated method stub
		this.challenger = challengerId;
	}

	@Override
	public void setChallengee(Long challengeeId) {
		// TODO Auto-generated method stub
		this.challenger = challengeeId;
	}

	@Override
	public void setStatus(int status) {
		// TODO Auto-generated method stub
		this.status = status;
	}

	@Override
	public void setDeck(Long deckId) {
		// TODO Auto-generated method stub
		this.deck = deckId;
	}

}
