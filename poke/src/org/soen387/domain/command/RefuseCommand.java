package org.soen387.domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.challenge.Challenge;
import org.soen387.domain.challenge.ChallengeFactory;
import org.soen387.domain.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.user.User;

public class RefuseCommand extends ValidatorCommand{

	public RefuseCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		Long challengeId = (Long) helper.getRequestAttribute("challengeId");
		int version = helper.getInt("version");
		User user = (User) helper.getSessionAttribute("user");
		Long userId = user.getId();
		
		try {
			Challenge challenge = ChallengeInputMapper.find(challengeId);
			Long challengeeId = challenge.getChallengee();
			Long challengerId = challenge.getChallenger();
			Long latestVersion = challenge.getVersion();
			Long deckId = challenge.getDeck();
			if(userId != challengeeId) {
				throw new CommandException("You are not the challengee, so you do not have right to refuse this challenge!");
			} else if(version != latestVersion) {
				throw new CommandException("Currency issue, You are lost update!");
			} else {
				ChallengeFactory.createDirty(challengeId, latestVersion, challengerId, challengeeId, 1, deckId);
			}
		} catch (SQLException | MapperException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new CommandException(e.getMessage());
		}
	}

}