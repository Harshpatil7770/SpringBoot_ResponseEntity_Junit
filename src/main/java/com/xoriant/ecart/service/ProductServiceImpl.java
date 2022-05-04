package com.xoriant.ecart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.ecart.dao.ProductDao;
import com.xoriant.ecart.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product addNewProduct(Product product) {

		return productDao.save(product);
	}

	@Override
	public List<Product> addNewListOfProduct(List<Product> prodLists) {

		return productDao.saveAll(prodLists);
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> prod = productDao.findById(product.getProductId());
		if (prod.isPresent()) {
			Product exisProd = productDao.findById(product.getProductId()).orElse(null);
			exisProd.setProductId(product.getProductId());
			exisProd.setProductName(product.getProductName());
			exisProd.setPrice(product.getPrice());
			exisProd.setDescription(product.getDescription());
		} else {
			return null;
		}
		return productDao.save(product);
	}

	@Override
	public List<Product> updateListOfProduct(List<Product> proLists) {
		List<Product> updateProdLists = new ArrayList<Product>();
		for (Product eachProduct : proLists) {
			Optional<Product> prod = productDao.findById(eachProduct.getProductId());
			if (!prod.isPresent()) {
				return null;
			}

			Product existingProd = prod.get();
			existingProd.setProductId(eachProduct.getProductId());
			existingProd.setProductName(eachProduct.getProductName());
			existingProd.setPrice(eachProduct.getPrice());
			existingProd.setDescription(eachProduct.getDescription());
			productDao.save(existingProd);
			updateProdLists.add(eachProduct);
		}
		return updateProdLists;
	}

	@Override
	public Product findByProductName(String productName) {

		return productDao.findByProductName(productName);
	}

	@Override
	public List<Product> findByPriceIsBetween(double minPrice, double maxPrice) {
		
		return productDao.findByPriceIsBetween(minPrice,maxPrice);
	}

	@Override
	public List<Product> findByPriceLessThan(double minPrice) {
		
		return productDao.findByPriceLessThan(minPrice);
	}

	@Override
	public List<Product> findByPriceGretherThanEqual(double maxPrice) {
		
		return productDao.findByPriceGreaterThan(maxPrice);
	}

}
