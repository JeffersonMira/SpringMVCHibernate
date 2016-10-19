package com.journaldev.spring.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.journaldev.spring.model.PermissaoUsuario;
import com.journaldev.spring.model.Person;

/**
 * http://docs.spring.io/autorepo/docs/spring-security/3.2.5.RELEASE/apidocs/org/springframework/security/core/Authentication.html
 */
public class SFAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;
	
	private final Person person;
	private boolean autenticado;
	
	public SFAuthentication(Person person, List<PermissaoUsuario> permissoes) {
		this.person = person;
		this.permissoes = permissoes;
	}
	
	public String getName() {
		return person != null ? person.getLogin() : null;
	}
	
	private List<PermissaoUsuario> permissoes;
	
	/**
	   Set by an AuthenticationManager to indicate the authorities that the 
	   principal has been granted. Note that classes should not rely on this 
	   value as being valid unless it has been set by a trusted AuthenticationManager.
	    Implementations should ensure that modifications to the returned collection
	   array do not affect the state of the Authentication object, or use an unmodifiable instance.

		Returns:
		the authorities granted to the principal, or an empty collection if the token has not been authenticated. Never null.
	 */
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissoes;
	}

	/**
	 	The credentials that prove the principal is correct. This is usually a password, 
	 	but could be anything relevant to the AuthenticationManager. Callers are expected to populate the credentials.
		Returns:
		the credentials that prove the identity of the Principal
	 */
	public Object getCredentials() {
		return person != null ? person.getHashSenha() : null;
	}

	/**
	 	Stores additional details about the authentication request. 
	 	These might be an IP address, certificate serial number etc.
		Returns:
		additional details about the authentication request, or null if not used
	 */
	public Object getDetails() {
		return person;
	}

	
	/**
	 	The identity of the principal being authenticated. In the case of an 
	 	authentication request with username and password, this would be the username. 
	 	Callers are expected to populate the principal for an authentication request.
		The AuthenticationManager implementation will often return an Authentication 
		containing richer information as the principal for use by the application. 
		Many of the authentication providers will create a UserDetails object as the principal.

		Returns:
		the Principal being authenticated or the authenticated principal after authentication.
	 */
	public Object getPrincipal() {
		return person != null ? person.getLogin() : null;
	}

	public boolean isAuthenticated() {
		return this.autenticado;
	}

	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		this.autenticado = isAuthenticated;
		
	}

}
