package co.com.ecommerce.domain.port.outound;

import java.util.List;

import co.com.ecommerce.application.dto.request.LoginRequestDTO;
import co.com.ecommerce.application.dto.response.FrequentCustomersTopDto;
import co.com.ecommerce.domain.model.User;

public interface UserRepositoryPort {
  public User create(User user);
  public User login(LoginRequestDTO login);
  public User findByUsername(String username);
  public List<User> findAll();
  public User updated(User user);
  public List<FrequentCustomersTopDto> findFrequentCustomersTop();
}