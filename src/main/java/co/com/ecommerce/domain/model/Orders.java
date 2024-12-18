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
public class Orders {
    private Long id;
    private User user;
    private Date createdAt;
    private Date updatedAt;
    private String status;
    private Double total;
    private String paymentStatus;
}
