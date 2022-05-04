package com.xoriant.ecart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.ecart.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

	Product findByProductName(String productName);

	List<Product> findByPriceIsBetween(double minPrice, double maxPrice);

	List<Product> findByPriceLessThan(double minPrice);

	List<Product> findByPriceGreaterThan(double maxPrice);

}
