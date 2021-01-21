package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Product;
import com.example.demo.model.ProductModel;

public interface ProductDAO {

	Product createProduct(Product product);
	Product updateProduct(Product product);
	void deleteProduct(Product product);
	List<Product> getAll();
	Product getProductById(long id);
	List<Product> getProductByPrice(double startPrice, double endPrice);
	List<Product> getProductByNameLike(String name, int cateId);
	List<Product> getProductByNameLikeCatePrice(String name, int cateId,double startPrice, double endPrice);
	List<Object[]> getNameAndPrice();
	List<ProductModel> getNameAndPriceByProductModel();
}
