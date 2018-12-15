package org.soen387.domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.user.User;
import org.soen387.domain.user.UserFactory;
import org.soen387.domain.user.mapper.UserInputMapper;

public class RegisterCommand extends ValidatorCommand{

	public RegisterCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		String username = helper.getString("user");
		String password = helper.getString("pass");
		
		if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
			throw new CommandException("username or password can not be empty");
		}
		try {
			User user = UserInputMapper.find(username);
			String name = user.getUsername();
			throw new CommandException("username " + name + " already exist!");
		} catch (SQLException e) {
			// good to go
			//e.printStackTrace();
		} catch (MapperException e) {
			// good to go
			//e.printStackTrace();
		}
		
		// send 2 params to factory
		try {
			User newUser = UserFactory.createNew(username, password);
			helper.setRequestAttribute("user", newUser);
			helper.setSessionAttribute("user", newUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CommandException("Somthing wrong!");
		}
		
	}
	
	

}
