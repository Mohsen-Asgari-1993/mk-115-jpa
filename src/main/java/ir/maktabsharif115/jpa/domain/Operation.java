package ir.maktabsharif115.jpa.domain;

import ir.maktabsharif115.jpa.base.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Operation.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//public class Permission
//public class Authority
public class Operation extends BaseEntity<Long> {

    public static final String TABLE_NAME = "operations";

    public static final String NAME = "name";

    @Column(name = NAME)
    private String name;
}
