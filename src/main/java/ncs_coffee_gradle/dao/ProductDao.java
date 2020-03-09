package ncs_coffee_gradle.dao;

import java.sql.SQLException;

import ncs_coffee_gradle.dto.Product;

public interface ProductDao {
	
	abstract String selectProductName (String productCode) throws SQLException;
	
}
