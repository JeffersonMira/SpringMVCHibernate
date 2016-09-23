package com.journaldev.spring.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.model.PersonPermissions;

public class SFAuthentication implements Authentication {

	private Person p;
	private List<PersonPermissions> authority;
	
	public SFAuthentication() {
		super();
	}
	
	public SFAuthentication(Person p, List<PersonPermissions> pp){
		this.p = p;
		this.authority = pp;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return false;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {

	}
	
	public Person getP() {
		return p;
	}

	public void setP(Person p) {
		this.p = p;
	}

	public List<PersonPermissions> getAuthority() {
		return authority;
	}

	public void setAuthority(List<PersonPermissions> authority) {
		this.authority = authority;
	}
}
