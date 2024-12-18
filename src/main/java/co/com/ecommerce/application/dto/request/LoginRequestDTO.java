package co.com.ecommerce.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    private String username;

    @NotBlank(message = "La contraseña del usuario no puede estar vacío")
    private String password;

    private String rol;
}
