package com.com.test;

import java.text.DateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	public static void main (String[] args)
	{
		LinkedList<String> x = new LinkedList<String>();
		ArrayList<String> y = new ArrayList<String>();
		x.add("1");
		x.add("2");
		x.add("3");
		x.add("4");
		x.add("5");
		x.add("6");
		x.add("7");
		
		System.out.println(x);
		System.out.println(x.peek());
		System.out.println(x.peekLast());
		System.out.println(x.poll());
		System.out.println(x);
		System.out.println(x.pop());
		System.out.println(x);
		
	
		//Hashtable<Integer, String> xy = new Hashtable<Integer, String>();
		//xy.put(null, null);
		
		
		Stack xxx = new Stack();
		xxx.add("1");
		xxx.add("2");
		xxx.add("3");
		xxx.add("4");
		xxx.add("5");
		xxx.add("6");
		xxx.add("7");
		
		System.out.println(xxx);
		System.out.println(xxx.peek());
		
		ArrayDeque<String> ar = new ArrayDeque<String>();
		ar.add("1");
		ar.add("2");
		ar.add("3");
		ar.add("4");
		ar.add("5");
		ar.add("6");
		ar.add("7");
		ar.add("8");
		ar.add("9");
		System.out.println(ar);
	
		System.out.println(ar.peek());
	}
	
}
