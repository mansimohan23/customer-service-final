package com.ust.customerservice.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ust.customerservice.Repository.CustomerRepository;
import com.ust.customerservice.exception.CustomerAlreadyExsists;
import com.ust.customerservice.exception.CustomerNotFound;
import com.ust.customerservice.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository repo;

	@Override
	public boolean registerCustomer(Customer customer) throws CustomerAlreadyExsists {
		try {
			Customer c = repo.findByCustomerId(customer.getCustomerId());
			if (c == null) {
				repo.save(customer);
				return true;
			}
			throw new CustomerAlreadyExsists("Customer Already Exsists");
		} catch (CustomerAlreadyExsists e) {
			throw new CustomerAlreadyExsists("Customer Already Exsists");
		}
	}

	@Override
	public Customer updateCustomer(String customerId, Customer customer) throws CustomerNotFound {

		try {
			customer.setCustomerId(customerId);
			return repo.save(customer);
		} catch (NoSuchElementException e) {
			throw new CustomerNotFound("Customer not found");
		}
	}

	@Override
	public boolean deleteCustomer(String customerId) throws CustomerNotFound {

		try {
			Customer fecthedCustomer = repo.findById(customerId).get();
			if (fecthedCustomer != null) {
				repo.delete(fecthedCustomer);
			}
		} catch (NoSuchElementException exception) {
			throw new CustomerNotFound("Customer does not exists");
		}
		return true;
	}

	@Override
	public Customer getCustomerById(String customerId) throws CustomerNotFound {
		try {
			return repo.findById(customerId).get();
		} catch (NoSuchElementException e) {
			throw new CustomerNotFound("Customer not found");
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}
}
