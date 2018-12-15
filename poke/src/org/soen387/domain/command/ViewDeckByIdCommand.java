package org.soen387.domain.command;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.card.ICard;
import org.soen387.domain.card.mapper.CardInputMapper;

public class ViewDeckByIdCommand extends ValidatorCommand{

	public ViewDeckByIdCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		Object userObj = helper.getSessionAttribute("user");
		if(userObj != null) {
			Long deckId = (Long) helper.getRequestAttribute("deckId");
			try {
				List<ICard> cardsList = CardInputMapper.findByDeckId(deckId);
				helper.setRequestAttribute("cardsList", cardsList);
			} catch (SQLException | MapperException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new CommandException("can not find any cards with given deck id");
			} 
		} else {
			throw new CommandException("can not find any cards without login!");
		}
		
		
	}

}
