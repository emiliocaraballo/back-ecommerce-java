package co.com.ecommerce.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartDetails {
    private Long id;
    private Carts cart;
    private User user;
    private Product product;
    private Long quantity;
}
