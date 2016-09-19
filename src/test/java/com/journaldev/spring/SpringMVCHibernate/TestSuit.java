package com.journaldev.spring.SpringMVCHibernate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.journaldev.spring.test.model.PersonTest;


/*
 * It is a group o test classes that could be called all together to be executed. 
 */
@RunWith(Suite.class)
@SuiteClasses(PersonTest.class)
public class TestSuit {
}
