package ir.maktabsharif115.jpa;

import ir.maktabsharif115.jpa.domain.Province;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;

public class JpaApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

        entityManager.getTransaction().begin();
        Province oldProvince = entityManager.find(Province.class, 2L);
        oldProvince.setName("11");
        System.out.println("em contain after find: " + entityManager.contains(oldProvince));
        entityManager.remove(oldProvince);
        System.out.println("em contain after remove: " + entityManager.contains(oldProvince));
        entityManager.persist(oldProvince);
        System.out.println("em contain after persist: " + entityManager.contains(oldProvince));
//        System.out.println("detach: " + entityManager.contains(oldProvince));
        entityManager.getTransaction().commit();

//        Province province = entityManager.find(Province.class, 1L);
//        System.out.println(province);

    }
}
