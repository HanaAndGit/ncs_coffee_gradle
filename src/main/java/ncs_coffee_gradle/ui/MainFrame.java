package ncs_coffee_gradle.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import ncs_coffee_gradle.dto.Sale;
import ncs_coffee_gradle.service.SaleService;
import ncs_coffee_gradle.ui.panel.product_sale_panel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private product_sale_panel panel;
	private SaleService service = new SaleService();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("판매 실적 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel btnsPanel = new JPanel();
		contentPane.add(btnsPanel, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("입력");
		btnsPanel.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<String> list = new ArrayList<>();
					list = service.saleExistChk();
					Sale sale = panel.getSale();
					Boolean chk = true;
					
					for(int i=0; i<list.size(); i++) {
						if(list.get(i).equals(sale.getProduct_code())){
							JOptionPane.showMessageDialog(null, "이미 등록된 제품입니다.");
							chk = false;
						}else {
							break;
						}
					}
					if(sale.getPrice() >= 99999999 || sale.getSale_cnt() >= 99999999 || sale.getMargin_rate() >= 100) {
						JOptionPane.showMessageDialog(null, "제품 단가와 판매수량은 8자리 이내의 정수, 마진율은 2자리 이내의 정수 입니다.");
					}else {
						if(chk ==true) {
							service.addSale(sale);
							JOptionPane.showMessageDialog(null, "입력되었습니다.");
							panel.clearTf();	
						}
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "다시 입력하세요.");
				}
				
			}
		});
		
		
		
		JButton btnPrint1 = new JButton("출력1");
		btnPrint1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintFrame frame = new PrintFrame();
				frame.setBounds(100, 100, 450, 500);
				frame.setVisible(true);
			}
		});
		btnsPanel.add(btnPrint1);
		
		JButton btnPrint2 = new JButton("출력2");
		btnsPanel.add(btnPrint2);
		
		panel = new product_sale_panel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		
		
	}

}
