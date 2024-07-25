package ir.maktabsharif115.jpa;

import com.github.javafaker.Faker;
import ir.maktabsharif115.jpa.domain.Address;
import ir.maktabsharif115.jpa.domain.Admin;
import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.domain.User;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.Set;

public class JpaApplication {

    static Faker faker = new Faker();


    @SneakyThrows
    public static void main(String[] args) {

        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(new User());
        entityManager.persist(new Admin());
        entityManager.persist(new Customer());


        entityManager.getTransaction().commit();


        System.out.println("users : ");
        entityManager.createQuery(
                "from User", User.class
        ).getResultList().forEach(u -> System.out.println(u.getId() + " " + u.getFirstName()));


        System.out.println("admins : ");
        entityManager.createQuery(
                "from Admin", Admin.class
        ).getResultList().forEach(u -> System.out.println(u.getId() + " " + u.getFirstName()));

        System.out.println("Customers : ");
        entityManager.createQuery(
                "from Customer", Customer.class
        ).getResultList().forEach(u -> System.out.println(u.getId() + " " + u.getFirstName()));
    }

    private static Set<Address> getRandomAddress(EntityManager entityManager, int numberOfAddress) {
        Set<Address> addresses = new HashSet<>();
        for (int j = 0; j < numberOfAddress; j++) {
            Address address = getAddress(entityManager);
            addresses.add(address);
        }
        return addresses;
    }

    private static Address getAddress(EntityManager entityManager) {
        Address address = new Address();
        address.setAddress(faker.address().fullAddress());
        address.setPostalCode(faker.address().zipCode());
        entityManager.persist(address);
        return address;
    }
}
