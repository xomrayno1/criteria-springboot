package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDAO;
import com.example.demo.entity.Product;
import com.example.demo.model.ProductModel;

@Service
public class ProductService {
	@Autowired
	ProductDAO productDAO;
	
	public Product createProduct(Product product) {
		return productDAO.createProduct(product);
	}
	public Product updateProduct(Product product) {
		return productDAO.updateProduct(product);
	}
	public void deleteProduct(Product product) {
		productDAO.deleteProduct(product);
	}
	public Product getById(long id) {
		return productDAO.getProductById(id);
	}
	public List<Product> getAll(){
		return productDAO.getAll();
	}
	public List<Product> getProductByPrice(double startPrice ,double endPrice){
		return productDAO.getProductByPrice(startPrice, endPrice);
	}
	public List<Product> getProductByNameLike(String name, int cateId){
		return productDAO.getProductByNameLike(name, cateId);
	}
	public List<Object[]> getNameAndPrice(){
		return productDAO.getNameAndPrice();
	}
	public List<ProductModel> getNameAndPriceByProductModel(){
		return productDAO.getNameAndPriceByProductModel();
	}
	public List<Product> getProductByNameLikeCatePrice(String name, int cateId, double startPrice, double endPrice) {
		return	productDAO.getProductByNameLikeCatePrice(name, cateId, startPrice, endPrice);
	}
	public List<Object[]> staticsProductCountByCategory() {
		 return productDAO.staticsProductCountByCategory();
	}
	
	
}
