package ir.maktabsharif115.jpa.domain;

import ir.maktabsharif115.jpa.base.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = User.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs(
        value = {
                @NamedEntityGraph(
                        name = User.FULL_GRAPH,
                        attributeNodes = {
                                @NamedAttributeNode(value = "roles", subgraph = "roles_subgraph")
                        },
                        subgraphs = {
                                @NamedSubgraph(
                                        name = "roles_subgraph",
                                        attributeNodes = {
                                                @NamedAttributeNode(value = "operations")
                                        }
                                )
                        }
                )
        }
)
public class User extends BaseEntity<Long> {

    public static final String TABLE_NAME = "users";
    public static final String FULL_GRAPH = "users_fullGraph";

    public static final String MOBILE_NUMBER = "mobile_number";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    @Column(name = USERNAME)
    private String username;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = MOBILE_NUMBER)
    private String mobileNumber;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    private Role role;
}
