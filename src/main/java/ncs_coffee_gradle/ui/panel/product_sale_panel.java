package ncs_coffee_gradle.ui.panel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ncs_coffee_gradle.dto.Product;
import ncs_coffee_gradle.dto.Sale;
import ncs_coffee_gradle.service.ProductService;

import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class product_sale_panel extends JPanel {
	private JTextField tfProductCode;
	private JTextField tfProductName;
	private JLabel lblPrice;
	private JTextField tfPrice;
	private JLabel lblSaleCnt;
	private JTextField tfSaleCnt;
	private JLabel lblMarginRate;
	private JTextField tfMarginRate;
	private ProductService service = new ProductService();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 */
	public product_sale_panel() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblProductCode = new JLabel("제품코드");
		lblProductCode.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProductCode);
		
		tfProductCode = new JTextField();
		
		add(tfProductCode);
		tfProductCode.setColumns(10);
		
		lblNewLabel = new JLabel("");
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("입력 후 <Enter> 키를 누르세요.");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_1);
		
		JLabel lblProductName = new JLabel("제품 명");
		lblProductName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProductName);
		
		tfProductName = new JTextField();
		tfProductName.setEditable(false);
		tfProductName.setColumns(10);
		add(tfProductName);
		
		
		tfProductCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
				String code = tfProductCode.getText();
				String name = service.showName(code);
				tfProductName.setText(name);
				} catch (SQLException e1) {
					e1.printStackTrace();
			}
				
				
			}
		});
		
//		tfProductCode.addPropertyChangeListener(new PropertyChangeListener() {
//			
//			
//			public void propertyChange(PropertyChangeEvent evt) {
//				
//				try {
//					String code = tfProductCode.getText();
//					String name = service.showName(code);
//					tfProductName.setText(name);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				
//				
//			}
//		});
		
		
		
		lblPrice = new JLabel("제품 단가");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPrice);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		add(tfPrice);
		
		lblSaleCnt = new JLabel("판매 수량");
		lblSaleCnt.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSaleCnt);
		
		tfSaleCnt = new JTextField();
		tfSaleCnt.setColumns(10);
		add(tfSaleCnt);
		
		lblMarginRate = new JLabel("마진율");
		lblMarginRate.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMarginRate);
		
		tfMarginRate = new JTextField();
		tfMarginRate.setColumns(10);
		add(tfMarginRate);

	}
	
	
	public Product getProduct() {
		String code = tfProductCode.getText();
		String name = tfProductName.getText();
		
		return new Product(code, name);
	}
	
	public Sale getSale() {
		String code = tfProductCode.getText();
		int price = Integer.parseInt(tfPrice.getText());
		int saleCnt = Integer.parseInt(tfSaleCnt.getText());
		int marginRate = Integer.parseInt(tfMarginRate.getText());
		
		
		Sale sale = new Sale();
		sale.setProduct_code(code);
		sale.setPrice(price);
		sale.setSale_cnt(saleCnt);
		sale.setMargin_rate(marginRate);
		
		return sale;
	}
	
	public void setItem(Product product) {
		tfProductCode.setText(product.getCode());
		tfProductName.setText(product.getName());
	}
	
	public void clearTf() {
		tfProductCode.setText("");
		tfProductName.setText("");
		tfPrice.setText("");
		tfSaleCnt.setText("");
		tfMarginRate.setText("");
	}

}
