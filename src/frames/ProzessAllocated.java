package frames;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProzessAllocated {
	private static List<AllocatedItem> allocatedItems;
	public static List<AllocatedItem> selectedAllocatedItems = new ArrayList<>();
	private static DefaultTableModel model = new DefaultTableModel();
	/*
	 * Es werden 2 Variablen definiert, ein Integer und eine Arrayliste.
	 * Bei "print all" wird der Integer printedOnce auf seinen Wert überprüft und wenn nötig ein Dialog angezeigt.
	 * Zur Arrayliste werden alle Suchwerte hinzugefügt und wenn ein Wert bereits enthalten ist wird ein Dialog angezeigt.
	 * 
	 */
	private static int printedOnce = 0;
	private static ArrayList<String> searchedOnce = new ArrayList<String>();  
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
		// Bers Tataev
		if(printedOnce==1) {
			
			int reply = JOptionPane.showConfirmDialog(null, "Du hast bereits gedruckt, willst es nochmal tun?", "Achtung", JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.NO_OPTION) {
	        		return null;
	        }
			
		}
		// Bers Tataev
		
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
		printedOnce = 1; // Bers Tataev
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
		// Bers Tataev
		if(searchedOnce.contains(newPN) || printedOnce == 1) {	
			int reply = JOptionPane.showConfirmDialog(null, "Du hast diese Nummer bereits gedruckt, willst es nochmal tun?", "Achtung", JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.NO_OPTION) {
	        		return null;
	        }
			
		}
		// Bers Tataev
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
		searchedOnce.add(newPN);
		printSelectedAllocatedItems();
		return model;
	}
}
