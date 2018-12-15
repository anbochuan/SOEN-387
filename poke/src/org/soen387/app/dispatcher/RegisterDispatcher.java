package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.RegisterCommand;

public class RegisterDispatcher extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		RegisterCommand registerCommand = new RegisterCommand(myHelper);
		try {
			registerCommand.execute();
			try {
				UoW.getCurrent().commit();
			} catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
				// TODO Auto-generated catch block
				myHelper.setRequestAttribute("message", "something wrong during commit");
				forward("/WEB-INF/jsp/fail.jsp");
			} 
			myHelper.setRequestAttribute("message", "user register successfully.");
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/fail.jsp");
		}
		
		
	}

	
}
