package org.soen387.domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.user.User;
import org.soen387.domain.user.mapper.UserInputMapper;

public class LoginCommand extends ValidatorCommand{

	public LoginCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		String username = helper.getString("user");
		String password = helper.getString("pass");
		
		try {
			User user = UserInputMapper.find(username, password);
			helper.setRequestAttribute("user", user);
			helper.setSessionAttribute("user", user);
		} catch (SQLException | MapperException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new CommandException("Invalid username or password, please try again!");
		}
		
	}

}
