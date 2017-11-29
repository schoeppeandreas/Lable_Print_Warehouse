package frames;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ProzessAllocated {
	private static List<AllocatedItem> allocatedItems;
	public static List<AllocatedItem> selectedAllocatedItems = new ArrayList<>();
	private static DefaultTableModel model = new DefaultTableModel();
	
	private static void printSelectedAllocatedItems() {
		System.out.println(selectedAllocatedItems.size());
		if(selectedAllocatedItems.size() != 0) {
			Tsp400Label_Allocated.output(selectedAllocatedItems);
		}
	}
	
	private static void setDefault() {
		model = new DefaultTableModel();
		allocatedItems = new ArrayList<AllocatedItem>();
		allocatedItems = ReadAllocatedFromXls.read();
		if(selectedAllocatedItems.size() != 0) {
			selectedAllocatedItems.clear();
		}
	}
	
	public static DefaultTableModel printAllParts() {
		setDefault();
		for (int i = 0; i < allocatedItems.size(); i++) {
			AllocatedItem allocatedItem = allocatedItems.get(i);
			if (i == 0) { // Ueberschrift ableiten
				Object[] obj = allocatedItem.getObjectArr();
				for (int j = 0; j < obj.length; j++) {
					model.addColumn(obj[j].toString());
				}
			} else { // String Daten
					model.addRow(allocatedItem.getObjectArr());	
					selectedAllocatedItems.add(allocatedItem);
			}
		}
		printSelectedAllocatedItems();
		return model;
	}
	
	public static DefaultTableModel printReturnN_Parts () {
		setDefault();
		for (int i = 0; i < allocatedItems.size(); i++) {
			AllocatedItem allocatedItem = allocatedItems.get(i);
			if (i == 0) { // Ueberschrift ableiten
				Object[] obj = allocatedItem.getObjectArr();
				for (int j = 0; j < obj.length; j++) {
					model.addColumn(obj[j].toString());
				}
			} else { // String Daten
				if(allocatedItem.getIsKeyPart().equals("N")) {
					model.addRow(allocatedItem.getObjectArr());	
					selectedAllocatedItems.add(allocatedItem);
				}
			}
		}
		printSelectedAllocatedItems();
		return model;
	}
	
	public static DefaultTableModel printByPnTextSearch(String newPN) {
		setDefault();
		AllocatedItem seachForAllocatedItem = new AllocatedItem();
		seachForAllocatedItem.setNewPartNo(newPN.trim());
		if (allocatedItems.contains(seachForAllocatedItem)) {
			for (int i = 0; i < allocatedItems.size(); i++) {
				AllocatedItem allocatedItem = allocatedItems.get(i);
				if (i == 0) { // Ueberschrift ableiten
					Object[] obj = allocatedItem.getObjectArr();
					for (int j = 0; j < obj.length; j++) {
						model.addColumn(obj[j].toString());
					}
				} else { // String Daten
					if(allocatedItem.equals(seachForAllocatedItem)) {
						model.addRow(allocatedItem.getObjectArr());	
						selectedAllocatedItems.add(allocatedItem);
					}
				}
			}
		}
		printSelectedAllocatedItems();
		return model;
	}
}
