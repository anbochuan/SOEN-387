package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.UploadDeckCommand;
import org.soen387.domain.command.ViewDecksCommand;

public class UploadViewDecksDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		String deckPram = myHelper.getString("deck");
		if(deckPram != null) {
			UploadDeckCommand uploadDeckCommand = new UploadDeckCommand(myHelper);
			try {
				uploadDeckCommand.execute();
				try {
					UoW.getCurrent().commit();
				} catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				myHelper.setRequestAttribute("message", "upload deck successfully done!");
				forward("/WEB-INF/jsp/success.jsp");
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				myHelper.setRequestAttribute("message", e.getMessage());
				forward("/WEB-INF/jsp/fail.jsp");
			}
		} else {
			ViewDecksCommand viewDecksCommand = new ViewDecksCommand(myHelper);
			try {
				viewDecksCommand.execute();
				myHelper.setRequestAttribute("message", "view decks successfully done!");
				forward("/WEB-INF/jsp/viewDecksSuccess.jsp");
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				myHelper.setRequestAttribute("message", e.getMessage());
				forward("/WEB-INF/jsp/fail.jsp");
			}
		}
	}

}
