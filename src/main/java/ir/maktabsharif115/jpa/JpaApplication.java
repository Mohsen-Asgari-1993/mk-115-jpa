package ir.maktabsharif115.jpa;

import ir.maktabsharif115.jpa.domain.Address;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;

public class JpaApplication {

    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

        entityManager.getTransaction().begin();

        Address address = entityManager.find(Address.class, 1L);
        System.out.println(address);

        entityManager.getTransaction().commit();


    }
}
