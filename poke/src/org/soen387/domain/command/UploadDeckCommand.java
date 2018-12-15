package org.soen387.domain.command;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.card.Card;
import org.soen387.domain.card.CardFactory;
import org.soen387.domain.deck.Deck;
import org.soen387.domain.deck.DeckFactory;
import org.soen387.domain.user.User;

public class UploadDeckCommand extends ValidatorCommand{

	public UploadDeckCommand(Helper helper) {
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
			Long deckId = (long) 0;
			try {
				Deck newDeck = DeckFactory.createNew(userId);
				deckId = newDeck.getId();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String deck = helper.getString("deck");
			List<String> cards = Arrays.asList(deck.split("\\n"));
			
			String cardType = "";
			String cardName = "";
			String cardBasic = "";
			
			if(cards.size() == 40) {
				for(int i = 0; i < cards.size(); i++) {
					String card = cards.get(i);
					String[] cardInfo = card.split(" ");
					
					if(cardInfo.length == 2) {
						cardType = cardInfo[0];
						cardName = cardInfo[1].replace("\"", "");
						cardBasic = "";
					}
					if(cardInfo.length == 3) {
						cardType = cardInfo[0];
						cardName = cardInfo[1].replace("\"", "");
						cardBasic = cardInfo[2].replace("\"", "");
					}
					
					try {
						Card newCard = CardFactory.createNew(deckId, cardType, cardName, cardBasic);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						throw new CommandException("something wrong, can not upload deck!");
					}
			    }
			} else {
				if(cards.size() > 40) {
					throw new CommandException("can not upload deck," + cards.size() + " cards is more than 40");
				} else {
					throw new CommandException("can not upload deck," + cards.size() + " cards is less than 40");
				}
				
			}
		
		} else {
			throw new CommandException("can not upload deck, Please login first!");
		}
	}
}
