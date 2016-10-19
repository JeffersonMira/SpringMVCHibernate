package com.journaldev.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.PermissaoUsuario;
import com.journaldev.spring.model.Person;

@Repository("daoPermissao")
public class HBPermissaoUsuario implements DAOPermissaoUsuario {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<PermissaoUsuario> getPermissoesUsuario(Person person) {
		if (person == null) {
			return new ArrayList<PermissaoUsuario>();
		}
		Query query = sessionFactory.getCurrentSession().createQuery("from PermissaoUsuario pu where pu.person = ?");
		query.setEntity(0, person);
		return query.list();
	}

	public void addRole(String role, Person person) {
		if (role != null && person != null) {
			PermissaoUsuario permissao = new PermissaoUsuario();
			permissao.setRole(role);
			permissao.setUsuario(person);
			sessionFactory.getCurrentSession().saveOrUpdate(permissao);
		}
		
	}

}
