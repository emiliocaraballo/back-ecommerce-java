package co.com.ecommerce.domain.port.inound.user;

import co.com.ecommerce.application.dto.request.LoginRequestDTO;
import co.com.ecommerce.domain.model.User;

public interface LoginCreateUseCase {
    public User login(LoginRequestDTO login);
}
