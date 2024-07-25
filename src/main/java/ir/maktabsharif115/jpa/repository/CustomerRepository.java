package ir.maktabsharif115.jpa.repository;

import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.dto.CustomerSearch;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll(CustomerSearch search);

    List<Customer> findAllWithCriteria(CustomerSearch search);
}
