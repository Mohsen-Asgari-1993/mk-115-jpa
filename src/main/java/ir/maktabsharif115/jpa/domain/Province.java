package ir.maktabsharif115.jpa.domain;


import ir.maktabsharif115.jpa.domain.enumeration.ProvinceType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = Province.TABLE_NAME,
        indexes = {
                @Index(columnList = Province.NAME),
                @Index(columnList = Province.NAME + "," + Province.PROVINCE_TYPE)
        }
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Province {

    public static final String TABLE_NAME = "province";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PROVINCE_TYPE = "province_type";

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    @GeneratedValue
    @Column(name = ID)
    private Long id;

    @Column(name = NAME, columnDefinition = "varchar")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = PROVINCE_TYPE)
    private ProvinceType provinceType;
}
