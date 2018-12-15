package org.soen387.domain.challenge.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.challenge.Challenge;
import org.soen387.domain.challenge.ChallengeFactory;
import org.soen387.domain.challenge.ChallengeProxy;
import org.soen387.domain.challenge.IChallenge;
import org.soen387.service.ChallengeFinder;

public class ChallengeInputMapper {
	
	public static Challenge find(Long challengeId) throws SQLException, MapperException, DomainObjectCreationException {
		
		ResultSet rs = ChallengeFinder.find(challengeId);
		if(!rs.next()) {
			throw new MapperException("Can not find any challenge with given id");
		}
		Challenge challenge = getChallenge(rs);
		return challenge;
	}
	
	public static List<IChallenge> findAll() throws SQLException, MapperException, DomainObjectCreationException{
		
		ResultSet rs = ChallengeFinder.findAll();
		List<IChallenge> challengesList = buildCollection(rs);
		return challengesList;
	}
	
	public static Challenge findDuplicate(Long challengeeId, Long challengerId, int status) throws SQLException, MapperException {
		
		ResultSet rs = ChallengeFinder.findDuplicate(challengeeId, challengerId, status);
		if(!rs.next()) {
			throw new MapperException("Can not find any challenge with given challengeeId and challengerId");
		}
		Challenge challenge = getChallenge(rs);
		return challenge;
	}
	
	private static Challenge getChallenge(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		Long id = rs.getLong("id");
		Long version = rs.getLong("version");
		Long challenger = rs.getLong("challenger");
		Long challengee = rs.getLong("challengee");
		int status = rs.getInt("status");
		Long deck = rs.getLong("deck");
		Challenge challenge = ChallengeFactory.createClean(id, version, challenger, challengee, status, deck);
		UoW.getCurrent().registerClean(challenge);
		return challenge;
	}
	
	private static List<IChallenge> buildCollection(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		List<IChallenge> challengesList = new ArrayList<IChallenge>();
		while(rs.next()) {
			Long id = rs.getLong("id");
			IChallenge challengeProxy = new ChallengeProxy(id);
			challengesList.add(challengeProxy);
		}
		return challengesList; 
	}

}
