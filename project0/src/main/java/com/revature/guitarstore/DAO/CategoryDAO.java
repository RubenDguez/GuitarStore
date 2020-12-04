package com.revature.guitarstore.DAO;

import com.revature.guitarstore.model.Category;

public class CategoryDAO extends DAO<Category> {
	
	public CategoryDAO() {
		this.table = "CATEGORY";
	}
}
