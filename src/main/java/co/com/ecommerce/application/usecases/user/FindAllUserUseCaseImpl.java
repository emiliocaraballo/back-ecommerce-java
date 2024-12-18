package co.com.ecommerce.application.usecases.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.domain.model.User;
import co.com.ecommerce.domain.port.inound.user.FindAllUserUseCase;
import co.com.ecommerce.domain.port.outound.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FindAllUserUseCaseImpl implements FindAllUserUseCase {

  private final UserRepositoryPort userPort;

  @Override
  public List<User> findAll() {
    return userPort.findAll();
  }
  
}
