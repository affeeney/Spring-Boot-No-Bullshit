package com.example.demo.Headers;

import com.example.demo.Product.Model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    @GetMapping("/header")
    public String getRegionalRequest(@RequestHeader(required = false, defaultValue = "USA") String region){
        if(region.equals("USA")) return "BALD EAGLE FREEDOM";

        if(region.equals("CAN")) return "GEY";

        return "Country not supported :";

    }

    @GetMapping(value="/header", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct() {
        Product product = new Product();
        product.setName("myProduct");
        product.setId(1);
        product.setDescription("hello product");

        return ResponseEntity.ok(product);
    }

}
