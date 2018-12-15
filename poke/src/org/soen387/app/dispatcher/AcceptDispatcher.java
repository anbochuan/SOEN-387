package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.AcceptCommand;

public class AcceptDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = myRequest.getPathInfo();
		String[] info = pathInfo.split("/");
		Long challengeId = Long.parseLong(info[2]);
		myHelper.setRequestAttribute("challengeId", challengeId);
		
		AcceptCommand acceptCommand = new AcceptCommand(myHelper);
		try {
			acceptCommand.execute();
			UoW.getCurrent().commit();
			forward("/WEB-INF/jsp/success.jsp");
		} catch (InstantiationException | IllegalAccessException | CommandException | MapperException
				| SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
