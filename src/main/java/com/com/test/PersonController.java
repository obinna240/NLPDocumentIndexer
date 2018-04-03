package com.com.test;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/person")
public class PersonController 
{
	
	Person person;
	
	@Autowired
	public PersonController(Person person)
	{
		this.person = person;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getPersons(Model model)
	{
		List<String> y = new LinkedList<String>();
		y.add("n");
		y.add("p");
		model.addAttribute("persons", y);
		return "personList";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/register")
	public String register()
	{
		return "registrationForm";
	}
}
