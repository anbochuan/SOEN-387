package org.soen387.domain.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.service.ChallengeTDG;

public class ChallengeFactory {
	
	public static Challenge createNew(Long challenger, Long challengee, int status, Long deck) throws SQLException {
		
		Long id = ChallengeTDG.getMaxId();
		Long version = (long) 1;
		
		Challenge challenge = new Challenge(id, version, challenger, challengee, status, deck);
		try {
			UoW.getCurrent().registerNew(challenge);
		} catch (MissingMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return challenge;	
	}
	
	public static Challenge createClean(Long id, Long version, Long challenger, Long challengee, int status, Long deck) {
		
		Challenge challenge = new Challenge(id, version, challenger, challengee, status, deck);
		UoW.getCurrent().registerClean(challenge);
		return challenge;
	}
	
	public static Challenge createDirty(Long id, Long version, Long challenger, Long challengee, int status, Long deck) {
		Challenge challenge = new Challenge(id, version, challenger, challengee, status, deck);
		try {
			UoW.getCurrent().registerDirty(challenge);
		} catch (MissingMappingException | MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return challenge;
	}

}
