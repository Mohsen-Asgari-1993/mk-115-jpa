package ir.maktabsharif115.jpa.repository.impl;

import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.domain.Customer_;
import ir.maktabsharif115.jpa.domain.Wallet;
import ir.maktabsharif115.jpa.domain.Wallet_;
import ir.maktabsharif115.jpa.dto.CustomerSearch;
import ir.maktabsharif115.jpa.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
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
//        fillWalletPredicate(search, predicates, criteriaBuilder, customerRoot);
        fillWalletPredicateWithSubquery(search, predicates, criteriaBuilder, query, customerRoot);
        if (!predicates.isEmpty()) {
            query.where(
//                    predicates.size() == 1 ? predicates.get(0) :
                    criteriaBuilder.and(
                            predicates.toArray(new Predicate[0])
                    )
            );
        }

        return entityManager.createQuery(query).getResultList();
    }

    private static void fillFirstNamePredicate(String firstName, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<Customer> customerRoot) {
        addLikeQuery(firstName, predicates, criteriaBuilder, customerRoot, Customer_.FIRST_NAME);
    }

    private static void fillLastNamePredicate(String lastName, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<Customer> customerRoot) {
        addLikeQuery(lastName, predicates, criteriaBuilder, customerRoot, Customer_.LAST_NAME);
    }

    private static void fillUsernamePredicate(String username, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<Customer> customerRoot) {
        addLikeQuery(username, predicates, criteriaBuilder, customerRoot, Customer_.USERNAME);
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
        addLikeQuery(mobileNumber, predicates, criteriaBuilder, customerRoot, Customer_.MOBILE_NUMBER);
    }

    private void fillWalletPredicate(CustomerSearch search, List<Predicate> predicates,
                                     CriteriaBuilder criteriaBuilder, Root<Customer> customerRoot) {
//        entityManager.createQuery("select c from Customer c join c.wallet w where w.total > 100");
        if (search.getMinWallet() != null || search.getMaxWallet() != null) {
            Join<Customer, Wallet> walletJoin = customerRoot.join(Customer_.wallet, JoinType.INNER);
            if (search.getMaxWallet() != null) {
                predicates.add(
                        criteriaBuilder.lt(
                                walletJoin.get(Wallet_.TOTAL), search.getMaxWallet()
                        )
                );
            }
            if (search.getMinWallet() != null) {
                predicates.add(
                        criteriaBuilder.gt(
                                walletJoin.get(Wallet_.TOTAL), search.getMinWallet()
                        )
                );
            }
        }
    }

    private void fillWalletPredicateWithSubquery(CustomerSearch search, List<Predicate> predicates,
                                                 CriteriaBuilder criteriaBuilder, CriteriaQuery<Customer> query,
                                                 Root<Customer> customerRoot) {
        if (search.getMinWallet() != null || search.getMaxWallet() != null) {

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Wallet> walletRoot = subquery.from(Wallet.class);
            subquery.select(walletRoot.get(Wallet_.ID));
            subquery.where(
                    criteriaBuilder.gt(walletRoot.get(Wallet_.TOTAL), search.getMinWallet())
            );

            predicates.add(
                    criteriaBuilder.in(customerRoot.get(Customer_.WALLET_ID))
                            .value(subquery)
            );
        }
    }
}
