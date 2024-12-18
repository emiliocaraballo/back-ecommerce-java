package co.com.ecommerce.domain.port.outound;

import co.com.ecommerce.domain.model.Carts;

public interface CartRepositoryPort {
    public void saveCart(Carts cart);
    public Object findByUserId(Long userId);
}