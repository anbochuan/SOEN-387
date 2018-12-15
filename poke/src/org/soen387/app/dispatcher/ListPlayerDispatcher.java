package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.domain.command.ListPlayerCommand;

public class ListPlayerDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		ListPlayerCommand listPlayerCommand = new ListPlayerCommand(myHelper);
		try {
			listPlayerCommand.execute();
			forward("/WEB-INF/jsp/listPlayerSuccess.jsp");
			
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
