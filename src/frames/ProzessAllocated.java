package frames;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ProzessAllocated {
	private static List<AllocatedItem> allocatedItems;
	public static List<AllocatedItem> selectedAllocatedItems = new ArrayList<>();
	private static DefaultTableModel model = new DefaultTableModel();
	
	private static void printSelectedAllocatedItems() {
		if(!selectedAllocatedItems.isEmpty())
			Tsp400Label_Allocated.output(selectedAllocatedItems);
	}
	
	public static DefaultTableModel printAllParts() {
		model = new DefaultTableModel();
		allocatedItems = new ArrayList<AllocatedItem>();
		allocatedItems = ReadAllocatedFromXls.read();
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
		model = new DefaultTableModel();
		allocatedItems = new ArrayList<AllocatedItem>();
		allocatedItems = ReadAllocatedFromXls.read();
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
		model = new DefaultTableModel();
		//table.setModel(model);
		
		// model.addColumn("APPLICANT");
		// model.addColumn("IS_KEY_PART");
		// model.addColumn("LOCATION");
		// model.addColumn("NEW_PART_DESC");
		// model.addColumn("NEW_PART_NO");
		// model.addColumn("RMA_NO");
		// model.addColumn("REAL_LOCATION");

		// CELL col=8 VALUE=STRING value=APPLICANT
		// CELL col=32 VALUE=STRING value=IS_KEY_PART
		// CELL col=43 VALUE=STRING value=LOCATION
		// CELL col=47 VALUE=STRING value=NEW_PART_DESC
		// CELL col=48 VALUE=STRING value=NEW_PART_NO
		// CELL col=86 VALUE=STRING value=RMA_NO
		// CELL col=89 VALUE=STRING value=REAL_LOCATION

		allocatedItems = new ArrayList<AllocatedItem>();
		allocatedItems = ReadAllocatedFromXls.read();

		// 90AZ0080-R90031
		// 60-N86MB1000-A02

		AllocatedItem seachForAllocatedItem = new AllocatedItem();
		seachForAllocatedItem.setNewPartNo(newPN.trim());

		//System.out.println(model.getRowCount()); // set Model to null
		//System.out.println(allocatedItems.contains(seachForAllocatedItem));

	
		if (allocatedItems.contains(seachForAllocatedItem)) {
			if(selectedAllocatedItems != null)
				selectedAllocatedItems.clear();
			
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
