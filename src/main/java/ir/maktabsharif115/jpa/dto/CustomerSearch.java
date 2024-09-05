
package ir.maktabsharif115.jpa.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSearch implements Serializable {

    private String firstName;

    private String lastName;

    private String username;

    private String mobileNumber;

    private Long minWallet;

    private Long maxWallet;

}
