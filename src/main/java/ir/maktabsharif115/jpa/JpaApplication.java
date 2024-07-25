package ir.maktabsharif115.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.dto.CustomerCardboardDTO;
import ir.maktabsharif115.jpa.dto.CustomerSearch;
import ir.maktabsharif115.jpa.repository.CustomerRepository;
import ir.maktabsharif115.jpa.repository.impl.CustomerRepositoryImpl;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JpaApplication {

    static Faker faker = new Faker();


    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

        Scanner scanner = new Scanner(System.in);
        String jsonSearch = scanner.nextLine();

        ObjectMapper objectMapper = new ObjectMapper();
        CustomerSearch customerSearch = objectMapper.readValue(
                jsonSearch, CustomerSearch.class
        );


        CustomerRepository customerRepository = new CustomerRepositoryImpl(entityManager);
        searchOnCustomersV1(customerRepository, customerSearch, objectMapper);
//        searchOnCustomersV2(customerRepository, customerSearch, objectMapper);

    }

    private static void searchOnCustomersV1(CustomerRepository customerRepository, CustomerSearch customerSearch, ObjectMapper objectMapper) throws JsonProcessingException {
        List<Customer> customers = customerRepository.findAll(customerSearch);
        System.out.println("total: " + customers.size());
        List<CustomerCardboardDTO> results = new ArrayList<>();
        if (!customers.isEmpty()) {
            customers.forEach(
                    customer -> results.add(
                            new CustomerCardboardDTO(
                                    customer.getId(),
                                    customer.getFirstName(),
                                    customer.getLastName(),
                                    customer.getUsername(),
                                    customer.getMobileNumber()
                            )
                    )
            );
        }
        System.out.println(
                objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(results)
        );
    }

    private static void searchOnCustomersV2(CustomerRepository customerRepository,
                                            CustomerSearch customerSearch,
                                            ObjectMapper objectMapper) throws JsonProcessingException {
        System.out.println(
                objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(
                                customerRepository.findAll(customerSearch)
                                        .stream().map(customer -> new CustomerCardboardDTO(
                                                        customer.getId(),
                                                        customer.getFirstName(),
                                                        customer.getLastName(),
                                                        customer.getUsername(),
                                                        customer.getMobileNumber()
                                                )
                                        ).toList()
                        )
        );
    }

    private static void initCustomers() {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

        entityManager.getTransaction().begin();

        for (int i = 0; i < 200; i++) {
            Customer customer = new Customer();
            customer.setFirstName(faker.name().firstName());
            customer.setLastName(faker.name().lastName());
            customer.setUsername(faker.name().username());
            customer.setMobileNumber(faker.number().digits(11));
            entityManager.persist(customer);
        }

        entityManager.getTransaction().commit();
    }

}
