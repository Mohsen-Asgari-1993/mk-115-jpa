package ir.maktabsharif115.jpa;

import ir.maktabsharif115.jpa.domain.Province;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;

public class JpaApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

        entityManager.getTransaction().begin();

        Province province = entityManager.find(Province.class, 152L);
        entityManager.detach(province);
        entityManager.persist(province);
//        province = entityManager.merge(province);
//        province.setName(province.getName().concat(province.getName()));

        entityManager.getTransaction().commit();

    }
}
