package com.xoriant.ecart.service;

import java.util.List;

import com.xoriant.ecart.model.Product;

public interface ProductService {

	Product addNewProduct(Product product);

	List<Product> addNewListOfProduct(List<Product> prodLists);

	Product updateProduct(Product product);

	List<Product> updateListOfProduct(List<Product> proLists);

	Product findByProductName(String productName);

	List<Product> findByPriceIsBetween(double minPrice, double maxPrice);

	List<Product> findByPriceLessThan(double minPrice);

	List<Product> findByPriceGretherThanEqual(double maxPrice);
}
