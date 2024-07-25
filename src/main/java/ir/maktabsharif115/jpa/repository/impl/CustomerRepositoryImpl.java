package ir.maktabsharif115.jpa.repository.impl;

import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.dto.CustomerSearch;
import ir.maktabsharif115.jpa.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final EntityManager entityManager;

    @Override
    public List<Customer> findAll(CustomerSearch search) {

        String query = "select c from Customer c ";
        boolean isWhereClauseAdded = false;
        if (search.getFirstName() != null) {
            isWhereClauseAdded = true;
            query = query.concat("where ");
            query = query.concat("c.firstName like '%" + search.getFirstName() + "%' ");
        }
        if (search.getLastName() != null) {
            if (!isWhereClauseAdded) {
                isWhereClauseAdded = true;
                query = query.concat("where ");
            } else {
                query = query.concat("and ");
            }
            query = query.concat("c.lastName like '%" + search.getLastName() + "%' ");
        }
        if (search.getUsername() != null) {
            if (!isWhereClauseAdded) {
                isWhereClauseAdded = true;
                query = query.concat("where ");
            } else {
                query = query.concat("and ");
            }
            query = query.concat("c.username like '%" + search.getUsername() + "%' ");
        }
        if (search.getMobileNumber() != null) {
            if (!isWhereClauseAdded) {
                query = query.concat("where ");
            } else {
                query = query.concat("and ");
            }
            query = query.concat("c.mobileNumber like '%" + search.getMobileNumber() + "%' ");
        }

        return new ArrayList<>(entityManager.createQuery(query, Customer.class).getResultList());
    }
}
