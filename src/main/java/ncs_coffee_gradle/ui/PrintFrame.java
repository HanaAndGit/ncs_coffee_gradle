package ncs_coffee_gradle.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ncs_coffee_gradle.service.SaleService;
import ncs_coffee_gradle.ui.panel.PrintTbl1;

public class PrintFrame extends JFrame {
	private SaleService service = new SaleService(); 
	public PrintFrame() {
		
		PrintTbl1 panel = new PrintTbl1();
		try {
			panel.loadTableData(service.showSaleCntRank());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getContentPane().add(panel, BorderLayout.CENTER);
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintFrame frame = new PrintFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

}
