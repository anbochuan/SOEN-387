package org.soen387.domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.game.Game;
import org.soen387.domain.game.mapper.GameInputMapper;
import org.soen387.domain.user.User;

public class ViewDiscardCommand extends ValidatorCommand{

	public ViewDiscardCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		Long gameId = (Long) helper.getRequestAttribute("gameId");
		User user = (User)helper.getSessionAttribute("user");
		Long userId = user.getId();
		
		try {
			Game game = GameInputMapper.find(gameId);
			Long challengerId = game.getChallenger();
			Long challengeeId = game.getChallengee();
			if(userId != challengerId && userId != challengeeId) {
				throw new CommandException("not your game.");
			}
		} catch (SQLException | MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
