package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.CategoryDAO;
import com.example.demo.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
	@Autowired
	EntityManagerFactory entityManagerFactory;
 
	private static final Logger log = LoggerFactory.getLogger(CategoryDAOImpl.class);


	@Override
	public Category createCategory(Category category) {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			 
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			entityManager.close();
		}
		
		return null;
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategory(Category category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
			Root<Category> root = criteriaQuery.from(Category.class);
			//Predicate predicate = criteriaBuilder.equal(root.get("id"), 1);
			criteriaQuery.select(root);//.where(criteriaBuilder.equal(root.get("id"), 1));\
			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			entityManager.close();
			System.out.println("close");
		}
		return null;
	}

	@Override
	public Category getCategoryById(long id) {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			 CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			 CriteriaQuery<Category> query = builder.createQuery(Category.class);
			 Root<Category> root = query.from(Category.class);
			 Predicate condition = builder.equal(root.get("id"), id);
			 query.select(root).where(condition);
			 return entityManager.createQuery(query).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("get category by id  :" + e.getMessage());
		}finally {
			entityManager.close();
			System.out.println("close");
		}
		return null;
	}

}
