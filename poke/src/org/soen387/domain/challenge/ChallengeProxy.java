package org.soen387.domain.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.domain.challenge.Challenge;
import org.soen387.domain.challenge.IChallenge;
import org.soen387.domain.challenge.mapper.ChallengeInputMapper;

public class ChallengeProxy extends DomainObjectProxy<Long, Challenge> implements IChallenge{

	public ChallengeProxy(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getChallenger() {
		// TODO Auto-generated method stub
		return getInnerObject().getChallenger();
	}

	@Override
	public Long getChallengee() {
		// TODO Auto-generated method stub
		return getInnerObject().getChallengee();
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return getInnerObject().getStatus();
	}

	@Override
	public Long getDeck() {
		// TODO Auto-generated method stub
		return getInnerObject().getDeck();
	}

	@Override
	public void setChallenger(Long challengerId) {
		// TODO Auto-generated method stub
		getInnerObject().setChallenger(challengerId);
	}

	@Override
	public void setChallengee(Long challengeeId) {
		// TODO Auto-generated method stub
		getInnerObject().setChallengee(challengeeId);
	}

	@Override
	public void setStatus(int status) {
		// TODO Auto-generated method stub
		getInnerObject().setStatus(status);
	}

	@Override
	public void setDeck(Long deckId) {
		// TODO Auto-generated method stub
		getInnerObject().setDeck(deckId);
	}

	@Override
	protected Challenge getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		// TODO Auto-generated method stub
		Challenge challenge;
		try {
			challenge = ChallengeInputMapper.find(id);
			return challenge;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException(e);
		}
	}

}
