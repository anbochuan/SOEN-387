package org.soen387.domain.challenge.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.challenge.Challenge;
import org.soen387.service.ChallengeTDG;

public class ChallengeOutputMapper extends GenericOutputMapper<Long, Challenge>{
	
	public void insert(Challenge challenge) throws MapperException {
		Long id = challenge.getId();
		Long version = challenge.getVersion();
		Long challenger = challenge.getChallenger();
		Long challengee = challenge.getChallengee();
		int status = challenge.getStatus();
		Long deck = challenge.getDeck();
		try {
			int affectedRow = ChallengeTDG.insert(id, version, challenger, challengee, status, deck);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException("Something wrong, can not insert challenge with id: " + id, e);
		}
	}
	
	public void update(Challenge challenge) throws MapperException {
		Long id = challenge.getId();
		Long version = challenge.getVersion();
		Long challenger = challenge.getChallenger();
		Long challengee = challenge.getChallengee();
		int status = challenge.getStatus();
		Long deck = challenge.getDeck();
		try {
			int affectedRow = ChallengeTDG.update(id, version, challenger, challengee, status, deck);
			if(affectedRow == 0) {
				throw new LostUpdateException("LostUpdate: id " + id + " version " + version);
			} else {
				challenge.setVersion(challenge.getVersion()+1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException("Something wrong, can not update challenge with id: " + id, e);
		}
	}
	
	public void delete(Challenge challenge) throws MapperException {
		Long id = challenge.getId();
		Long version = challenge.getVersion();
		try {
			int affectedRow = ChallengeTDG.delete(id, version);
			if(affectedRow == 0) {
				throw new LostUpdateException("LostUpdate: id " + id + " version " + version);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException("Something wrong, can not delete challenge with id: " + id, e);
		}
	}

}
