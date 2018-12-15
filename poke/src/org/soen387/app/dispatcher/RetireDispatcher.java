package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.domain.command.RetireCommand;
import org.soen387.domain.command.ViewHandCommand;

public class RetireDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = myRequest.getPathInfo();
		String[] info = pathInfo.split("/");
		Long gameId = Long.parseLong(info[2]);
		myHelper.setRequestAttribute("gameId", gameId);
		
		RetireCommand retireCommand = new RetireCommand(myHelper);
		try {
			retireCommand.execute();
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			myHelper.setRequestAttribute("message","bad");
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
