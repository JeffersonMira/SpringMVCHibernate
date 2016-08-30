package com.journaldev.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TilesController {

	  @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	   public String homePage(Model model) {
	       return "homePage";
	   }

	  @RequestMapping(value = { "/contactus" }, method = RequestMethod.GET)
	   public String contactusPage(Model model) {
	       model.addAttribute("address", "Vietnam");
	       model.addAttribute("phone", "...");
	       model.addAttribute("email", "...");
	       return "contactusPage";
	   }
}
