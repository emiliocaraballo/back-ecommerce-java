package co.com.ecommerce.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    private String name;

    @NotBlank(message = "La marca del producto no puede estar vacío")
    private String tradeMark;

    @NotBlank(message = "La descripción del producto no puede estar vacío")
    private String description;

    @NotBlank(message = "La imagen del producto no puede estar vacío")
    private String image;

    @Positive(message = "El precio debe ser mayor a cero")
    private Double price;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

    @NotBlank(message = "El estado del producto no puede estar vacío")
    private String status;
}
