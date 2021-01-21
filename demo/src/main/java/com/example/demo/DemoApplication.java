package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	@Autowired
	CategoryService  categoryService;
	@Autowired
	ProductService productService;
	
	//https://gpcoder.com/6515-hibernate-criteria-query-language-hcql/
	//https://viblo.asia/p/tai-sao-nen-su-dung-jpa-criteria-V3m5WB8xlO7
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		List<Category> list = categoryService.getAll();
		list.forEach(System.out::println);
		
		Category category = categoryService.getCategoryById(3);
		System.out.println(category);
		
		
		System.out.println("Danh sachs Product");
		productService.getAll().forEach(System.out::println);
		System.out.println("Get Product By Id  : 3 " );
		System.out.println(productService.getById(3));
		System.out.println("Add Product");
		Product product = productService.createProduct(new Product("IPhone 12 ", 5000, new Category(1)));
		System.out.println(product);
		
		System.out.println("Danh sachs Product");
		productService.getAll().forEach(System.out::println);
		
		
		System.out.println("Danh sach Product có giá từ :  200 -> 650");
		
		productService.getProductByPrice(200, 650).forEach(System.out::println);
		
		
		System.out.println("search product name  and ");
		productService.getProductByNameLike("o", 1).forEach(System.out::println);
		
		
		System.out.println("get 2 cloumn name and price");
		productService.getNameAndPrice().forEach(item -> {
			for (Object object : item) {
				if(object instanceof String) {
					System.out.print("Name :[ "+(String)object+"] ");
				}
				if(object instanceof Double) {
					System.out.println(" Price :[ "+(Double)object+"]");
				}
			}
		});
		System.out.println("get 2 cloumn name and price by ProductModel");
		productService.getNameAndPriceByProductModel().forEach(System.out::println);
		
		
		System.out.println("Search ");
		
		productService.getProductByNameLikeCatePrice("o", 1, 460, 650)
					.forEach(System.out::println);
		

	}
	

}
