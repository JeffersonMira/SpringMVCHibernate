package com.journaldev.spring.model;

import org.springframework.security.core.GrantedAuthority;

public class PersonPermissions implements GrantedAuthority {

	//Change it for an ENUM
	private String authority;
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
