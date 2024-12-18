package co.com.ecommerce.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrequentCustomerResponse {
    private Long id;
    private String name;
    private String lastName;
    private Long totalSales;
    private String email;
}
