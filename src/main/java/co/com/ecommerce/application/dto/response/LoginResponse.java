package co.com.ecommerce.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
  private Long id;
  private String token;
  private String documentType;
  private String documentNumber;
  private String email;
  private String name;
  private String lastName;
  private String phone;
  private String rol;
}
