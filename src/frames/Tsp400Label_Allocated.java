package frames;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Tsp400Label_Allocated {

	private static String getTxtLine(String text) {
		StringBuffer sb = new StringBuffer();
		sb.append((char) 27);
		sb.append(text);
		sb.append((char) 10);
		sb.append((char) 00);
		return sb.toString();
	}

	public static void output(List<AllocatedItem> selectedAllocatedItems) {
		StringBuffer sb = new StringBuffer();
		for (AllocatedItem allocatedItem : selectedAllocatedItems) {
			sb.append(getTxtLine("#1,0005"));
			sb.append(getTxtLine("#2,0100"));
			//sb.append(getTxtLine("R2"));
			sb.append(getTxtLine("C"));
			sb.append(getTxtLine("D0800"));
			
			//LableID;AbstandLinks;AbstandOben
			
			sb.append(getTxtLine("PC01;0020,0005,1,2,4,00,01")); //Applicant
			sb.append(getTxtLine("PC02;0020,0090,1,2,2,00,02")); //RmaNo
			sb.append(getTxtLine("PC03;0020,0160,1,2,2,00,02")); //Location
			sb.append(getTxtLine("PC04;0020,0240,1,2,4,00,02")); //PN No
			sb.append(getTxtLine("PC05;0020,0380,1,2,1,00,02")); //PN Desc
			sb.append(getTxtLine("PC06;0560,0720,1,2,2,00,02")); //KeyPart
			
			sb.append(getTxtLine("PC07;0020,0440,1,2,4,00,02")); //Replaced
			sb.append(getTxtLine("PC08;0020,0530,1,2,4,00,02")); //DOA
			sb.append(getTxtLine("PC09;0020,0620,1,2,4,00,02")); //Gutteil
			
			sb.append(getTxtLine("PB00;0400,0090,1,7,0,0050")); //RmaNo Barcode
			sb.append(getTxtLine("PB01;0020,0310,1,7,0,0060")); //PN No Barcode


			sb.append(getTxtLine("B"));
			
			sb.append(getTxtLine("RC01;" + allocatedItem.getApplicant()));
			sb.append(getTxtLine("RC02;RMA:" + allocatedItem.getRmaNo()));
			sb.append(getTxtLine("RC03;Location: " + allocatedItem.getLocation() + "(" +  allocatedItem.getRealLocation() + ")" ));
			sb.append(getTxtLine("RC04;" + allocatedItem.getNewPartNo()));
			sb.append(getTxtLine("RC05;" + allocatedItem.getNewPartDesc()));
			sb.append(getTxtLine("RC06;KeyPart:" + allocatedItem.getIsKeyPart())); //KeyPart
			
			sb.append(getTxtLine("RC07;( ) Replaced - BAD"));
			sb.append(getTxtLine("RC08;( ) DOA - BAD"));
			sb.append(getTxtLine("RC09;( ) Gutteil - Good"));
			
			
			sb.append(getTxtLine("RB00;" + allocatedItem.getRmaNo()));
			sb.append(getTxtLine("RB01;" + allocatedItem.getNewPartNo()));


			sb.append(getTxtLine("I"));
		}
		createFile(sb, "label.dat");
		
		try {
			printFile("cmd.exe /C Label.bat");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void printFile(String cmd) {
		Runtime run = Runtime.getRuntime();
		Process p = null;
		try {
			p = run.exec(cmd);
			p.waitFor();
			System.out.println(p.exitValue());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			p.destroy();
		}
	}

	private static void createFile(StringBuffer sb, String fileName) {
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			pWriter.print(sb);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
	}
}
