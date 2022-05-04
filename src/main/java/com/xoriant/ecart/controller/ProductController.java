package com.xoriant.ecart.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.ecart.model.Product;
import com.xoriant.ecart.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/save")
	public ResponseEntity<String> addNewProduct(@RequestBody Product product) {
		Product newProduct = productService.addNewProduct(product);
		if (!newProduct.getProductName().isEmpty() || newProduct.getProductName().length() != 0) {
			return new ResponseEntity<String>("New Product Added Succesfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Not able to add New Product", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/saveAll")
	public ResponseEntity<String> addListOfProuct(@RequestBody List<Product> prodLists) {
		for (Product eachProd : prodLists) {
			if (eachProd.getProductName().isEmpty() || eachProd.getProductName().length() == 0) {
				return new ResponseEntity<String>("Please check inputs", HttpStatus.BAD_REQUEST);
			}
			if (eachProd.getPrice() <= 0) {
				return new ResponseEntity<String>("Please check inputs", HttpStatus.BAD_REQUEST);
			}
			if (eachProd.getDescription().isEmpty() || eachProd.getDescription().length() == 0) {
				return new ResponseEntity<String>("Please check inputs", HttpStatus.BAD_REQUEST);
			}
		}
		productService.addNewListOfProduct(prodLists);
		return new ResponseEntity<String>("Lists Of Product Added ", HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {

		if (product.getProductName().isEmpty() || product.getProductName().length() == 0) {
			return new ResponseEntity<String>("Please check inputs", HttpStatus.BAD_REQUEST);
		}
		if (product.getPrice() <= 0) {
			return new ResponseEntity<String>("Please check inputs", HttpStatus.BAD_REQUEST);
		}
		if (product.getDescription().isEmpty() || product.getDescription().length() == 0) {
			return new ResponseEntity<String>("Please check inputs", HttpStatus.BAD_REQUEST);
		}
		Product prod = productService.updateProduct(product);
		if (prod == null) {
			return new ResponseEntity<String>("Product Not Present In Database", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Update Product Succesfully", HttpStatus.OK);
	}

	@PutMapping("/updateAll")
	public ResponseEntity<String> updateProuctLists(@RequestBody List<Product> product) {
		for (Product updateProduct : product) {
			if (updateProduct.getProductId() <= 0) {
				return new ResponseEntity<String>("Please check inputs", HttpStatus.BAD_REQUEST);
			}
			if (updateProduct.getProductName().isEmpty() || updateProduct.getProductName().length() == 0) {
				return new ResponseEntity<String>("Please check inputs", HttpStatus.BAD_REQUEST);
			}
			if (updateProduct.getPrice() <= 0) {
				return new ResponseEntity<String>("Please check inputs", HttpStatus.BAD_REQUEST);
			}
			if (updateProduct.getDescription().isEmpty() || updateProduct.getDescription().length() == 0) {
				return new ResponseEntity<String>("Please check inputs", HttpStatus.BAD_REQUEST);
			}
		}

		List<Product> reposnse = productService.updateListOfProduct(product);
		if (reposnse == null) {
			return new ResponseEntity<String>("Product Not Present In Database", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Update Product List Succesfully", HttpStatus.OK);
	}

	@GetMapping("/find/{productName}")
	public ResponseEntity<Product> findByProductName(@PathVariable String productName) {

		Product product = productService.findByProductName(productName);
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@GetMapping("/find/{minPrice}/{maxPrice}")
	public ResponseEntity<List<Product>> findByPriceIsBetween(@PathVariable double minPrice,
			@PathVariable double maxPrice) {
		List<Product> prodLists = productService.findByPriceIsBetween(minPrice, maxPrice);
		return new ResponseEntity<List<Product>>(prodLists, HttpStatus.OK);
	}

	@GetMapping("/find/product/{minPrice}")
	public ResponseEntity<List<Product>> findByPriceLessThan(@PathVariable double minPrice) {
		List<Product> prodLists = productService.findByPriceLessThan(minPrice);
		return new ResponseEntity<List<Product>>(prodLists, HttpStatus.OK);

	}

	@GetMapping("/find/product/maxPrice/{maxPrice}")
	public ResponseEntity<List<Product>> findByPriceGretherThanEqual(@PathVariable double maxPrice) {
		List<Product> prodLists = productService.findByPriceGretherThanEqual(maxPrice);
		return new ResponseEntity<List<Product>>(prodLists, HttpStatus.OK);
	}
}
