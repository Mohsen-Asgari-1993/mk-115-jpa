package ir.maktabsharif115.jpa;

import com.github.javafaker.Faker;
import ir.maktabsharif115.jpa.domain.Address;
import ir.maktabsharif115.jpa.domain.User;
import ir.maktabsharif115.jpa.domain.UserDetail;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.Set;

public class JpaApplication {

    static Faker faker = new Faker();


    @SneakyThrows
    public static void main(String[] args) {

        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

        TypedQuery<User> typedQuery = entityManager.createQuery("from User u", User.class);
//        offset
        typedQuery.setFirstResult(35);
//        size
        typedQuery.setMaxResults(10);
        System.out.println(typedQuery.getResultList().size());


    }

    private static void printUserDetail(User user) {
        Set<Address> manyToManyAddress = user.getManyToManyAddress();
        System.out.println(manyToManyAddress.size());
//        System.out.println("manyToManyAddress size: " + manyToManyAddress.size());
        Set<Address> oneToManyAddress = user.getOneToManyAddress();
        System.out.println(oneToManyAddress.size());
//        System.out.println("oneToManyAddress size: " + oneToManyAddress.size());
        Address oneToOneAddress = user.getOneToOneAddress();
        System.out.println(oneToOneAddress.getAddress());
//        System.out.println("oneToOneAddress id: " + oneToOneAddress.getId());
        Address manyToOneAddress = user.getManyToOneAddress();
        System.out.println(manyToOneAddress.getAddress());
//        System.out.println("manyToOneAddress id: " + manyToOneAddress.getId());
    }

    private static void initUsers(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setManyToManyAddress(getRandomAddress(entityManager, 4));
            user.setOneToManyAddress(getRandomAddress(entityManager, 2));
            user.setManyToOneAddress(getAddress(entityManager));
            user.setOneToOneAddress(getAddress(entityManager));
            user.setUserDetail(
                    new UserDetail(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            faker.name().username(),
                            faker.name().fullName()
                    )
            );
            user.getMobileNumbers().add(faker.number().digits(11));
            user.getUserDetails().add(
                    new UserDetail(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            faker.name().username(),
                            faker.name().fullName()
                    )
            );
//            user.setFirstName(faker.name().firstName());
//            user.setLastName(faker.name().lastName());
//            user.setUsername(faker.name().username());
//            user.setPassword(faker.name().fullName());
            user.setMobileNumber(faker.number().digits(11));
            entityManager.persist(user);
        }
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
}
