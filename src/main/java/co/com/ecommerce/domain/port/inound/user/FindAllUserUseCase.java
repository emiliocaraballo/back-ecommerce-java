package co.com.ecommerce.domain.port.inound.user;

import java.util.List;

import co.com.ecommerce.domain.model.User;

public interface FindAllUserUseCase {
  public List<User> findAll();
}
