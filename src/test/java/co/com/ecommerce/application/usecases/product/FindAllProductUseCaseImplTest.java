package co.com.ecommerce.application.usecases.product;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import co.com.ecommerce.application.dto.request.ProductSearchDto;
import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.domain.port.outound.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FindAllProductUseCaseImplTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private FindAllProductUseCaseImpl findAllProductUseCase;

    private ProductSearchDto searchDto;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        // Inicializar los productos de prueba
        product1 = new Product(1L, "Producto 1", "Marca 1", "Descripción 1", "imagen1.jpg", 50.0, 10, "disponible");
        product2 = new Product(2L, "Producto 2", "Marca 2", "Descripción 2", "imagen2.jpg", 100.0, 20, "disponible");

        // Inicializar el DTO de búsqueda
        searchDto = new ProductSearchDto();
    }

    @Test
    void testFindAllProducts() {
        // Configurar el comportamiento del repositorio mockeado
        when(productRepositoryPort.findAll(searchDto)).thenReturn(Arrays.asList(product1, product2));

        // Llamar al método del caso de uso
        List<Product> products = findAllProductUseCase.findAll(searchDto);

        // Verificar que la lista de productos no sea nula y tenga los productos esperados
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Producto 1", products.get(0).getName());
        assertEquals("Producto 2", products.get(1).getName());

        // Verificar que el repositorio fue llamado una vez
        verify(productRepositoryPort, times(1)).findAll(searchDto);
    }
}
