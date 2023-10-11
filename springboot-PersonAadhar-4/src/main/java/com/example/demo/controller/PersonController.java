package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.AadharCard;
import com.example.demo.model.Orders;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

import jakarta.validation.Valid;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@PostMapping("/save")
	public ResponseEntity<Person> savePerson(@Valid  @RequestBody Person person)
	{
		return new ResponseEntity<Person>(personService.savePerson(person),HttpStatus.CREATED);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable long id)
	{
		Optional<Person> person=personService.getPersonById(id);
		return person.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@GetMapping("/gets/{adharnumber}")
	public ResponseEntity<Person> getPersonByAdharnumber(@PathVariable(name="adharnumber")String adharnumber)
	{
	      Optional<Person> person=personService.getPersonByAdharnumber(adharnumber);
	      return person.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	@PutMapping("/updates/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person,@PathVariable long id) throws UserNotFoundException
	{
		return new ResponseEntity<Person>(personService.updatePerson(person, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable long id) throws UserNotFoundException
	{
		personService.deletePerson(id);
		return new ResponseEntity<String>("Person is Deleted From Database",HttpStatus.OK);
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Orders> saveOrder(@RequestBody  Orders orders)
	{
		return new ResponseEntity<Orders>(personService.saveOrder(orders),HttpStatus.CREATED);
	}
	
	@GetMapping("/getting/{orderid}")
	public ResponseEntity<Orders> getOrderById(@PathVariable(name="orderid") long orderid) throws UserNotFoundException
	{
		return new ResponseEntity<Orders>(personService.getOrderById(orderid),HttpStatus.OK);
	}
	
	@PutMapping("/updating/{orderid}")
	public ResponseEntity<Orders> updateOrders(@RequestBody  Orders orders1,@PathVariable(name="orderid") long orderid) throws UserNotFoundException
	{
		return new ResponseEntity<Orders>(personService.updateOrders(orders1, orderid),HttpStatus.OK);
	}
	@DeleteMapping("deletes/{orderid}")
	public ResponseEntity<String> delteOrders(@PathVariable  long orderid) throws UserNotFoundException
	{
		personService.deleteOrders(orderid);
		return new ResponseEntity<String>("Order is Deleted From Database",HttpStatus.OK);
	}
}
