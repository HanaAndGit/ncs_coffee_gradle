package ncs_coffee_gradle.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ncs_coffee_gradle.dao.SaleDao;
import ncs_coffee_gradle.dto.Sale;
import ncs_coffee_gradle.impl.SaleDaoImpl;

public class SaleService {
	private SaleDao dao = new SaleDaoImpl();
	
	public SaleService() {
		dao = SaleDaoImpl.getInstance();
	}
	
	public int addSale(Sale sale) throws SQLException {
		return dao.insertSale(sale);
	}
	
	public List<Sale> showSaleCntRank() throws SQLException{
		return dao.showSaleCntRank();
	}
	
	public List<String> saleExistChk() throws SQLException{
		return dao.saleExistChk();
	}
	
	public List<Sale> showMarginCntRank() throws SQLException{
		return dao.showMarginCntRank();
	}
	
}


