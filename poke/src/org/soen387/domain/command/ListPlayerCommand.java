package org.soen387.domain.command;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.user.IUser;
import org.soen387.domain.user.mapper.UserInputMapper;

public class ListPlayerCommand extends ValidatorCommand{

	public ListPlayerCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		// TODO Auto-generated method stub
		Object userObj = helper.getSessionAttribute("user");
		if(userObj != null) {
			try {
				List<IUser> playersList = UserInputMapper.findAll();
				helper.setRequestAttribute("playersList", playersList);
			} catch (SQLException | MapperException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new CommandException("something wrong, can not find any players.");
			} 
		} else {
			throw new CommandException("can not list players without login first.");
		}
		
	}

}
