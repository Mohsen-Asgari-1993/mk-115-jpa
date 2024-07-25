package ir.maktabsharif115.jpa;

import com.github.javafaker.Faker;
import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;

public class JpaApplication {

    static Faker faker = new Faker();


    @SneakyThrows
    public static void main(String[] args) {

        initCustomers();

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
