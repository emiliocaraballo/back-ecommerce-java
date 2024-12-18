package co.com.ecommerce.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    private String name;
    
    @NotBlank(message = "La apellido del usuario no puede estar vacío")
    private String lastName;

    @NotBlank(message = "La direccion de correo electrónico del usuario no puede estar vacío")
    private String email;

    @NotBlank(message = "El teléfono del usuario no puede estar vacío")
    private String phone;

    @NotBlank(message = "El rol del usuario no puede estar vacío")
    private String rol;

    @NotBlank(message = "El documento del usuario no puede estar vacío")
    private String documentNumber;

    @NotBlank(message = "El tipo de documento del usuario no puede estar vacío")
    private String documentType;

    @NotBlank(message = "La contraseña del usuario no puede estar vacío")
    private String password;
}
