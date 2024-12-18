package co.com.ecommerce.infrastructure.outadapter.persistence.mapper;

import co.com.ecommerce.application.dto.request.ProductCreateDTO;
import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setPrice(entity.getPrice());
        product.setStock(entity.getStock());
        product.setImage(entity.getImage());
        product.setStatus(entity.getStatus().name());
        product.setTradeMark(entity.getTradeMark());
        return product;
    }

    public ProductEntity toEntity(Product domain) {
        if (domain == null) {
            return null;
        }
        ProductEntity entity = new ProductEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setPrice(domain.getPrice());
        entity.setStock(domain.getStock());
        entity.setImage(domain.getImage());
        entity.setStatus(ProductEntity.ProductStatus.valueOf(domain.getStatus()));
        entity.setTradeMark(domain.getTradeMark());
        // Ignorar los campos especificados
        entity.setCreatedAt(null);
        entity.setCreatedBy(null);
        entity.setUpdatedAt(null);
        entity.setUpdatedBy(null);
        return entity;
    }

    public Product dtoToModel(ProductCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(null);
        product.setTradeMark(dto.getTradeMark());
        product.setName(dto.getName());
        product.setImage(dto.getImage());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setStatus(dto.getStatus());
        return product;
    }

    public ProductEntity merge(ProductEntity newData, ProductEntity existingData) {
        if (newData.getName() != null) {
            existingData.setName(newData.getName());
        }
        if (newData.getDescription() != null) {
            existingData.setDescription(newData.getDescription());
        }
        if (newData.getPrice() != null) {
            existingData.setPrice(newData.getPrice());
        }
        if (newData.getStock() != null) {
            existingData.setStock(newData.getStock());
        }

        if (newData.getImage() != null) {
            existingData.setImage(newData.getImage());
        }

        if (newData.getStatus() != null) {
            existingData.setStatus(newData.getStatus());
        }

        if (newData.getTradeMark() != null) {
            existingData.setTradeMark(newData.getTradeMark());
        }

        // Agrega otros campos si es necesario
        return existingData;
    }
}
