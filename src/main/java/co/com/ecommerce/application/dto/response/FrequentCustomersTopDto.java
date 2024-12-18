package co.com.ecommerce.application.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FrequentCustomersTopDto {
    private Long id;
    private String name;
    private String lastName;
    private Long totalSales;
    private Long totalAmount;

    public FrequentCustomersTopDto(Long id, String name, String lastName, Long totalSales, Long totalAmount) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.totalSales = totalSales;
        this.totalAmount = totalAmount;
    }
}
