package ncs_coffee_gradle.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ncs_coffee_gradle.service.SaleService;
import ncs_coffee_gradle.ui.panel.PrintTbl1;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class PrintFrame extends JFrame {
	private SaleService service = new SaleService(); 
	public PrintFrame() {
		initialize();
	}
	private void initialize() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		PrintTbl1 rankPanel = new PrintTbl1();
		try {
			rankPanel.loadTableData(service.showSaleCntRank());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.add(rankPanel);
		
		JPanel sumPanel = new JPanel();
		panel.add(sumPanel);
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintFrame frame = new PrintFrame();
					frame.setBounds(100, 100, 550, 300);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

}
