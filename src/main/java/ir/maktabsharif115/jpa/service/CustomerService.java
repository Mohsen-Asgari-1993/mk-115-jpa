package ir.maktabsharif115.jpa.service;

import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.dto.CustomerSearch;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll(CustomerSearch search);

}
