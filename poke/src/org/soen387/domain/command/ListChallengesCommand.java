package org.soen387.domain.command;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.challenge.IChallenge;
import org.soen387.domain.challenge.mapper.ChallengeInputMapper;

public class ListChallengesCommand extends ValidatorCommand{

	public ListChallengesCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		Object userObj = helper.getSessionAttribute("user");
		if(userObj != null) {
			try {
				List<IChallenge> challengesList = ChallengeInputMapper.findAll();
				helper.setRequestAttribute("challengesList", challengesList);
			} catch (SQLException | MapperException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new CommandException("something wrong, can not find any challenge!");
			}
		} else {
			throw new CommandException("You are not allowed to list challenges without login first!");
		}
	}

}
