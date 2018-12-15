package org.soen387.domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.challenge.Challenge;
import org.soen387.domain.challenge.ChallengeFactory;
import org.soen387.domain.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.game.Game;
import org.soen387.domain.game.GameFactory;
import org.soen387.domain.game.mapper.GameInputMapper;
import org.soen387.domain.user.User;

public class AcceptCommand extends ValidatorCommand{

	public AcceptCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		Long challengeId = (Long) helper.getRequestAttribute("challengeId");
		Long version = helper.getLong("version");
		Long challengeeDeck = helper.getLong("deck");
		User user = (User)helper.getSessionAttribute("user");
		Long userId = user.getId();
		
		try {
			Challenge challenge = ChallengeInputMapper.find(challengeId);
			Long challengeeId = challenge.getChallengee();
			Long challengerId = challenge.getChallenger();
			Long challengerDeck = challenge.getDeck();
			int status = challenge.getStatus();
			Long latestVersion = challenge.getVersion();
			
			if(challengeeId != userId) {
				throw new CommandException("You can not accept someone else's challenge.");
			} else if(challengerId == userId) {
				throw new CommandException("You can not accept yourselfs challenge.");
			} else if(status == 3) {
				Game game;
				try {
					game = GameInputMapper.findDuplicate(challengeeId, challengerId, challengeeDeck);
					throw new CommandException("This challenge has already been accepted.");
				} catch (MapperException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} 
			} else if(version != latestVersion) {
				throw new CommandException("Currency issue, you are lost update!");
			} else {
				ChallengeFactory.createDirty(challengeId, latestVersion, challengerId, challengeeId, 3, challengerDeck);
				GameFactory.createNew(challengerId, challengeeId, challengerDeck, challengeeDeck);
			}
		} catch (SQLException | MapperException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new CommandException(e.getMessage());
		}
		
	}

}
