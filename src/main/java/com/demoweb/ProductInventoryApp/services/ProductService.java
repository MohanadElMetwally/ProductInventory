package com.demoweb.ProductInventoryApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demoweb.ProductInventoryApp.DTOs.ProductDTO;
import com.demoweb.ProductInventoryApp.Exceptions.ProductNotFoundException;
import com.demoweb.ProductInventoryApp.Mappers.ProductMapper;
import com.demoweb.ProductInventoryApp.Repository.ProductRepo;
import com.demoweb.ProductInventoryApp.models.Product;

@Service
public class ProductService {

    private final ProductRepo prodRepo;
    private final ProductMapper prodMapper;

    public ProductService(ProductRepo prodRepo, ProductMapper prodMapper) {
        this.prodRepo = prodRepo;
        this.prodMapper = prodMapper;
    }

    public List<ProductDTO> getProducts() {
        return prodRepo.findAll().stream().map(prodMapper::toDTO).toList();
    }

    public ProductDTO addProduct(ProductDTO dto) {
        Product prod = prodMapper.toEntity(dto);
        prodRepo.save(prod);
        return prodMapper.toDTO(prod);
    }

    public ProductDTO getProductById(int id) {
        Optional<Product> existingProduct = prodRepo.findById(id);

        if (existingProduct.isEmpty())
            throw new ProductNotFoundException(id);

        return prodMapper.toDTO(existingProduct.get());
    }

    public ProductDTO updateProduct(int id, ProductDTO dto) {
        Optional<Product> existingProduct = prodRepo.findById(id);

        if (existingProduct.isEmpty())
            throw new ProductNotFoundException(id);

        Product product = existingProduct.get();
        prodMapper.updateProduct(dto, product);
        prodRepo.save(product);
        return prodMapper.toDTO(product);
    }

    public ProductDTO patchProduct(int id, ProductDTO dto) {
        Optional<Product> existingProduct = prodRepo.findById(id);

        if (existingProduct.isEmpty())
            throw new ProductNotFoundException(id);

        Product product = existingProduct.get();
        prodMapper.patchProduct(dto, product);
        prodRepo.save(product);
        return prodMapper.toDTO(product);
    }

    public void deleteProduct(int id) {
        Optional<Product> existingProduct = prodRepo.findById(id);

        if (existingProduct.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        prodRepo.delete(existingProduct.get());
    }

}
