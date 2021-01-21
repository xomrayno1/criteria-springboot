package com.example.demo.dao.impl;

import java.util.LinkedList;
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

import com.example.demo.dao.ProductDAO;
import com.example.demo.entity.Product;
import com.example.demo.model.ProductModel;

@Repository
public class ProductDAOImpl implements ProductDAO{
	@Autowired
	EntityManagerFactory entityManagerFactory;

	
	private static final Logger log = LoggerFactory.getLogger(ProductDAOImpl.class);

	
	@Override
	public Product createProduct(Product product) { 
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();;
			entityManager.persist(product);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("create product error : " + e.getMessage());
		}finally {
			entityManager.close();
		}
	
		return null;
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override //get all
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
			CriteriaQuery<Product> query = builder.createQuery(Product.class);
			Root<Product> root = query.from(Product.class);
			query.select(root);
			return entityManager.createQuery(query).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("get all product "+ e.getMessage());
		}finally {
			entityManager.close();
		}
		return  null;
	}

	@Override
	public Product getProductById(long id) {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Product> query= builder.createQuery(Product.class);
			Root<Product> root =  query.from(Product.class);
			Predicate condition = builder.equal(root.get("id"), id);
			query.select(root).where(condition);
			return entityManager.createQuery(query).getSingleResult(); 
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("get product by id "+ e.getMessage());
		}finally {
			entityManager.close();
		}
		return null;
	}

	@Override //get >= <=
	public List<Product> getProductByPrice(double startPrice, double endPrice) {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Product> query = builder.createQuery(Product.class);
			Root<Product> root = query.from(Product.class);
			Predicate condition =
					builder.and(builder.greaterThanOrEqualTo(root.get("price"), startPrice),
							builder.lessThanOrEqualTo(root.get("price"), endPrice));
					 
			query.select(root).where(condition);
			return entityManager.createQuery(query).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			entityManager.close();
		}
		return null;
	}

	@Override //search like and  =
	public List<Product> getProductByNameLike(String name, int cateId) {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Product> criteriaQuery =  builder.createQuery(Product.class);
			Root<Product> root = criteriaQuery.from(Product.class);
			Predicate condition = builder.and(builder.equal(root.get("category"), cateId),builder.like(root.get("name"), "%"+name+"%"));   		 
			criteriaQuery.select(root).where(condition);
			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			entityManager.close();
		}
		return null;
	}

	@Override // get một vài column 
	public List<Object[]> getNameAndPrice() {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
			Root<Product> root = query.from(Product.class);
			query.multiselect(root.get("name"),root.get("price"));
			List<Object[]> objects =  entityManager.createQuery(query).getResultList();
			return objects;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			entityManager.close();
		}
		return null;
	}

	@Override //  get nhiều điều kiện
	public List<Product> getProductByNameLikeCatePrice(String name, int cateId, double startPrice, double endPrice) {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
			Root<Product>  root = criteriaQuery.from(Product.class);
			List<Predicate> predicates = new LinkedList<>();
			if(name != null && !name.trim().isEmpty()) {
				String wrapperSearch = "%"+name.trim()+"%";
				Predicate wraperPre = builder.like(root.get("name") , wrapperSearch);
				predicates.add(wraperPre);
			}
			if((startPrice != 0 && endPrice != 0) && (startPrice < endPrice)) {
				Predicate pricePre = builder.between(root.get("price"), startPrice, endPrice);
				predicates.add(pricePre);
			}
			if(cateId != 0) {
				Predicate catePre = builder.equal(root.get("category") , cateId);
				predicates.add(catePre);
			}
		//	builder.and(predicates.toArray(new Predicate[] {}));
			criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}))
										.orderBy(builder.desc(root.get("price")));
			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("get ProductByNameLikeCatePrice error : "+e.getMessage());
		}finally {
			entityManager.close();
		}
		return null;
	}

	@Override // get một vài column  sử dụng constructor
	public List<ProductModel> getNameAndPriceByProductModel() {
		// TODO Auto-generated method stub
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductModel> criteriaQuery = builder.createQuery(ProductModel.class);
			Root<Product>  root = criteriaQuery.from(Product.class);
			criteriaQuery.select(builder.construct(ProductModel.class, root.get("name"), root.get("price")));
			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("get name and price product error : "+e.getMessage());
		}finally {
			entityManager.close();
		}
		return null;
	}

}
