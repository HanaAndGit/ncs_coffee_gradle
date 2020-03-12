package ncs_coffee_gradle.ui.panel;

import javax.swing.JPanel;

import ncs_coffee_gradle.dto.Sale;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.Font;

public class PrintMarginRank extends AbsCenterTblPanel<Sale> {

	
	public PrintMarginRank() {
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("마진액 순위");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		

	}

	@Override
	protected void setTblWidthAlign() {
		tableSetWidth(50,100,100,50,50,100,100,100,50);
		tableCellAlign(SwingConstants.RIGHT, 3,4,5,6,7,8,9);
		tableCellAlign(SwingConstants.CENTER, 0,1,2);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"순위", "제품코드", "제품명", "제품단가", "판매수량", "공급가액", "부가세액", "판매금액", "마진율", "마진액"};
	}

	@Override
	protected Object[] toArray(Sale item) {
		return new Object[] {item.getRank(), //순위
				item.getProduct_code(), //제품코드
				item.getName(), //제품명
				String.format("%,d", item.getPrice()), //제품단가
				String.format("%,d", item.getSale_cnt()), //판매수량
				String.format("%,d", item.getSupply_value()), //공급가액
				String.format("%,d", item.getSurtax_value()), //부가세액
				String.format("%,d", item.getSelling_price()), //판매금액
				String.format("%,d", item.getMargin_rate()), //마진율
				String.format("%,d", (item.getMargin_value()/100))}; //마진액
	}

	@Override
	protected void updateRow(Sale item, int updateIdx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Sale getSelectedItem() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
