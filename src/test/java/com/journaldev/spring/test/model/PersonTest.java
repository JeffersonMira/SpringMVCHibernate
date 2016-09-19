package com.journaldev.spring.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.test.util.PersonBuilder;


/*
 * The class must have the suffix 'test' in order to be include on Maven test fase.
 * Annotations are just available on version 4.x of maven.
 */
public class PersonTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/*
	 * Is going to be executed before executing the class of tests
	 */
	@BeforeClass
	public static void beforeClass(){}
	
	/*
	 * Is going to be executed before each test
	 */
	@Before 
	public void before(){}
	
	
	@Test
	public void shouldReturnSameName(){
		PersonBuilder builder = new PersonBuilder();
		Person p = builder.withName("João").build();
		
		assertEquals(p.getName(), "João");
	}
	
	@Test
	public void shouldNotAcceptInvalidID1(){
		exception.expect(RuntimeException.class);
		exception.expectMessage("impossibru");
		
		Person person = new Person();
		person.setId(-10);
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldNotAcceptInvalidID2(){
		
		Person person = new Person();
		person.setId(-10);
	}
	
}
