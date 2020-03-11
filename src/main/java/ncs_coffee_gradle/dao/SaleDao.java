package ncs_coffee_gradle.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ncs_coffee_gradle.dto.Sale;

public interface SaleDao {
	abstract int insertSale(Sale sale) throws SQLException;
	abstract List<Sale> showSales() throws SQLException;
	abstract List<Sale> showSaleCntRank() throws SQLException;
	abstract List<String> saleExistChk() throws SQLException;
}
