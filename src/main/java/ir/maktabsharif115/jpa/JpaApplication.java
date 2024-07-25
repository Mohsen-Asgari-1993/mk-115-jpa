package ir.maktabsharif115.jpa;

import com.github.javafaker.Faker;
import ir.maktabsharif115.jpa.domain.User;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.SneakyThrows;

public class JpaApplication {

    static Faker faker = new Faker();


    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

        TypedQuery<User> typedQuery = entityManager.createQuery("from User u where u.id = :id", User.class);

//        EntityGraph<?> test = entityManager.createEntityGraph("test");
//        test.addAttributeNodes("roles", "roles.operations");

        typedQuery.setParameter("id", 1L)
                .setHint("jakarta.persistence.fetchgraph",
                        entityManager.getEntityGraph(User.FULL_GRAPH)
                );

        User user = typedQuery.getSingleResult();
        System.out.println(user.getId());

    }

}
