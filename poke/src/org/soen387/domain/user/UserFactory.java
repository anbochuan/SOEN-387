package org.soen387.domain.user;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.service.UserTDG;

public class UserFactory {
	
	public static User createNew(String username, String password) throws SQLException {
		
		Long id = UserTDG.getMaxId();
		Long version = (long) 1;
		User newUser = new User(id, version, username, password);
		try {
			UoW.getCurrent().registerNew(newUser);
		} catch (MissingMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newUser;
	}
	
	public static User createClean(Long id, Long version, String username, String password) throws SQLException {
		
		User user = new User(id, version, username, password);
		UoW.getCurrent().registerClean(user);
		return user;
	}

}
