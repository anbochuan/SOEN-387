package org.soen387.domain.game;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.domain.challenge.Challenge;
import org.soen387.domain.challenge.IChallenge;
import org.soen387.domain.game.Game;
import org.soen387.domain.game.IGame;
import org.soen387.domain.game.mapper.GameInputMapper;
import org.soen387.domain.challenge.mapper.ChallengeInputMapper;

public class GameProxy extends DomainObjectProxy<Long, Game> implements IGame{

	public GameProxy(Long id) {
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
	public Long getChallengerDeck() {
		// TODO Auto-generated method stub
		return getInnerObject().getChallengerDeck();
	}

	@Override
	public Long getChallengeeDeck() {
		// TODO Auto-generated method stub
		return getInnerObject().getChallengeeDeck();
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
	public void setChallengerDeck(Long challengerDeckId) {
		// TODO Auto-generated method stub
		getInnerObject().setChallengeeDeck(challengerDeckId);
	}

	@Override
	public void setChallengeeDeck(Long challengeeDeckId) {
		// TODO Auto-generated method stub
		getInnerObject().setChallengeeDeck(challengeeDeckId);
	}

	@Override
	protected Game getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		// TODO Auto-generated method stub
		try {
			return GameInputMapper.find(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException(e);
		}
	}

}
