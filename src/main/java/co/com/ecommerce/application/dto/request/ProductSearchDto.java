package co.com.ecommerce.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchDto {
    public String name;
    public String tradeMark;
    public String status;
}
