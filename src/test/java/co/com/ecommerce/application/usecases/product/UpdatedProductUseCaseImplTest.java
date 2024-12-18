package co.com.ecommerce.application.usecases.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.domain.port.outound.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UpdatedProductUseCaseImplTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private UpdatedProductUseCaseImpl updatedProductUseCase;

    private Product product;

    @BeforeEach
    void setUp() {
      // Inicializar los mocks
        MockitoAnnotations.openMocks(this);
        
        product = new Product(1L, "Producto Test", "Marca Test", "Descripción Test", "imagen.jpg", 100.0, 10, "disponible");
    }

    @Test
    void testUpdatedProduct() {
        // Configurar el comportamiento del repositorio mockeado
        when(productRepositoryPort.updated(any(Product.class))).thenReturn(product);

        // Llamar al método del caso de uso
        Product updatedProduct = updatedProductUseCase.updatedProduct(product);

        // Verificar que el producto actualizado sea el esperado
        assertNotNull(updatedProduct);
        assertEquals("Producto Test", updatedProduct.getName());
        assertEquals(100.0, updatedProduct.getPrice());

        // Verificar que el repositorio fue llamado una vez
        verify(productRepositoryPort, times(1)).updated(any(Product.class));
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
