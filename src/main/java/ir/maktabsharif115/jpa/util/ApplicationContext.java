package ir.maktabsharif115.jpa.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktabsharif115.jpa.repository.CustomerRepository;
import ir.maktabsharif115.jpa.repository.impl.CustomerRepositoryImpl;
import ir.maktabsharif115.jpa.service.CustomerService;
import ir.maktabsharif115.jpa.service.impl.CustomerServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

public class ApplicationContext {

    private ApplicationContext() {
    }

    private static ApplicationContext applicationContext;

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    private EntityManagerFactory emf;

    private EntityManager em;

    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @Getter
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("default");
        }
        return emf;
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            em = getEntityManagerFactory().createEntityManager();
        }
        return em;
    }

    public CustomerRepository getCustomerRepository() {
        if (customerRepository == null) {
            customerRepository = new CustomerRepositoryImpl(getEntityManager());
        }
        return customerRepository;
    }

    public CustomerService getCustomerService() {
        if (customerService == null) {
            customerService = new CustomerServiceImpl(getCustomerRepository());
        }
        return customerService;
    }
}
