package ncs_coffee_gradle.ui.panel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ncs_coffee_gradle.dto.Sale;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PrintTbl1 extends AbsCenterTblPanel<Sale> {

	
	public PrintTbl1() {
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("판매 금액 순위");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

	}

	@Override
	protected void setTblWidthAlign() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"순위", "제품코드", "제품명", "제품단가", "판매수량", "공급가액", "부가세액", "판매금액", "마진율", "마진액"};
	}

	@Override
	protected Object[] toArray(Sale item) {
		return new Object[] {item.getRank(),
				item.getProduct_code(),
				item.getName(),
				item.getPrice(),
				item.getSale_cnt(),
				item.getSupply_value(),
				item.getSurtax_value(),
				item.getSelling_price(),
				item.getMargin_rate(),
				item.getMargin_value()};
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
