package com.com.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author oonyimadu
 *
 */
@Component
public class Person 
{
	@Autowired
	List<String> lperson;
	

	public List<String> getLperson() {
		return lperson;
	}

	public void setLperson(List<String> lperson) {
		this.lperson = lperson;
	}
	
	
}
