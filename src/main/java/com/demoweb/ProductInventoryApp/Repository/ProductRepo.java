package com.demoweb.ProductInventoryApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoweb.ProductInventoryApp.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
