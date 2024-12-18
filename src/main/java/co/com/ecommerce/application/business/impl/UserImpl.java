package co.com.ecommerce.application.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.application.business.interfaces.UserBusiness;
import co.com.ecommerce.application.dto.request.LoginRequestDTO;
import co.com.ecommerce.application.dto.response.FrequentCustomersTopDto;
import co.com.ecommerce.domain.model.User;
import co.com.ecommerce.domain.port.inound.user.FindAllUserUseCase;
import co.com.ecommerce.domain.port.inound.user.FindFrequentCustomersTopUseCase;
import co.com.ecommerce.domain.port.inound.user.LoginCreateUseCase;
import co.com.ecommerce.domain.port.inound.user.UpdatedUserUseCase;
import co.com.ecommerce.domain.port.inound.user.UserCreateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserImpl implements UserBusiness, UserCreateUseCase, LoginCreateUseCase, UpdatedUserUseCase, FindAllUserUseCase, FindFrequentCustomersTopUseCase {
  private final UserCreateUseCase createUserUseCase;
  private final LoginCreateUseCase loginUseCase;
  private final UpdatedUserUseCase updateUserUseCase;
  private final FindAllUserUseCase findAllUserUseCase;
  private final FindFrequentCustomersTopUseCase findFrequentCustomersTopUseCase;

  @Override
  public User createUser(User user) {
    return createUserUseCase.createUser(user);
  }

  @Override
  public User updatedUser(User user) {
    return this.updateUserUseCase.updatedUser(user);
  }

  @Override
  public User login(LoginRequestDTO login) {
    return loginUseCase.login(login);
  }


  @Override
  public User findByUsername(String username) {
    return null;
  }

  @Override
  public List<User> findAll() {
    return findAllUserUseCase.findAll();
  }

  @Override
  public List<FrequentCustomersTopDto> findFrequentCustomersTop() {
    List<FrequentCustomersTopDto> response = findFrequentCustomersTopUseCase.findFrequentCustomersTop();
    log.info("end process: Finding frequent customers top: {}", response);
    return response;
  }
}
