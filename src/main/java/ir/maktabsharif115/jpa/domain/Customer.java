package ir.maktabsharif115.jpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {

    public static final String PASSWORD_VALID_DATE = "password_valid_date";

    @Column(name = PASSWORD_VALID_DATE)
    private ZonedDateTime passwordValidDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Wallet wallet;
}
