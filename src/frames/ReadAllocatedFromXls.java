package frames;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class ReadAllocatedFromXls {
	/**
	 * creates an {@link HSSFWorkbook} with the specified OS filename.
	 */
	private static HSSFWorkbook readFile(String filename) throws IOException {
		try (FileInputStream fis = new FileInputStream(filename)) {
			return new HSSFWorkbook(fis); // NOSONAR - should not be closed here
		}
	}

	public static List<AllocatedItem> read() {
		
//		CELL col=8 VALUE=STRING value=APPLICANT
//		CELL col=32 VALUE=STRING value=IS_KEY_PART
//		CELL col=43 VALUE=STRING value=LOCATION
//		CELL col=47 VALUE=STRING value=NEW_PART_DESC
//		CELL col=48 VALUE=STRING value=NEW_PART_NO
//		CELL col=86 VALUE=STRING value=RMA_NO
//		CELL col=89 VALUE=STRING value=REAL_LOCATION
		List<Integer> intArray = Arrays.asList(8, 32, 43, 47, 48, 86, 89);
		
		List<AllocatedItem> allocatedItems = new ArrayList<>();

		try (HSSFWorkbook wb = ReadAllocatedFromXls.readFile("allocated.xls")) {
			//System.out.println("Data dump:\n");
			HSSFSheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			//System.out.println("Sheet " + 0 + " \"" + wb.getSheetName(0) + "\" has " + rows + " row(s).");
			for (int r = 0; r < rows; r++) {
				HSSFRow row = sheet.getRow(r);
				if (row == null) {
					continue;
				}
				//System.out.println("\nROW " + row.getRowNum() + " has " + row.getPhysicalNumberOfCells() + " cell(s).");
				AllocatedItem rowItem = new AllocatedItem();
				allocatedItems.add(rowItem);
				for (int c = 0; c < row.getLastCellNum(); c++) {
					if (intArray.contains(c)) {
						//System.out.println(intArray.contains(c));

						HSSFCell cell = row.getCell(c);
						String value = "";
						//Es wird nur String benötigt, um dies abzusichern der Vergleich.
						if(cell.getCellTypeEnum() == CellType.STRING ) {
							value = cell.getStringCellValue();
						}				
						switch (c) {
							case 8:	rowItem.setApplicant(value);
							case 32: rowItem.setIsKeyPart(value);
							case 43: rowItem.setLocation(value);
							case 47: rowItem.setNewPartDesc(value);
							case 48: rowItem.setNewPartNo(value);
							case 86: rowItem.setRmaNo(value);
							case 89: rowItem.setRealLocation(value);
						}
//						if (cell != null) {
//							switch (cell.getCellTypeEnum()) {
//							case FORMULA:
//								value = "FORMULA value=" + cell.getCellFormula();
//								break;
//
//							case NUMERIC:
//								value = "NUMERIC value=" + cell.getNumericCellValue();
//								break;
//
//							case STRING:
//								value = "STRING value=" + cell.getStringCellValue();
//								break;
//
//							case BLANK:
//								value = "<BLANK>";
//								break;
//
//							case BOOLEAN:
//								value = "BOOLEAN value-" + cell.getBooleanCellValue();
//								break;
//
//							case ERROR:
//								value = "ERROR value=" + cell.getErrorCellValue();
//								break;
//
//							default:
//								value = "UNKNOWN value of type " + cell.getCellTypeEnum();
//							}
//
//							System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE=" + value);
//						}
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allocatedItems;

	}

}
