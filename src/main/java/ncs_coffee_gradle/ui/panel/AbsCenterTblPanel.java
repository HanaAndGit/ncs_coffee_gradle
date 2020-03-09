package ncs_coffee_gradle.ui.panel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
abstract public class AbsCenterTblPanel<T> extends JPanel {
	private JTable table;
	protected NotEditableModel model;
	private JScrollPane scrollPane;
	public AbsCenterTblPanel() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}
	
	public JTable getTable() {
		return table;
	}

	public void loadTableData(List<T> itemList) {
		model = new NotEditableModel(getRows(itemList), getColumns());
		table.setModel(model);
		
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		
		setTblWidthAlign();
	}
	protected abstract void setTblWidthAlign();
	
	protected abstract String[] getColumns();
	
	protected Object[][] getRows(List<T> itemList) {
		Object[][] rows = new Object[itemList.size()][];
		for(int i=0;i<itemList.size();i++) {
			rows[i] = toArray(itemList.get(i));
			
		}
		return rows;
	};
	protected abstract Object[] toArray(T item);
	

	protected void setColumnWidth(int ...idx) {
		TableColumnModel cModel = table.getColumnModel();
		for(int i=0; i< idx.length; i++) {
			cModel.getColumn(i).setPreferredWidth(idx[i]);
			
		}
	}
	protected void setColumnAlign(int align, int...idx) {
		//SwingConstants 사용
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		TableColumnModel cModel = table.getColumnModel();
		for(int i=0;i<idx.length;i++) {
			cModel.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	protected abstract void updateRow(T item, int updateIdx);
	public void addItem(T item) {
		model.addRow(toArray(item));
	}
	public void removeItem(int delIdx) {
		model.removeRow(delIdx);
	}
	
	protected abstract T getSelectedItem();
	
	public int getSelectedRowIdx() {
		int selectedIdx = table.getSelectedRow();
//		if(selectedIdx==-1) {
//			throw new RuntimeException("선택부터 해주세요");
//		}
		return selectedIdx;
	}

	public void setPopupMenu(JPopupMenu popupMenu) {
		scrollPane.setComponentPopupMenu(popupMenu);
		table.setComponentPopupMenu(popupMenu);
	}

	protected class NotEditableModel extends DefaultTableModel {

		public NotEditableModel(Object[][] rows, String[] columns) {
			super(rows,columns);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}	
	}
}
