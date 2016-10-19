package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.Person;

public interface PersonDAO {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
	
	/** Busca usuario por login e senha */
	public Person getPerson(String login, String senha);
	
	/** Busca usuario pelo login */
	public Person getPerson(String login);
	
	
}
