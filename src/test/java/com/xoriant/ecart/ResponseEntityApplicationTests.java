package com.xoriant.ecart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.xoriant.ecart.dao.ProductDao;
import com.xoriant.ecart.model.Product;
import com.xoriant.ecart.service.ProductServiceImpl;

@SpringBootTest
class ResponseEntityApplicationTests {

	@Mock
	ProductDao productDao;

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	private static Product product1;
	private static Product product2;

	@BeforeAll
	public static void beforeAll() {
		product1 = new Product(101, "Oppo F1f", 15999, "Selfi Expert");
		product2 = new Product(102, "Samsung Galaxy Core", 125000, "Galaxy Series");
	}

	@Test
	public void addNewProduct() {
		when(productDao.save(product1)).thenReturn(product1);
		assertEquals(product1, productServiceImpl.addNewProduct(product1));
	}

	@Test
	public void addNewListOfProduct() {
		List<Product> prodLists = new ArrayList<Product>();
		prodLists.add(product1);
		prodLists.add(product2);
		when(productDao.saveAll(prodLists)).thenReturn(prodLists);
		assertEquals(prodLists, productServiceImpl.addNewListOfProduct(prodLists));
	}

	@Test
	public void updateProduct() {
		Optional<Product> existProd = Optional.of(product1);
		when(productDao.findById(product1.getProductId())).thenReturn(existProd);
		product1.setProductName("Oppo Reno");
		product1.setPrice(37000);
		product1.setDescription("Curved Edges");
		when(productDao.save(product1)).thenReturn(product1);
		assertThat(productServiceImpl.updateProduct(product1)).isEqualTo(product1);

	}

	@Test
	public void updateListOfProduct() {
		List<Product> prodLists = new ArrayList<Product>();
		Optional<Product> exitProd1 = Optional.of(product1);
		when(productDao.findById(product1.getProductId())).thenReturn(exitProd1);
		product1.setProductName("I Phone 12");
		product1.setPrice(52000);
		product1.setDescription("I Phone Series");
		when(productDao.save(product1)).thenReturn(product1);
		prodLists.add(product1);
		Optional<Product> existProd2=Optional.of(product2);
		when(productDao.findById(product2.getProductId())).thenReturn(existProd2);
		product2.setProductName("I Phone XR");
		product2.setPrice(39000);
		product2.setDescription("I Phone");
		when(productDao.save(product2)).thenReturn(product2);
		prodLists.add(product2);
		assertThat(productServiceImpl.updateListOfProduct(prodLists)).isEqualTo(prodLists);
	}

	@Test
	public void findByProductName() {
		String productName = "Oppo F1f";
		when(productDao.findByProductName(productName)).thenReturn(product1);
		assertEquals(product1, productServiceImpl.findByProductName(productName));
	}

	@Test
	public void findByPriceIsBetween() {
		List<Product> prodLists = new ArrayList<Product>();

		double minPrice = 10000;
		double maxPrice = 30000;
		prodLists.add(product1);
		prodLists.add(product2);
		when(productDao.findByPriceIsBetween(minPrice, maxPrice)).thenReturn(prodLists);
		assertEquals(prodLists, productServiceImpl.findByPriceIsBetween(minPrice, maxPrice));
	}

	@Test
	public void findByPriceLessThan() {
		List<Product> prodLists = new ArrayList<Product>();
		double minPrice = 15000;
		prodLists.add(product1);
		when(productDao.findByPriceLessThan(minPrice)).thenReturn(prodLists);
		assertEquals(prodLists, productServiceImpl.findByPriceLessThan(minPrice));
	}

	@Test
	public void findByPriceGretherThanEqual() {
		List<Product> prodLists = new ArrayList<Product>();
		double maxPrice = 35000;
		prodLists.add(product1);
		when(productDao.findByPriceGreaterThan(maxPrice)).thenReturn(prodLists);
		assertEquals(prodLists, productServiceImpl.findByPriceGretherThanEqual(maxPrice));
	}
}
