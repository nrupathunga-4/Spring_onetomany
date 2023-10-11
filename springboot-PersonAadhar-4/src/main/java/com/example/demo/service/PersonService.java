package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.AadharCard;
import com.example.demo.model.Orders;
import com.example.demo.model.Person;
import com.example.demo.repository.AadharRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AadharRepository aadharRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Person savePerson(Person person)
	{
		return personRepository.save(person);
	}
	
	public Optional<Person> getPersonById(long id)
	{
		return personRepository.findById(id);
	}
	
	public Optional<Person> getPersonByAdharnumber(String adharnumber)
	{
	    return personRepository.findByAadharCard_Adharnumber(adharnumber);
	}
	public Person updatePerson(Person person,long id) throws UserNotFoundException
	{
		Person person2=personRepository.findById(id).orElseThrow(()->new UserNotFoundException("User Doesn't Exist In Database"));
		person2.setFirstname(person.getFirstname());
		person2.setLastname(person2.getLastname());
		person2.setAge(person2.getAge());
		
		personRepository.save(person2);
		return person2;
	}
	public void deletePerson(long id) throws UserNotFoundException
	{
		personRepository.findById(id).orElseThrow(()->new UserNotFoundException("User Doesn't Exist In Database"));
		personRepository.deleteById(id);
	}
	
	public Orders saveOrder(Orders orders)
	{
		return orderRepository.save(orders);
	}
	
	public Orders getOrderById(long orderid) throws UserNotFoundException
	{
		return orderRepository.findById(orderid).orElseThrow(()->new UserNotFoundException("Order Doesn't Exist In Database"));
	}
	public Orders updateOrders(Orders orders1,long orderid) throws UserNotFoundException
	{
		Orders orders2=orderRepository.findById(orderid).orElseThrow(()->new UserNotFoundException("Order Doesn't Exist In Database"));
		orders2.setShippingaddress(orders1.getShippingaddress());
		orders2.setTrackingid(orders1.getTrackingid());
		
		orderRepository.save(orders2);
		return orders2;
		
	}
	public void deleteOrders(long orderid) throws UserNotFoundException
	{
		orderRepository.findById(orderid).orElseThrow(()->new UserNotFoundException("Order Doesn't Exist In Database"));
		orderRepository.deleteById(orderid);
	}
}
