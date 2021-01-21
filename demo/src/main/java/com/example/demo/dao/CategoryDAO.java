package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Category;

public interface CategoryDAO {

	
	Category createCategory(Category category);
	Category updateCategory(Category category);
	void deleteCategory(Category category);
	List<Category> getAll();
	Category getCategoryById(long id);

}
