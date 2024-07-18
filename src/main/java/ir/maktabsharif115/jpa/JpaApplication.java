package ir.maktabsharif115.jpa;

import com.github.javafaker.Faker;
import ir.maktabsharif115.jpa.domain.Address;
import ir.maktabsharif115.jpa.domain.User;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaApplication {

    static Faker faker = new Faker();


    @SneakyThrows
    public static void main(String[] args) {

        ApplicationContext applicationContext = ApplicationContext.getInstance();
        EntityManager entityManager = applicationContext.getEntityManager();

//        initUsers(entityManager);

        List<User> users = entityManager.createQuery("""
                from User u join fetch u.manyToManyAddress join fetch u.oneToManyAddress
                join fetch u.manyToOneAddress join fetch u.oneToOneAddress
                """, User.class).getResultList();
        users.forEach(JpaApplication::printUserDetail);
    }

    private static void printUserDetail(User user) {
        Set<Address> manyToManyAddress = user.getManyToManyAddress();
        manyToManyAddress.size();
//        System.out.println("manyToManyAddress size: " + manyToManyAddress.size());
        Set<Address> oneToManyAddress = user.getOneToManyAddress();
        oneToManyAddress.size();
//        System.out.println("oneToManyAddress size: " + oneToManyAddress.size());
        Address oneToOneAddress = user.getOneToOneAddress();
        Long oneToOneAddressid = oneToOneAddress.getId();
//        System.out.println("oneToOneAddress id: " + oneToOneAddress.getId());
        Address manyToOneAddress = user.getManyToOneAddress();
        Long manyToOneAddressid = manyToOneAddress.getId();
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
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setUsername(faker.name().username());
            user.setPassword(faker.name().fullName());
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
