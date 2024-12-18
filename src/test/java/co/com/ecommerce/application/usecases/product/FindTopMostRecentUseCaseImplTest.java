package co.com.ecommerce.application.usecases.product;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.domain.port.outound.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) 
class FindTopMostRecentUseCaseImplTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort; // Mock del repositorio

    @InjectMocks
    private FindTopMostRecentUseCaseImpl findTopMostRecentUseCase; // Servicio a probar

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        // Inicializar los productos de prueba
        product1 = new Product(1L, "Producto 1", "Marca 1", "Descripción 1", "imagen1.jpg", 50.0, 10, "disponible");
        product2 = new Product(2L, "Producto 2", "Marca 2", "Descripción 2", "imagen2.jpg", 100.0, 20, "disponible");
    }

    @Test
    void testFindTopMostRecent() {
        // Configurar el comportamiento del mock
        when(productRepositoryPort.findTopMostRecent()).thenReturn(Arrays.asList(product1, product2));
    
        // Ejecutar el caso de uso
        List<Product> result = findTopMostRecentUseCase.findTopMostRecent();
    
        // Verificar que el resultado no es nulo y contiene los productos esperados
        assertNotNull(result);
        assertEquals(2, result.size());
    
        // Verificar que los nombres de los productos son correctos
        assertEquals("Producto 1", result.get(0).getName());  // Corregido aquí
        assertEquals("Producto 2", result.get(1).getName());  // Corregido aquí
    
        // Verificar que el repositorio fue llamado
        verify(productRepositoryPort, times(1)).findTopMostRecent();
    }

    @Test
    void testFindTopMostRecentEmptyList() {
        // Configurar el mock para devolver una lista vacía
        when(productRepositoryPort.findTopMostRecent()).thenReturn(Arrays.asList());

        // Ejecutar el caso de uso
        List<Product> result = findTopMostRecentUseCase.findTopMostRecent();

        // Verificar que el resultado es una lista vacía
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verificar que el repositorio fue llamado
        verify(productRepositoryPort, times(1)).findTopMostRecent();
    }
}
