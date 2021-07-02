package com.ust.customerservice.repositorytest;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ust.customerservice.Repository.CustomerRepository;
import com.ust.customerservice.model.Customer;

@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;
	Customer customer = new Customer();

	@Before
	public Customer setUp() {
		Customer customer = new Customer();
		customer.setCustomerId("kkkk");
		customer.setFirstName("Mansi");
		customer.setLastName("Mohan");
		customer.setAddress("kkk mmm ppp");
		customer.setMobileNumber(908877665);
		customer.setEmailId("kkk@ppp");
		return customer;
	}

	@After
	public void tearDown() throws Exception {

		customerRepository.deleteAll();
	}

	@Test
	public void registerCustomerTest() {

		customerRepository.save(setUp());
		Customer fetchedcustomer = customerRepository.findById("kkkk").get();
		Assertions.assertEquals("kkkk", fetchedcustomer.getCustomerId());
	}

	@Test
	public void deleteCustomerTest() {

		Assertions.assertThrows(NoSuchElementException.class, () -> {
			customerRepository.delete(setUp());
			@SuppressWarnings("unused")
			Customer fetchedcustomer = customerRepository.findById("kkkk").get();
		});
	}

	@Test
	public void updateCustomerTest() {

		Assertions.assertThrows(NoSuchElementException.class, () -> {
			@SuppressWarnings("unused")
			Customer fetchedcustomer = customerRepository.findById("kkkk").get();
		});
	}

	@Test
	public void getCustomerbyIdTest() {

		Customer fetchedcustomer = customerRepository.findById("kkkk").get();
		Assertions.assertEquals("kkkk", fetchedcustomer.getCustomerId());
	}

	@Test
	public void getAllCustomerTest() {
		List<Customer> customer = customerRepository.findAll();
		Assertions.assertEquals(10, customer.size());
	}
}