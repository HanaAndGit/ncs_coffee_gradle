package ncs_coffee_gradle.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ncs_coffee_gradle.dao.ProductDao;
import ncs_coffee_gradle.ds.MySqlDataSource;
import ncs_coffee_gradle.dto.Product;

public class ProductDaoImpl implements ProductDao {
	private static final ProductDaoImpl instance = new ProductDaoImpl();
	
	public static ProductDaoImpl getInstance() {
		return instance;
	}
	
	
	@Override
	public String selectProductName(String productCode) throws SQLException {
		String sql = "select product_name from product where product_code=? ";
		try(Connection con = MySqlDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, productCode);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					return rs.getString(1);
				}
			}
		}
		return null;
	}

	

}
