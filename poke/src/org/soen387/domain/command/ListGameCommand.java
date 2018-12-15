package org.soen387.domain.command;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.challenge.IChallenge;
import org.soen387.domain.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.game.IGame;
import org.soen387.domain.game.mapper.GameInputMapper;

public class ListGameCommand extends ValidatorCommand{

	public ListGameCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		Object userObj = helper.getSessionAttribute("user");
		if(userObj != null) {
			try {
				List<IGame> gamesList = GameInputMapper.findAll();
				helper.setRequestAttribute("gamesList", gamesList);
			} catch (SQLException | MapperException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new CommandException("something wrong, can not find any game!");
			}
		} else {
			throw new CommandException("You are not allowed to list games without login first!");
		}
	}

}
