package ir.maktabsharif115.jpa.domain;

import ir.maktabsharif115.jpa.base.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Role.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity<Long> {

    public static final String TABLE_NAME = "roles";

    public static final String NAME = "name";

    @Column(name = NAME)
    private String name;

    @ManyToMany
    private Set<Operation> operations = new HashSet<>();
}
