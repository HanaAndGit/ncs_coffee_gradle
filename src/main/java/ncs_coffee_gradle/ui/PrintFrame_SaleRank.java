package ncs_coffee_gradle.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ncs_coffee_gradle.service.SaleService;
import ncs_coffee_gradle.ui.panel.PrintSaleRank;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class PrintFrame_SaleRank extends JFrame {
	private SaleService service = new SaleService(); 
	public PrintFrame_SaleRank() {
		initialize();
	}
	private void initialize() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		PrintSaleRank rankPanel = new PrintSaleRank();
		try {
			rankPanel.loadTableData(service.showSaleCntRank());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.add(rankPanel);
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintFrame_SaleRank frame = new PrintFrame_SaleRank();
					frame.setBounds(100, 100, 550, 300);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

}
