package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.domain.command.LoginCommand;

public class LoginDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginCommand loginCommand = new LoginCommand(myHelper);
		try {
			loginCommand.execute();
			myHelper.setRequestAttribute("message", "Login successfully done!");
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}
	

}
