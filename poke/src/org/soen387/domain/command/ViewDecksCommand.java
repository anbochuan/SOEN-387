package org.soen387.domain.command;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.deck.IDeck;
import org.soen387.domain.deck.mapper.DeckInputMapper;
import org.soen387.domain.user.User;

public class ViewDecksCommand extends ValidatorCommand{

	public ViewDecksCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		Object userObj = helper.getSessionAttribute("user");
		if(userObj != null) {
			User user = (User)userObj;
			Long userId = user.getId();
			try {
				List<IDeck> decksList = DeckInputMapper.findByUserId(userId);
				helper.setRequestAttribute("decksList", decksList);
			} catch (SQLException | MapperException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new CommandException("Can not find any deck with given user id");
			} 
		} else {
			throw new CommandException("Can not view decks without login!");
		}
	}

}
