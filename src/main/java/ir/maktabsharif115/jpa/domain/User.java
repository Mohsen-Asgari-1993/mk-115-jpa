package ir.maktabsharif115.jpa.domain;

import ir.maktabsharif115.jpa.base.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = User.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> {

    public static final String TABLE_NAME = "users";


    public static final String MOBILE_NUMBER = "mobile_number";
    public static final String USER_ID = "user_id";
    public static final String USERS_ADDRESS = "users_address";
    public static final String ADDRESS_ID = "address_id";
    public static final String USER_MOBILE_NUMBERS = "user_mobile_numbers";
    public static final String USER_DETAILS = "user_details";

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
    private Set<Address> manyToManyAddress = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "oneToManyAddress_id")
    private Set<Address> oneToManyAddress = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manyToOneAddress_id")
    private Address manyToOneAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oneToOneAddress_id")
    private Address oneToOneAddress;

    @Embedded
    private UserDetail userDetail;

    @Column(name = MOBILE_NUMBER)
    private String mobileNumber;

    @ElementCollection
    @CollectionTable(
            name = USER_MOBILE_NUMBERS,
            joinColumns = @JoinColumn(name = USER_ID, referencedColumnName = ID)
    )
    @Column(name = MOBILE_NUMBER)
    private Set<String> mobileNumbers = new TreeSet<>();

    @ElementCollection
    @CollectionTable(
            name = USER_DETAILS,
            joinColumns = @JoinColumn(name = USER_ID, referencedColumnName = ID)
    )
    private Set<UserDetail> userDetails = new HashSet<>();
}
