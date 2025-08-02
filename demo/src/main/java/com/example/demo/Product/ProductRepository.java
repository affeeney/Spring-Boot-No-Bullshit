package com.example.demo.Product;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT p FROM Product p WHERE p.price < :maxPrice", nativeQuery = true)
    List<Product> findProductWithPriceLessThan(@Param("maxPrice") double maxPrice);

    @Query("SELECT new com.example.demo.Product.Model.ProductDTO(p.name, p.description, p.price) FROM Product p")
    List<ProductDTO> getAllProductDTOs();

    //custom query
    @Query("SELECT p FROM Product p Where p.description LIKE %:description%")
    List<Product> customQueryMethod(@Param(value="description") String description);

    //spring data jpa
    List<Product> findByDescriptionContaining(String keyword);
}
