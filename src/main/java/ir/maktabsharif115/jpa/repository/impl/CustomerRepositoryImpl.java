package ir.maktabsharif115.jpa.repository.impl;

import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.dto.CustomerSearch;
import ir.maktabsharif115.jpa.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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

    @Override
    public List<Customer> findAllWithCriteria(CustomerSearch search) {
//        select c from Customer c <==> criteriaBuilder.createQuery(Customer.class)
//        select c.id from Customer c <==> criteriaBuilder.createQuery(Long.class)
//        select w from Customer c join c.wallet where c.id = 1 <==> criteriaBuilder.createQuery(Wallet.class)
//        select c.firstName from Customer c <==> criteriaBuilder.createQuery(String.class)
//        select c from Customer c where c.firstName like '%a%'
//        select c from Customer c where c.firstName like '%a% and c.lastName like '%a%'

//
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();
        fillFirstNamePredicate(search.getFirstName(), predicates, criteriaBuilder, customerRoot);
        fillLastNamePredicate(search.getLastName(), predicates, criteriaBuilder, customerRoot);
        fillUsernamePredicate(search.getUsername(), predicates, criteriaBuilder, customerRoot);
        fillMobileNumberPredicate(search.getMobileNumber(), predicates, criteriaBuilder, customerRoot);

        if (!predicates.isEmpty()) {
            query.where(
//                    predicates.size() == 1 ? predicates.get(0) :
                    criteriaBuilder.or(
                            predicates.toArray(new Predicate[0])
                    )
            );
        }

        return entityManager.createQuery(query).getResultList();
    }

    private static void fillFirstNamePredicate(String firstName, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<Customer> customerRoot) {
        addLikeQuery(firstName, predicates, criteriaBuilder, customerRoot, "firstName");
    }

    private static void fillLastNamePredicate(String lastName, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<Customer> customerRoot) {
        addLikeQuery(lastName, predicates, criteriaBuilder, customerRoot, "lastName");
    }

    private static void fillUsernamePredicate(String username, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<Customer> customerRoot) {
        addLikeQuery(username, predicates, criteriaBuilder, customerRoot, "username");
    }

    private static void addLikeQuery(String fieldValue, List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                     Root<?> customerRoot, String fieldName) {
        if (fieldValue != null) {
            predicates.add(
                    criteriaBuilder.like(
                            customerRoot.get(fieldName), "%" + fieldValue + "%"
                    )
            );
        }
    }

    private static void fillMobileNumberPredicate(String mobileNumber, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<Customer> customerRoot) {
        addLikeQuery(mobileNumber, predicates, criteriaBuilder, customerRoot, "mobileNumber");
    }
}
