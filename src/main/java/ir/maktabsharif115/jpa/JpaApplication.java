package ir.maktabsharif115.jpa;

import com.github.javafaker.Faker;
import ir.maktabsharif115.jpa.domain.Address;
import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.domain.Wallet;
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

        Customer customer = entityManager.find(Customer.class, 1L);
        System.out.println("customer firstName before change: " + customer.getFirstName());
        customer.setFirstName(
                faker.name().firstName()
        );
        System.out.println("customer firstName before refresh: " + customer.getFirstName());
        entityManager.refresh(customer);
        System.out.println("customer firstName after refresh: " + customer.getFirstName());

        entityManager.getTransaction().commit();
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

    private static void create(Customer customer, EntityManager entityManager) {
//        Wallet wallet = new Wallet();
//        entityManager.persist(wallet);
        customer.setWallet(new Wallet());
        entityManager.persist(customer);
    }
}
