package ir.maktabsharif115.jpa;

import ir.maktabsharif115.jpa.domain.Province;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;

public class JpaApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

        entityManager.getTransaction().begin();

        Province province = entityManager.find(Province.class, 1L);

        System.out.println(province);

        province.setName("tehran");
        System.out.println(province);

        entityManager.getTransaction().commit();


//        Province province = entityManager.find(Province.class, 1L);
//        System.out.println(province);

    }
}
