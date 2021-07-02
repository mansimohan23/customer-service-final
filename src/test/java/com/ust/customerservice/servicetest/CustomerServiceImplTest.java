package com.ust.customerservice.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.ust.customerservice.Repository.CustomerRepository;
import com.ust.customerservice.exception.CustomerAlreadyExsists;
import com.ust.customerservice.exception.CustomerNotFound;
import com.ust.customerservice.model.Customer;
import com.ust.customerservice.service.CustomerServiceImpl;

@SpringBootTest
class CustomerServiceImplTest {

	@Mock
	CustomerRepository repo;

	@InjectMocks
	CustomerServiceImpl customerService;

	List<Customer> customerList = new ArrayList<>();

	Optional<Customer> options;

	Customer customer = new Customer();

	@Test
	public void updateCustomerTest() throws CustomerNotFound {

		Customer fetchcustomer = customerService.updateCustomer(customer.getCustomerId(), customer);
		assertEquals(null, fetchcustomer);

	}

	@Test
	public void deleteCustomerTest() throws CustomerNotFound {
		Assertions.assertThrows(NullPointerException.class, () -> {
			Mockito.when(repo.findById(customer.getCustomerId())).thenReturn(options);
			boolean flag = customerService.deleteCustomer(customer.getCustomerId());
			Assertions.assertEquals(true, flag);
		});

	}

	@Test
	public void getCustomerByIdTest() throws CustomerNotFound {
		Assertions.assertThrows(NullPointerException.class, () -> {
			Mockito.when(repo.findById(customer.getCustomerId())).thenReturn(options);
			Customer fetchedcustomer = customerService.getCustomerById(customer.getCustomerId());
			assertEquals(customer, fetchedcustomer);
		});

	}

	@Test
	public void getAllCustomerTest() {
		Mockito.when(repo.findAll()).thenReturn(customerList);
		customerService.getAllCustomers();
		assertEquals(customerList, customerList);

	}

}
