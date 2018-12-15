package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.ChallengePlayerCommand;

public class ChallengePlayerDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = myRequest.getPathInfo();
		String[] info = pathInfo.split("/");
		Long playerId = Long.parseLong(info[2]);
		myHelper.setRequestAttribute("playerId", playerId);
		ChallengePlayerCommand  challengePlayerCommand = new ChallengePlayerCommand(myHelper);
		try {
			challengePlayerCommand.execute();
			try {
				UoW.getCurrent().commit();
			} catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myHelper.setRequestAttribute("message", "challenge player successfully done!");
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}
	
	

}
