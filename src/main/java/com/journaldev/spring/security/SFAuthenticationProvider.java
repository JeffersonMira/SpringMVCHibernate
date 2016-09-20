package com.journaldev.spring.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.model.Person;
import com.journaldev.spring.model.PersonPermissions;

public class SFAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PersonDAO personDAO;
	
	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) arg0;
		
		String username = auth.getName();
		String password = auth.getAuthorities().toString();
		
		Person person = new Person(); //personDAO.getPersonByLoginAndPassword(username, password);
		
		if(person == null) { return null; }
		
		List<PersonPermissions> permissions = new ArrayList<PersonPermissions>(); //personPermissionDAO.getPermissions(person).
		
		SFAuthentication resultado = new SFAuthentication(person, permissions);
		
		resultado.setAuthenticated(person != null);
		
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(arg0);
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
}
