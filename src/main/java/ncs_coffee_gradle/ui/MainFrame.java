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
					Sale sale = panel.getSale();
					service.addSale(sale);
					JOptionPane.showMessageDialog(null, "입력되었습니다.");
					panel.clearTf();
				} catch (SQLException e1) {
					e1.printStackTrace();
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
