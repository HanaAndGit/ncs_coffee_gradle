package ncs_coffee_gradle.service;

import java.sql.SQLException;

import ncs_coffee_gradle.dao.ProductDao;
import ncs_coffee_gradle.impl.ProductDaoImpl;

public class ProductService {
	private ProductDao dao = new ProductDaoImpl(); 
	
	public ProductService() {
		dao = ProductDaoImpl.getInstance();
	}
	
	public String showName(String productCode) throws SQLException {
		return dao.selectProductName(productCode);
	}
}
