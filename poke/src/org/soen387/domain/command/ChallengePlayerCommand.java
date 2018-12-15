package org.soen387.domain.command;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.challenge.Challenge;
import org.soen387.domain.challenge.ChallengeFactory;
import org.soen387.domain.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.deck.Deck;
import org.soen387.domain.deck.IDeck;
import org.soen387.domain.deck.mapper.DeckInputMapper;
import org.soen387.domain.user.User;
import org.soen387.domain.user.mapper.UserInputMapper;

public class ChallengePlayerCommand extends ValidatorCommand{

	public ChallengePlayerCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		Long challengeeId = (Long) helper.getRequestAttribute("playerId");
		Long deckId = helper.getLong("deck");
		Object userObj = helper.getSessionAttribute("user");
		Boolean flag = false;
		if(userObj != null) {
			User user = (User)userObj;
			Long challengerId = user.getId();
			if(challengerId == challengeeId) {
				throw new CommandException("You are not allowed to challenge yourself!");
			} else {
				try {
					Challenge challenge = ChallengeInputMapper.findDuplicate(challengeeId, challengerId, 0);
					if(challenge != null) {
						throw new CommandException("You are not allowed to challenge same player twice when the status is still open!");
					}
				} catch (SQLException | MapperException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					// good to go
				}
				try {
					User challengee = UserInputMapper.find(challengeeId);
				} catch (SQLException | MapperException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new CommandException(e.getMessage());
				}
				try {
					List<IDeck> decksList = DeckInputMapper.findByUserId(challengerId);
					for(IDeck deck : decksList) {
						if(deckId == deck.getId()) {
							flag = true;
						} else {
							flag = false;
						}
					}
					if(flag == false) {
						throw new CommandException("Invalid deck id, either not yours or does not exist ever!");
					} else {
						// add challenge info into challenge table
						ChallengeFactory.createNew(challengerId, challengeeId, 0, deckId);
					}
				} catch (SQLException | MapperException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new CommandException(e.getMessage());
				}
				
					
				
			}
		} else {
			throw new CommandException("You are not allowed to challenge player without login first!");
		}
	}

}
