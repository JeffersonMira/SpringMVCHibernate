package com.journaldev.spring;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;


//fonte https://www.caelum.com.br/apostila-java-web/spring-mvc/#11-18-melhorando-a-usabilidade-da-nossa-aplicacao
@Controller
public class PersonController {

	private PersonService personService;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	
//	@RequestMapping(value="/",method=RequestMethod.GET)
//	public String listPersonsInit(Model model){
//		model.addAttribute("person",new Person());
//		model.addAttribute("listPersons", this.personService.listPersons());
//		return "person";
//	}
	
	@RequestMapping(value="/membro/persons",method=RequestMethod.GET)
	public String listPersons(Model model){
		model.addAttribute("person",new Person());
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}
	
	@RequestMapping(value="/person/add", method=RequestMethod.POST)
	public String addPerson(@Valid @ModelAttribute("person") Person p, //O objeto tem que ter um contrutor vazio e todos os atributos tem que ter get e set
						BindingResult bindingResult, Model model){//JSR303 validation
		
		/*Feio pakas esse código da porrrrrrrrrrrra*/
		if(bindingResult.hasErrors()){
			//Quando se usa o JSR303 não se pode dar o redirect. Caso seja feito um redirect, é 
			//o erro validationException não é tratado.
			model.addAttribute("listPersons", this.personService.listPersons());
			return "person";
		}
		
		if(p.getId() == 0){
			//new person, add it
			personService.addPerson(p);
		}else{
			personService.updatePerson(p);
		}
		
		return "redirect:/membro/persons";
	}
	
	@Secured("hasRole('ROLE_MEMBRO')") //Spring security intercepta e não deixa qualquer um utilizar o método. 
	@RequestMapping("/membro/remove/{id}")
	public String removePerson(@PathVariable("id") int id){
		this.personService.removePerson(id);
		
		return "redirect:/persons";
	}
	
	@RequestMapping("/membro/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model){
		model.addAttribute("person", this.personService.getPersonById(id));
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}
	
//	public ModelAndView login(){
//		ModelAndView model = new ModelAndView();
//		return model;
//	}
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.POST)
	public String loginPage(
			@RequestParam(value = "error",required = false) String error,
			@RequestParam(value = "logout",	required = false) String logout) {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out from JournalDEV successfully.");
		}

		model.addObject("person", new Person());
		model.setViewName("person");
		return "redirect:/membro/persons";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		
		session.invalidate();
		
		ModelAndView model = new ModelAndView();
		model.addObject("person", new Person());
		model.setViewName("person");
		
		return "redirect:/membro/persons";
	}
}
