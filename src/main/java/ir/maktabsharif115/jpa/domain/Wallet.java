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
@Table(name = Wallet.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends BaseEntity<Long> {

    public static final String TABLE_NAME = "wallet";

    public static final String CASH = "cash";
    public static final String CREDIT = "credit";
    public static final String TOTAL = "total";

    @Column(name = CASH)
    private Long cash = 0L;

    @Column(name = CREDIT)
    private Long credit = 0L;

    @Column(name = TOTAL)
    private Long total = 0L;
}
