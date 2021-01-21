package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDAO;
import com.example.demo.entity.Category;

@Service
public class CategoryService {
	@Autowired
	CategoryDAO categoryDAO;
	
	public Category createCategory(Category category) {
		return null;
	}
	public Category updateCategory(Category category) {
		return null;
	}
	public void deleteCategory(Category category) {}
	public List<Category> getAll(){
		return categoryDAO.getAll();
	}
	public Category getCategoryById(long id) {
		return categoryDAO.getCategoryById(id);
	}
}
