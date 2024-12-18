package co.com.ecommerce.application.usecases.product;

import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.domain.port.outound.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreateProductUseCaseImplTest {

    @InjectMocks
    private CreateProductUseCaseImpl createProductUseCase;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    private Product product;

    @BeforeEach
    void setUp() {
        // Inicializar los mocks
        MockitoAnnotations.openMocks(this);

        
        product = new Product(null, "Producto Test", "Marca Test", "Descripción Test", "imagen.jpg", 100.0, 10, "disponible");
    }

    @Test
    void testCreateProduct() {
        // Configurar el comportamiento del repositorio mockeado
        when(productRepositoryPort.createProduct(any(Product.class))).thenReturn(product);

        // Llamar al método del caso de uso
        Product createdProduct = createProductUseCase.createProduct(product);

        // Verificar que el producto creado sea el esperado
        assertNotNull(createdProduct);
        assertEquals("Producto Test", createdProduct.getName());
        assertEquals(100.0, createdProduct.getPrice());

        // Verificar que el repositorio fue llamado una vez
        verify(productRepositoryPort, times(1)).createProduct(any(Product.class));
    }

    @Test
    void testCreateProductWithNegativeStock() {
        // Crear un producto con stock negativo
        Product invalidProduct = new Product(null, "Producto Invalido", "Marca Invalida", "Descripción Invalida", "imagen.jpg", 50.0, -5, "disponible");

        // Verificar que al intentar acceder al stock de un producto con stock negativo, se lance una excepción
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
          invalidProduct.getStock();  // Acceder al getter que valida el stock
        });
        // Verificar que el mensaje de la excepción sea el esperado
        assertEquals("La cantidad de productos no puede ser menor a 0", exception.getMessage());
    }

  }
