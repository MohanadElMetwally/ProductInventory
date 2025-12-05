package com.demoweb.ProductInventoryApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demoweb.ProductInventoryApp.dto.product.ProductCreateDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductDetailDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductSummaryDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductUpdateDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductsDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductsSummaryDTO;
import com.demoweb.ProductInventoryApp.exceptions.ProductNotFoundException;
import com.demoweb.ProductInventoryApp.mappers.ProductMapper;
import com.demoweb.ProductInventoryApp.models.Product;
import com.demoweb.ProductInventoryApp.models.Users;
import com.demoweb.ProductInventoryApp.repository.ProductRepo;

@Service
public class ProductService {

    private final ProductRepo prodRepo;
    private final ProductMapper prodMapper;

    public ProductService(ProductRepo prodRepo, ProductMapper prodMapper) {
        this.prodRepo = prodRepo;
        this.prodMapper = prodMapper;
    }

    public ProductsDTO getProducts() {
        List<ProductDetailDTO> products = prodRepo.findAll()
            .stream()
            .map(prodMapper::toDTO)
            .toList();
        return new ProductsDTO(products);
    }

    public ProductsSummaryDTO getProductsByUserId(int id) {
        List<ProductSummaryDTO> products = prodRepo.findAllProductsByUserId(id)
            .stream()
            .map(prodMapper::toSummaryDTO)
            .toList();
        return new ProductsSummaryDTO(products);
    }

    public ProductDetailDTO addProduct(ProductCreateDTO dto, Users user) {
        Product prod = prodMapper.toEntity(dto, user);
        prodRepo.save(prod);
        return prodMapper.toDTO(prod);
    }

    public ProductDetailDTO getProductById(int id) {
        Optional<Product> existingProduct = prodRepo.findById(id);

        if (existingProduct.isEmpty())
            throw new ProductNotFoundException(id);

        return prodMapper.toDTO(existingProduct.get());
    }

    @Transactional
    public ProductDetailDTO updateProduct(int id, ProductUpdateDTO dto) {
        Optional<Product> existingProduct = prodRepo.findById(id);

        if (existingProduct.isEmpty())
            throw new ProductNotFoundException(id);

        Product product = existingProduct.get();
        prodMapper.updateProduct(dto, product);
        return prodMapper.toDTO(product);
    }

    @Transactional
    public ProductDetailDTO patchProduct(int id, ProductDTO dto) {
        Optional<Product> existingProduct = prodRepo.findById(id);

        if (existingProduct.isEmpty())
            throw new ProductNotFoundException(id);

        Product product = existingProduct.get();
        prodMapper.patchProduct(dto, product);
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
