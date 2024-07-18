package ir.maktabsharif115.jpa;

import ir.maktabsharif115.jpa.domain.Province;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;

public class JpaApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

        entityManager.getTransaction().begin();

        Province province = Province.builder()
                .name("merged4").build();
        Province mergedEntity = entityManager.merge(province);
        province.setName("merged5");
        mergedEntity.setName("merged6");

        entityManager.getTransaction().commit();

    }
}
