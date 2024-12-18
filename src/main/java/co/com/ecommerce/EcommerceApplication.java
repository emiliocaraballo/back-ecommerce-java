package co.com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"co.com.ecommerce"})
@EnableJpaRepositories(basePackages = "co.com.ecommerce.infrastructure.outadapter.persistence.repository")
@EntityScan(basePackages = "co.com.ecommerce.infrastructure.outadapter.persistence.entity")
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
