package com.example.demo.Product.commandhandlers;

import com.example.demo.Command;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;


@Service
public class CreateProductCommandHandler implements Command<Product, ResponseEntity> {
    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateProductCommandHandler.class);

    @Override
    public ResponseEntity execute(Product product) {
        logger.info("Executing" + getClass() + product.toString());

        validateProduct(product);

        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    private void validateProduct(Product product) {

        //name
        if(StringUtils.isBlank(product.getName())) {
            logger.error(product.toString());
            throw new ProductNotValidException("Product name cant be empty");

        }
        //description
        if(StringUtils.isBlank(product.getDescription())) {
            logger.error(product.toString());
            throw new ProductNotValidException("Product description cant be empty");

        }
        //price
        if(product.getPrice() <= 0.0) {
            logger.error(product.toString());
            throw new ProductNotValidException("Product price cant be neg");
        }
        //quantity
        if(product.getQuantity() <= 0) {
            logger.error(product.toString());
            throw new ProductNotValidException("Product Quantity name cant be neg");
        }

    }
}
