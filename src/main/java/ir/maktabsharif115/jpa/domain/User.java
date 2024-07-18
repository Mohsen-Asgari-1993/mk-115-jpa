package ir.maktabsharif115.jpa.domain;

import ir.maktabsharif115.jpa.base.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = User.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> {

    public static final String TABLE_NAME = "users";

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String MOBILE_NUMBER = "mobile_number";
    public static final String USER_ID = "user_id";
    public static final String USERS_ADDRESS = "users_address";
    public static final String ADDRESS_ID = "address_id";

    @ManyToMany
    @JoinTable(
            name = USERS_ADDRESS,
            joinColumns = {
                    @JoinColumn(name = USER_ID, referencedColumnName = ID)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = ADDRESS_ID, referencedColumnName = ID)
            },
            indexes = {
                    @Index(columnList = USER_ID),
                    @Index(columnList = ADDRESS_ID)
            }
    )
    private Set<Address> addresses;

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    @Column(name = USERNAME, nullable = false)
    private String username;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = MOBILE_NUMBER)
    private String mobileNumber;

    @Override
    public String toString() {
        return "User{" +
               "id='" + getId() + '\'' +
               ", username='" + username + '\'' +
               '}';
    }
}
