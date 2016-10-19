package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.PermissaoUsuario;
import com.journaldev.spring.model.Person;

public interface DAOPermissaoUsuario {
	
	public List<PermissaoUsuario> getPermissoesUsuario(Person person);
	
	public void addRole(String role, Person person);
	
}
