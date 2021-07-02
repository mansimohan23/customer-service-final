package com.ust.customerservice.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.ust.customerservice.exception.CustomerAlreadyExsists;
import com.ust.customerservice.exception.CustomerNotFound;
import com.ust.customerservice.model.Customer;

@Service
public interface CustomerService {

	boolean registerCustomer(Customer customer) throws CustomerAlreadyExsists;

	Customer updateCustomer(String customerId, Customer customer) throws CustomerNotFound;

	boolean deleteCustomer(String customerId) throws CustomerNotFound;

	Customer getCustomerById(String customerId) throws CustomerNotFound;

	public List<Customer> getAllCustomers();

}
