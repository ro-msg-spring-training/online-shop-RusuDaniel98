package ro.msg.learning.shop.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductAndCategoryDTO;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.service.ProductAndCategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductAndCategoryController {

    @Autowired
    ProductAndCategoryService productAndCategoryService;

    @GetMapping("/product/{productId}")
    private Product readProductById(@PathVariable("productId") int productId) {
        return productAndCategoryService.readProductById(productId);
    }

    @GetMapping("/product")
    private List<Product> readProducts() {
        List<Product> products = productAndCategoryService.readProducts();
        return products;
    }

    @PostMapping("/product")
    private void createProductAndCategory(@RequestBody ProductAndCategoryDTO productAndCategoryDTO) {
        productAndCategoryService.createProductAndCategory(productAndCategoryDTO);
    }

    @PutMapping("/product/{productId}")
    private void updateProduct(@RequestBody ProductAndCategoryDTO productAndCategoryDTO,
                               @PathVariable("productId") int productId) {
        productAndCategoryService.updateProduct(productAndCategoryDTO, productId);
    }

    @DeleteMapping("/product/{productId}")
    private void deleteProductById(@PathVariable("productId") int productId) {
        productAndCategoryService.deleteProductById(productId);
    }

}
