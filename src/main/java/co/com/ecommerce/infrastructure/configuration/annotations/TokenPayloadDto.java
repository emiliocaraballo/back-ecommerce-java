package co.com.ecommerce.infrastructure.configuration.annotations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TokenPayloadDto {
    private String username;
    private Long customerId;
    private Long userId;
    private int distributorId;
    private String rol;
}
