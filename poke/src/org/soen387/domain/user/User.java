package org.soen387.domain.user;

import org.dsrg.soenea.domain.DomainObject;

public class User extends DomainObject<Long> implements IUser {
	
	private String username;
	private String password;
	
	public User(Long id, Long version, String username, String password) {
		super(id, version);
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
