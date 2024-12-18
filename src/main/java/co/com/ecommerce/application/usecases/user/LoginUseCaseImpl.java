package co.com.ecommerce.application.usecases.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.application.dto.request.LoginRequestDTO;
import co.com.ecommerce.domain.model.User;
import co.com.ecommerce.domain.port.inound.user.LoginCreateUseCase;
import co.com.ecommerce.domain.port.outound.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LoginUseCaseImpl implements LoginCreateUseCase {
  private final UserRepositoryPort userPort;
  
  public User login(LoginRequestDTO user) {
    return userPort.login(user);
  }
}
