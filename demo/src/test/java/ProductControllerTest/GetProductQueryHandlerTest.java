package ProductControllerTest;

import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.NoBsSpringBootApplication;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.queryhandlers.GetProductQueryHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = NoBsSpringBootApplication.class)
public class GetProductQueryHandlerTest {

    @InjectMocks
    private GetProductQueryHandler getProductQueryHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProductQueryHandler_validId_returnsProductDTO() {
        Product product = new Product();
        product.setId(1);
        product.setPrice(9.98);
        product.setName("deshaan");
        product.setDescription("Dark");
        product.setQuantity(10);

        ProductDTO expectedDTO = new ProductDTO(product);
        //given
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        //when
        ResponseEntity<ProductDTO> actualResponse = getProductQueryHandler.execute(product.getId());
        //then
        assertEquals(expectedDTO, actualResponse.getBody());
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());

    }

    @Test
    public void getProductQueryHandler_inValidId_throwsProductNotFoundException(){
        //given
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        //when and then
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> getProductQueryHandler.execute(1));
        //then
        assertEquals("Product Not Found", exception.getSimpleResponse().getMessage());
    }

}
