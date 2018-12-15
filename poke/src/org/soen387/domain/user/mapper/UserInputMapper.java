package org.soen387.domain.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.soen387.domain.user.IUser;
import org.soen387.domain.user.User;
import org.soen387.domain.user.UserFactory;
import org.soen387.domain.user.UserProxy;
import org.soen387.service.UserFinder;

public class UserInputMapper {
	
	public static User find(Long id) throws SQLException, MapperException, DomainObjectCreationException {
		try {
			return IdentityMap.get(id, User.class);
		} catch(DomainObjectNotFoundException e) {
		} catch(ObjectRemovedException e) {
		}
		
		ResultSet rs = UserFinder.find(id);
		if(!rs.next()) {
			throw new MapperException("Can not find any user with given id");
		} 
		User user = getUser(rs);
		return user;
	}
	
	public static User find(String username) throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = UserFinder.find(username);
		if(!rs.next()) {
			throw new MapperException("Can not find any user with given name");
		} 
		User user = getUser(rs);
		return user;
	}
	
	public static User find(String username, String password) throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = UserFinder.find(username, password);
		if(!rs.next()) {
			throw new MapperException("Can not find any user with given name and password");
		} 
		User user = getUser(rs);
		return user;
	}
	
	public static List<IUser> findAll() throws SQLException, MapperException, DomainObjectCreationException {
		ResultSet rs = UserFinder.findAll();
		List<IUser> usersList = buildCollection(rs);
		return usersList;
	}
	
	
	private static User getUser(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		long id = rs.getLong("id");
		long version = rs.getLong("version");
		String username = rs.getString("username");
		String password = rs.getString("password");
		User user = UserFactory.createClean(id, version, username, password);
		return user;
	}
	
	private static List<IUser> buildCollection(ResultSet rs) throws SQLException, MapperException, DomainObjectCreationException {
		List<IUser> usersList = new ArrayList<IUser>();
		while(rs.next()) {
			Long id = rs.getLong("id");
			IUser userProxy = new UserProxy(id);
			usersList.add(userProxy);
		}
		return usersList; 
	}
}
