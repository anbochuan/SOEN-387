package org.soen387.domain.user.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.user.User;
import org.soen387.service.UserTDG;

public class UserOutputMapper extends GenericOutputMapper<Long, User>{

	@Override
	public void insert(User user) throws MapperException {
		// TODO Auto-generated method stub
		Long id = user.getId();
		Long version = user.getVersion();
		String username = user.getUsername();
		String password = user.getPassword();
		try {
			int affectedRow = UserTDG.insert(id, version, username, password);
		} catch(SQLException e) {
			throw new MapperException("Something wrong, can not insert user with id: " + id, e);
		}
		
	}

	@Override
	public void update(User user) throws MapperException {
		Long id = user.getId();
		Long version = user.getVersion();
		String username = user.getUsername();
		String password = user.getPassword();
		try {
			int affectedRow = UserTDG.update(id, version, username, password);
			if(affectedRow == 0) {
				throw new LostUpdateException("LostUpdate: id " + id + " version " + version);
			}
			user.setVersion(user.getVersion()+1);
		} catch (SQLException e) {
			throw new MapperException("Something wrong, can not update User " + id,e); 
		}
		
	}

	@Override
	public void delete(User user) throws MapperException {
		// TODO Auto-generated method stub
		Long id = user.getId();
		Long version = user.getVersion();
		try {
			int affectedRow = UserTDG.delete(id, version);
			if(affectedRow == 0) {
				throw new LostUpdateException("LostUpdate: id " + id + " version " + version);
			}
			//user.setVersion(user.getVersion()+1);
		} catch(SQLException e) {
			throw new MapperException("Something wrong, can not delete User " + id, e);
		}
	}

}
