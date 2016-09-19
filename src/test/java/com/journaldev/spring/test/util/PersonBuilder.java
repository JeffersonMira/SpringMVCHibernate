package com.journaldev.spring.test.util;

import com.journaldev.spring.model.Person;

public class PersonBuilder {
	
	private int id = 1;
	private String name = "John Smith";
	private String country = "Turkey";
	
	public PersonBuilder withId(int id){
		this.id =id;
		return this;
	}
	
	public PersonBuilder withName(String name){
		this.name = name;
		return this;
	}
	
	public PersonBuilder withCountry(String country){
		this.country = country;
		return this;
	}
	
	public Person build(){
		return new Person(id, name, country);
	}
}
