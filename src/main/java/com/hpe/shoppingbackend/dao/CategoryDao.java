package com.hpe.shoppingbackend.dao;

import java.util.List;

import com.hpe.shoppingbackend.dto.Category;

public interface CategoryDao {
	
	Category get(int id);
	List<Category> list();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);

}
