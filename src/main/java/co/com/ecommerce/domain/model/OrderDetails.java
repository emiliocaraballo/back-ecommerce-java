package co.com.ecommerce.domain.model;

import java.sql.Date;

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
public class OrderDetails {
    private Long id;
    private User user;
    private Orders order;
    private Product product;
    private Double quantity;
    private Double price;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
