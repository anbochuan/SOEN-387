package org.soen387.domain.user;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.domain.user.mapper.UserInputMapper;


public class UserProxy extends DomainObjectProxy<Long, User> implements IUser{

	public UserProxy(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getInnerObject().getUsername();
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		getInnerObject().setUsername(username);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return getInnerObject().getPassword();
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		getInnerObject().setPassword(password);
	}

	@Override
	protected User getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		// TODO Auto-generated method stub
		try {
			return UserInputMapper.find(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException(e);
		}
	}
	
}
