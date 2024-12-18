package co.com.ecommerce.application.usecases.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.application.dto.response.FrequentCustomersTopDto;
import co.com.ecommerce.domain.port.inound.user.FindFrequentCustomersTopUseCase;
import co.com.ecommerce.domain.port.outound.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FindFrequentCustomersTopUseCaseImpl implements FindFrequentCustomersTopUseCase {

  private final UserRepositoryPort userPort;

  @Override
  public List<FrequentCustomersTopDto> findFrequentCustomersTop() {
    return userPort.findFrequentCustomersTop();
  }
  
}
