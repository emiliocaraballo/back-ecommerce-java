package co.com.ecommerce.domain.port.inound.user;

import co.com.ecommerce.domain.model.User;

public interface UserCreateUseCase {
  public User createUser(User user);
}
