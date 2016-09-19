package com.journaldev.spring.test.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;
import com.journaldev.spring.service.PersonServiceImpl;
import com.journaldev.spring.test.util.PersonBuilder;

public class PersonServiceTest {

	private PersonBuilder builder;
	
//	@injectMock is used to define which item will receive the mock objects
	// In this case it could be the service object and I could include the 
	// dao mock object. 
	
	@Mock
	private PersonDAO dao;
	
	@InjectMocks
	private PersonService service = new PersonServiceImpl();
	
	@Before
	public void prepare(){
		this.builder = new PersonBuilder();
//		this.service = new PersonServiceImpl();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldSavePerson(){
		Person p = builder.build();
		
		service.addPerson(p);
	}
	
	@Test
	public void shouldReturnListOfPerson(){
		when(dao.listPersons()).thenReturn(this.createListOfPerson());
		
		List<Person> ps = service.listPersons();
		
		assertTrue(ps.size() == 5);
		
		verify(dao, atLeastOnce()).listPersons();
		
		for (int i = 0; i < 5 ; i++) {
			assertTrue(ps.get(i).getId() == i);
		}
	}
	
	
	private List<Person> createListOfPerson(){
		
		List<Person> returnList = new ArrayList<Person>();
		
		for(int i = 0; i < 5 ; i++){
			returnList.add(builder.withId(i).build());
		}
		
		return returnList;
	}
	
}
