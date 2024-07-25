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

        TypedQuery<User> typedQuery = entityManager.createQuery("from User u ", User.class);

//        EntityGraph<?> test = entityManager.createEntityGraph("test");
//        test.addAttributeNodes("roles", "roles.operations");

        typedQuery
                .setHint("jakarta.persistence.fetchgraph",
                        entityManager.getEntityGraph(User.FULL_GRAPH)
                ).setMaxResults(5);

        System.out.println(typedQuery.getResultList().size());

    }

}
