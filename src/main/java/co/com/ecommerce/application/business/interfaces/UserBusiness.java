package co.com.ecommerce.application.business.interfaces;

import java.util.List;

import co.com.ecommerce.application.dto.request.LoginRequestDTO;
import co.com.ecommerce.application.dto.response.FrequentCustomersTopDto;
import co.com.ecommerce.domain.model.User;

public interface UserBusiness {
  public User createUser(User user);
  public User findByUsername(String username);
  public User updatedUser(User user);
  public User login(LoginRequestDTO login);
  public List<User> findAll();
  public List<FrequentCustomersTopDto> findFrequentCustomersTop();
}
