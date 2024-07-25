
package ir.maktabsharif115.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearch implements Serializable {

    private String firstName;

    private String lastName;

    private String username;

    private String mobileNumber;

    private Long minWallet;

    private Long maxWallet;

}
