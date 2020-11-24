package com.revature.guitarstore.test;



import java.sql.Connection;
import com.fasterxml.jackson.annotation.JsonFormat.Features;
import com.revature.guitarstore.DAO.BrandDAO;
import com.revature.guitarstore.DAO.CategoryDAO;
import com.revature.guitarstore.DAO.ConditionDAO;
import com.revature.guitarstore.DAO.DepartmentDAO;
import com.revature.guitarstore.DAO.FeaturesDAO;
import com.revature.guitarstore.DAO.PremiumGearDAO;
import com.revature.guitarstore.DAO.ProductFeatureDAO;
import com.revature.guitarstore.DAO.ProductReviewDAO;
import com.revature.guitarstore.DAO.SpecificationsDAO;
import com.revature.guitarstore.DAO.StyleDAO;
import com.revature.guitarstore.exceptions.GuitarStoreException;
import com.revature.guitarstore.model.Brand;
import com.revature.guitarstore.model.Category;
import com.revature.guitarstore.model.Condition;
import com.revature.guitarstore.model.Department;
import com.revature.guitarstore.model.Fture;
import com.revature.guitarstore.model.PremiumGear;
import com.revature.guitarstore.model.Specification;
import com.revature.guitarstore.model.Style;
import com.revature.guitarstore.product.ProductDAO;
import com.revature.guitarstore.product.ProductTemplate;
import com.revature.guitarstore.users.User;
import com.revature.guitarstore.users.UserDAO;
import com.revature.guitarstore.users.UserException;
import com.revature.guitarstore.users.UserLogin;
import com.revature.guitarstore.utils.DBConn;
import com.revature.guitarstore.utils.MD5Util;

public class Tests {

	public static void main(String[] args) throws Exception {
		
//		System.out.println("\n===== Testing DBConn =====");
//		Connection dbconn = DBConn.getConnection();
//		System.out.println(dbconn);
//		
//		
//		System.out.println("\n===== Testing MD5Util =====");		
//		String hashedString = MD5Util.getHashedCode("rubendominguez");
//		System.out.println("getHashedCode            : " + hashedString);
//		System.out.println("comparig expecting true  : " + hashedString.equals(MD5Util.getHashedCode("rubendominguez")));
//		System.out.println("comparig expecting false : " + hashedString.equals(MD5Util.getHashedCode("Rubendominguez")));
//		
//		System.out.println("\n===== Testing User Login =====");
//		System.out.println("existing user with good password");
//		User user = UserLogin.getAccess("ruben.dominguez", "rubendominguez");
//		System.out.println(user);
//		
//		System.out.println("existing user with bad password");
//		user = UserLogin.getAccess("ruben.dominguez", "RubenDominguez");
//		System.out.println(user);
//		
//		System.out.println("non existing user with other user real password");
//		user = UserLogin.getAccess("alexis.dominguez", "rubendominguez");
//		System.out.println(user);
//		
//		System.out.println("non existing user with non bad password");
//		user = UserLogin.getAccess("alexis.dominguez", "alexisdominguez");
//		System.out.println(user);
		
		
//		System.out.println("\n=====Testing User DAO=====");
//		User daoUser = new User();
//		System.out.println("empty user passed: UserException Expected");
//		try {
//			System.out.println(UserDAO.inserUser(daoUser));
//		} catch (UserException e) {
//			System.out.println(e.getMessage());
//		}
		
//		User daoUser = new User();
//		daoUser.setUsername("");
//		System.out.println("Passing empty username to UserDao: UserException expected");
//		try {
//			System.out.println(UserDAO.inserUser(daoUser));
//		} catch (UserException e) {
//			System.out.println(e.getMessage());
//		}		
		
//		User daoUser = new User();
//		daoUser.setUsername("rubendominguez");
//		System.out.println("Passing just username to UserDao: UserException expected");
//		try {
//			System.out.println(UserDAO.inserUser(daoUser));
//		} catch (UserException e) {
//			System.out.println(e.getMessage());
//		}
				
//		User daoUser = new User();
//		daoUser.setEmail("ruben.dominguez@hotmail.com");
//		System.out.println("Passing just email to UserDao: UserException expected: username empty");
//		try {
//			System.out.println(UserDAO.inserUser(daoUser));
//		} catch (UserException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//		}
		
		
//		User daoUser = new User("alexis.dominguez", "alexis.dominguez@hotmail.com", "alexisdominguez", 2);
//		User daoUser2 = new User("alexis.dominguez2", "alexis.dominguez@hotmail.com2", "alexisdominguez", 2);
//		System.out.println("Passing a valid User object to UserDAO");
//		try {
//			System.out.println(UserDAO.inserUser(daoUser));
//			System.out.println(UserDAO.updateUser(daoUser, daoUser2));
//		} catch (UserException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//		}
		

//		User daoUser = new User("alexis.dominguez", "alexis.dominguez@hotmail.com", "alexisdominguez", 2);
//		try {
//			System.out.println(UserDAO.inserUser(daoUser));
//			System.out.println("User deleted: " + UserDAO.deleteUser(daoUser));
//		} catch (UserException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//		}
		
//		User daoUser = new User("alexis.dominguez", "alexis.dominguez@hotmail.com", "alexisdominguez", 2);
//		try {
//			System.out.println("Inserting user....: " + UserDAO.inserUser(daoUser));
//			System.out.println("Active users......: " + UserDAO.getUsers());
//			System.out.println("User deleted......: " + UserDAO.deleteUser(daoUser));
//			System.out.println("Active users......: " + UserDAO.getUsers());
//			System.out.println("Inactive users....: " + UserDAO.getInactiveUsers());
//		} catch (UserException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//		}

//		System.out.println("==== Testing Brand DAO ====");
//		BrandDAO dao = new BrandDAO();
//		Brand obj = new Brand(11, "RBN", "RUBEN GUITARS");
//		System.out.println("Inserting     : " + dao.insert(obj));
//		System.out.println("Updating code : " + dao.updateCode(4, "RUBEN")); 
//		System.out.println("Updating Desc : " + dao.updateDescription(4, "Guitars by Ruben"));
//		System.out.println("Deleting      : " + dao.delete(4));
//		System.out.println("Active       : " + dao.getActive());
//		System.out.println("InActive     : " + dao.getInActive());
//		System.out.println("Deleting      : " + dao.delete(10));			

//		System.out.println("==== Testing Category DAO ====");
//		CategoryDAO dao = new CategoryDAO();
//		Category obj = new Category(11, "RBN", "RUBEN GUITARS");
//		System.out.println("Inserting     : " + dao.insert(obj));
//		System.out.println("Updating code : " + dao.updateCode(4, "RUBEN")); 
//		System.out.println("Updating Desc : " + dao.updateDescription(4, "Guitars by Ruben"));
//		System.out.println("Deleting      : " + dao.delete(4));
//		System.out.println("Active       : " + dao.getActive());
//		System.out.println("InActive     : " + dao.getInActive());
//		System.out.println("Deleting      : " + dao.delete(10));		
		
//		System.out.println("==== Testing Condition DAO ====");
//		ConditionDAO dao = new ConditionDAO();
//		Condition obj = new Condition(11, "RBN", "RUBEN GUITARS");
//		System.out.println("Inserting     : " + dao.insert(obj));
//		System.out.println("Updating code : " + dao.updateCode(4, "RUBEN")); 
//		System.out.println("Updating Desc : " + dao.updateDescription(4, "Guitars by Ruben"));
//		System.out.println("Deleting      : " + dao.delete(4));
//		System.out.println("Active       : " + dao.getActive());
//		System.out.println("InActive     : " + dao.getInActive());
//		System.out.println("Deleting      : " + dao.delete(10));			
		
//		System.out.println("==== Testing Department DAO ====");
//		DepartmentDAO dao = new DepartmentDAO();
//		Department obj = new Department(11, "RBN", "RUBEN GUITARS");
//		System.out.println("Inserting     : " + dao.insert(obj));
//		System.out.println("Updating code : " + dao.updateCode(4, "RUBEN")); 
//		System.out.println("Updating Desc : " + dao.updateDescription(4, "Guitars by Ruben"));
//		System.out.println("Deleting      : " + dao.delete(4));
//		System.out.println("Active       : " + dao.getActive());
//		System.out.println("InActive     : " + dao.getInActive());
//		System.out.println("Deleting      : " + dao.delete(10));	
				
//		System.out.println("==== Testing Features DAO ====");
//		FeaturesDAO dao = new FeaturesDAO();
//		Ftures obj = new Ftures(11, "RBN", "RUBEN GUITARS");
//		System.out.println("Inserting     : " + dao.insert(obj));
//		System.out.println("Updating code : " + dao.updateCode(4, "RUBEN")); 
//		System.out.println("Updating Desc : " + dao.updateDescription(4, "Guitars by Ruben"));
//		System.out.println("Deleting      : " + dao.delete(4));
//		System.out.println("Active       : " + dao.getActive());
//		System.out.println("InActive     : " + dao.getInActive());
//		System.out.println("Deleting      : " + dao.delete(10));	
		
//		System.out.println("==== Testing PremiumGear DAO ====");
//		PremiumGearDAO dao = new PremiumGearDAO();
//		PremiumGear obj = new PremiumGear(11, "RBN", "RUBEN GUITARS");
//		System.out.println("Inserting     : " + dao.insert(obj));
//		System.out.println("Updating code : " + dao.updateCode(4, "RUBEN")); 
//		System.out.println("Updating Desc : " + dao.updateDescription(4, "Guitars by Ruben"));
//		System.out.println("Deleting      : " + dao.delete(4));
//		System.out.println("Active       : " + dao.getActive());
//		System.out.println("Inactive     : " + dao.getInActive());
//		System.out.println("Deleting      : " + dao.delete(10));	
		
//		System.out.println("==== Testing Specificatins DAO ====");
//		SpecificationsDAO dao = new SpecificationsDAO();
//		Specifications obj = new Specifications(11, "RBN", "RUBEN GUITARS");
//		System.out.println("Inserting     : " + dao.insert(obj));
//		System.out.println("Updating code : " + dao.updateCode(4, "RUBEN")); 
//		System.out.println("Updating Desc : " + dao.updateDescription(4, "Guitars by Ruben"));
//		System.out.println("Deleting      : " + dao.delete(4));
//		System.out.println("Active       : " + dao.getActive());
//		System.out.println("InActive     : " + dao.getInActive());
//		System.out.println("Deleting      : " + dao.delete(10));	
		
//		System.out.println("==== Testing Style DAO ====");
//		StyleDAO dao = new StyleDAO();
//		System.out.println("Startig data  : " + dao.getActives());
//		System.out.println("Inserting     : " + dao.insert("RBN", "RUBEN GUITARS"));
//		System.out.println("Updating code : " + dao.updateCode(4, "RUBEN")); 
//		System.out.println("Updating Desc : " + dao.updateDescription(4, "Guitars by Ruben"));
//		System.out.println("Search by id  : " + dao.searchById(4));
//		System.out.println("Deleting      : " + dao.delete(4));
//		System.out.println("Active       : " + dao.getActives());
//		System.out.println("InActive     : " + dao.getInactives());
//		System.out.println("Deleting      : " + dao.delete(10));
		
		System.out.println("==== Testing Product DAO ====");
		ProductDAO pdao = new ProductDAO();
		for (ProductTemplate pt : pdao.getAllActiveProducts()) {
			System.out.println(pt);
		}
		
	}

}
