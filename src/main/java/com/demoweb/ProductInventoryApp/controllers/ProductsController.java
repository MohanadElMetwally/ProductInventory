package com.demoweb.ProductInventoryApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoweb.ProductInventoryApp.annotations.CurrentUser;
import com.demoweb.ProductInventoryApp.dto.MessageDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductCreateDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductDetailDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductUpdateDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductsDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductsSummaryDTO;
import com.demoweb.ProductInventoryApp.models.Users;
import com.demoweb.ProductInventoryApp.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductsDTO> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> getProduct(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ProductsSummaryDTO> getUserProducts(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(productService.getProductsByUserId(id));
    }

    @PostMapping("/")
    public ResponseEntity<ProductDetailDTO> createProduct(@CurrentUser Users user,
        @RequestBody ProductCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.addProduct(dto, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> updateProducts(@PathVariable int id,
        @RequestBody ProductUpdateDTO prod) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, prod));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> deleteProducts(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new MessageDTO("Item deleted successfully."));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> patchProducts(@PathVariable int id,
        @RequestBody ProductDTO prod) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.patchProduct(id, prod));
    }
}
