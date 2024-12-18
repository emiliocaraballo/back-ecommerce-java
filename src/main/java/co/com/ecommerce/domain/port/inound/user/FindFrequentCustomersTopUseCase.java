package co.com.ecommerce.domain.port.inound.user;

import java.util.List;

import co.com.ecommerce.application.dto.response.FrequentCustomersTopDto;

public interface FindFrequentCustomersTopUseCase {
  public List<FrequentCustomersTopDto> findFrequentCustomersTop();
}
