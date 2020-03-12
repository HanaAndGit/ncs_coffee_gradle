package ncs_coffee_gradle.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ncs_coffee_gradle.dao.SaleDao;
import ncs_coffee_gradle.ds.MySqlDataSource;
import ncs_coffee_gradle.dto.Sale;

public class SaleDaoImpl implements SaleDao {
private static final SaleDaoImpl instance = new SaleDaoImpl();
	
	public static SaleDaoImpl getInstance() {
		return instance;
	}
	@Override
	public int insertSale(Sale sale) throws SQLException {
		String sql = "insert into sale values(null, ?,?,?,?)";
		int res = -1;
		try(Connection con = MySqlDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, sale.getProduct_code());
			pstmt.setInt(2, sale.getPrice());
			pstmt.setInt(3, sale.getSale_cnt());
			pstmt.setInt(4, sale.getMargin_rate());
			
			res = pstmt.executeUpdate();
			
		}
		return res;
	}
	@Override
	public List<Sale> showSales() throws SQLException {
		
		return null;
	}
	@Override
	public List<Sale> showSaleCntRank() throws SQLException {
		List list = new ArrayList();
 		String sql = "select (select count(*)+1 from sale where sale_cnt > s.sale_cnt) as 순위, \n" + 
				"	s.product_code as 제품코드, \n" + 
				"	(select p.product_name from product where product_code= s.product_code) as 제품명,\n" + 
				"	s.price as 제품단가,\n" + 
				"	s.sale_cnt as 판매수량,\n" + 
				"	(select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11)) as 공급가액,\n" + 
				"	(select (select s.price*s.sale_cnt)/11) as 부가세액,\n" + 
				"	(select s.price*s.sale_cnt) as 판매금액,\n" + 
				"	s.margin_rate as 마진율,\n" + 
				"	(select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate) as 마진액\n" + 
				"	from sale s left join product p on s.product_code = p.product_code \n" + 
				"	order by 판매수량 desc";
		
		try(Connection con = MySqlDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				do {
					list.add(getSale(rs));
				}while(rs.next());
			}
			
			
		}
		
		
		return list;
	}
	
	
	private Sale getSale(ResultSet rs) throws SQLException {
		String rank = rs.getString(1);
		String code = rs.getString(2);
		String name = rs.getString(3);
		int price = rs.getInt(4);
		int sale_cnt = rs.getInt(5);
		int supply_value = rs.getInt(6);
		int surtax_value = rs.getInt(7);
		int selling_cost = rs.getInt(8);
		int margin_rate = rs.getInt(9);
		int margin_price = rs.getInt(10);
		
		return new Sale(rank, code, name, price, sale_cnt, supply_value, surtax_value, selling_cost, margin_rate, margin_price);
		
	}
	@Override
	public List<String> saleExistChk() throws SQLException {
		String sql = "select product_code from sale";
		List<String> list = null;
		int res = 0;
		try(Connection con = MySqlDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(rs.getString(1));
				}while(rs.next());
			}
			
		}
		return list;
	}
	@Override
	public List<Sale> showMarginCntRank() throws SQLException {
		List list = new ArrayList();
 		String sql = "select (select count(*)+1 from sale  where (select (select (select price*sale_cnt)-(select (select price*sale_cnt)/11))*margin_rate) > (select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate) ) as 순위, \n" + 
				"	s.product_code as 제품코드, \n" + 
				"	(select p.product_name from product where product_code= s.product_code) as 제품명,\n" + 
				"	s.price as 제품단가,\n" + 
				"	s.sale_cnt as 판매수량,\n" + 
				"	(select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11)) as 공급가액,\n" + 
				"	(select (select s.price*s.sale_cnt)/11) as 부가세액,\n" + 
				"	(select s.price*s.sale_cnt) as 판매금액,\n" + 
				"	s.margin_rate as 마진율,\n" + 
				"	(select (select (select s.price*s.sale_cnt)-(select (select s.price*s.sale_cnt)/11))*s.margin_rate) as 마진액\n" + 
				"	from sale s left join product p on s.product_code = p.product_code \n" + 
				"	order by 마진액 desc";
		
		try(Connection con = MySqlDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				do {
					list.add(getSale(rs));
				}while(rs.next());
			}
			
			
		}
		
		
		return list;
	}

}
