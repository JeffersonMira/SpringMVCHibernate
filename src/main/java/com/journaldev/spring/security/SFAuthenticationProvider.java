package com.journaldev.spring.security;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.journaldev.spring.dao.DAOPermissaoUsuario;
import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.model.PermissaoUsuario;
import com.journaldev.spring.model.Person;


/**
 * Exemplo de authentication provider
 * @author kicolobo
 */
public class SFAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	@Qualifier("personDAO")
	private PersonDAO personDAO;
	
	public PersonDAO getDaoUsuario() {return personDAO;}
	public void setDaoUsuario(PersonDAO dao) {personDAO = dao;}
	
	@Autowired
	@Qualifier("daoPermissao")
	private DAOPermissaoUsuario daoPermissao;
	
	public DAOPermissaoUsuario getDaoPermissao() {return daoPermissao;}
	public void setDaoPermissao(DAOPermissaoUsuario dao) {daoPermissao = dao;}
	
	@Transactional
	public Authentication authenticate(Authentication auth)	throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;
		String username = token.getName();
		String senha    = token.getCredentials() != null ? token.getCredentials().toString() : null;
		Person person = personDAO.getPerson(username, senha);
		if (person == null) {
			return null;
		}
		List<PermissaoUsuario> permissoes = getDaoPermissao().getPermissoesUsuario(person);
		SFAuthentication resultado = new SFAuthentication(person, permissoes);
		resultado.setAuthenticated(person != null);
		return resultado;
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
