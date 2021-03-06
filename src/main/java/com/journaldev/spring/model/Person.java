package com.journaldev.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 *
 */
@Entity
@Table(name="PERSON")
public class Person {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	@Size(max=150)
	private String name;
	
	@Column(name="login", nullable=false, unique=true, length=64)
	private String login;
	
	@Column(name="hash_senha", nullable=false, length=128)
	private String hashSenha;
	
	private String country;

	public Person(){}
	
	public Person(int id, String name, String country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		if(id > 0)
			this.id = id;
		else
			throw new RuntimeException("impossibru");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getHashSenha() {
		return hashSenha;
	}
	public void setHashSenha(String valor) {
		hashSenha = valor;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", country=" + country + "]";
	}

}
