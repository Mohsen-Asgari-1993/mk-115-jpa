package ir.maktabsharif115.jpa.service.impl;

import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.dto.CustomerSearch;
import ir.maktabsharif115.jpa.repository.CustomerRepository;
import ir.maktabsharif115.jpa.service.CustomerService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository baseRepository;

    @Override
    public List<Customer> findAll(CustomerSearch search) {
        return baseRepository.findAllWithCriteria(search);
    }

}
