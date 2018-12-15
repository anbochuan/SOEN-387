package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.soen387.domain.user.User;

public class LogoutDispatcher extends Dispatcher{

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Object user = myHelper.getSessionAttribute("user");
		if(user != null) {
			myRequest.getSession().invalidate();
			myHelper.setRequestAttribute("message", "you logout successfully!");
			forward("/WEB-INF/jsp/success.jsp");
		} else {
			myHelper.setRequestAttribute("message", "you can not logout without login!");
			forward("/WEB-INF/jsp/fail.jsp");
		}
		
	}
	
	

}
