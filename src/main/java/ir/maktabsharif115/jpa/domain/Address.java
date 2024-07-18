package ir.maktabsharif115.jpa.domain;

import ir.maktabsharif115.jpa.base.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = Address.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address extends BaseEntity<Long> {

    public static final String TABLE_NAME = "address";

    public static final String ADDRESS = "address";
    public static final String POSTAL_CODE = "postal_code";

    @ManyToOne
//    user_id
    @JoinColumn(name = "u_i")
    private User user;

    @Column(name = ADDRESS)
    private String address;

    @Column(name = POSTAL_CODE)
    private String postalCode;
}
