package com.hpe.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.shoppingbackend.dao.ProductDAO;
import com.hpe.shoppingbackend.dto.Product;
@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {
     
	
	@Autowired
	private SessionFactory sessionFactory;
     
	public Product get(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class,Integer.valueOf(productId));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> list() {
		try {
			return sessionFactory.getCurrentSession().createQuery("FROM Product",Product.class).getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
		    return true;
		    
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Product product) {
		try {
			product.setActive(false);
			return this.update(product);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return false;
	}

	public List<Product> listActiveProducts() {
		String selectActiveProducts="From Product where active=:active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts,Product.class).setParameter("active",true).getResultList();
	}
	
	public List<Product> listActiveProductsByCategory(int categoryId) {
	    String selectActiveProductByCategory="FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductByCategory,Product.class).setParameter("active", true).setParameter("categoryId",categoryId).getResultList();
	}

	public List<Product> getLatestActiveProducts(int count) {
		 
			return sessionFactory.getCurrentSession().createQuery("FROM PRODUCT where active=:active order by id",Product.class).setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
		
	}

}
