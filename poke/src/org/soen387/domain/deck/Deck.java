package org.soen387.domain.deck;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.domain.deck.IDeck;

public class Deck extends DomainObject<Long> implements IDeck {
	
	private Long userId;
	
	public Deck(Long id, Long version, Long userId) {
		super(id, version);
		this.userId = userId;
	}

	@Override
	public Long getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}

	@Override
	public void setUserId(Long userId) {
		// TODO Auto-generated method stub
		this.userId = userId;
	}

}
