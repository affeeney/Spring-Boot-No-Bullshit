package ProductControllerTest;

import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.NoBsSpringBootApplication;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.commandhandlers.CreateProductCommandHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = NoBsSpringBootApplication.class)
public class CreateProductCommandHandlerTest {


    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;
    //MethodName_StateUnderTest_ExpectedBehavior

    //given,when,then
    @Test
    public void createProductCommandHandler_validProduct_returnsSuccess() {
        Product product = new Product();
        product.setId(1);
        product.setPrice(9.98);
        product.setName("deshaan");
        product.setDescription("Dark");
        product.setQuantity(10);

        ResponseEntity response = createProductCommandHandler.execute(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void createProductCommandHandler_invalidPrice_throwsInvalidPriceException(){
        Product product = new Product();
        product.setId(1);
        product.setPrice(-9.98);
        product.setName("deshaan");
        product.setDescription("Dark");
        product.setQuantity(10);

        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () -> createProductCommandHandler.execute(product));
        assertEquals("Price cant be negative", exception.getSimpleResponse().getMessage());
    }
}
