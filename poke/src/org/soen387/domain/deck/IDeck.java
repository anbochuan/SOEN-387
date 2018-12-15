package org.soen387.domain.deck;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IDeck extends IDomainObject<Long> {
	
	public abstract Long getUserId();
	
	public abstract void setUserId(Long userId);
	
}
