package ir.maktabsharif115.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User {

    public static final String IS_SUPER_ADMIN = "is_super_admin";

    @Column(name = IS_SUPER_ADMIN)
    private Boolean isSuperAdmin;

}
