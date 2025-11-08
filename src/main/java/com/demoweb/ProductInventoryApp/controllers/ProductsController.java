package com.demoweb.ProductInventoryApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoweb.ProductInventoryApp.DTOs.ApiResponse;
import com.demoweb.ProductInventoryApp.DTOs.MessageDTO;
import com.demoweb.ProductInventoryApp.DTOs.ProductDTO;
import com.demoweb.ProductInventoryApp.DTOs.ProductsDTO;
import com.demoweb.ProductInventoryApp.Exceptions.ProductNotFoundException;
import com.demoweb.ProductInventoryApp.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getProducts() {
        try {
            return new ResponseEntity<>(new ProductsDTO(productService.getProducts()),
                HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageDTO("An error occurred while fetching item: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable int id) {
        try {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageDTO("An error occurred while fetching item: " + e.getMessage()));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody ProductDTO dto) {
        try {
            return new ResponseEntity<>(productService.addProduct(dto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageDTO("An error occurred while adding item: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProducts(@PathVariable int id,
        @RequestBody ProductDTO prod) {
        try {
            return new ResponseEntity<>(productService.updateProduct(id, prod), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageDTO("An error occurred while updating item: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> deleteProducts(@PathVariable int id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK)
                .body(new MessageDTO("Item deleted successfully."));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageDTO("An error occurred while deleting item: " + e.getMessage()));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> patchProducts(@PathVariable int id,
        @RequestBody ProductDTO prod) {
        try {
            return new ResponseEntity<>(productService.patchProduct(id, prod), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageDTO("An error occurred while patching item: " + e.getMessage()));
        }
    }
}
