package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MainFrame {

	private JFrame frame;
	private JTable table;
	private JTextField textFieldNewPN;
	private List<AllocatedItem> allocatedItems;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 844, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnButton = new JButton("Load");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = new DefaultTableModel();

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
				allocatedItems = ReadAllocated.read();

				// 90AZ0080-R90031

				AllocatedItem seachForAllocatedItem = new AllocatedItem();
				seachForAllocatedItem.setNewPartNo(textFieldNewPN.getText());

				//System.out.println(model.getRowCount()); // set Model to null
				//System.out.println(allocatedItems.contains(seachForAllocatedItem));

				if (allocatedItems.contains(seachForAllocatedItem)) {
					
					for (int i = 0; i < allocatedItems.size(); i++) {
						AllocatedItem allocatedItem = allocatedItems.get(i);
						if (i == 0) { // Überschrift ableiten
							Object[] obj = allocatedItem.getObjectArr();
							for (int j = 0; j < obj.length; j++) {
								model.addColumn(obj[j].toString());
							}
						} else { // String Daten
							if(allocatedItem.equals(seachForAllocatedItem)) {
								model.addRow(allocatedItem.getObjectArr());	
							}
						}
					}

					// for (AllocatedItem allocatedItem : allocatedItems) {
					// model.addRow(allocatedItem.getObjectArr());
					// }
				}

				table.setModel(model);

				// JOptionPane.showMessageDialog(null, textField.getText());
			}
		});

		btnButton.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(btnButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 36, 832, 438);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		textFieldNewPN = new JTextField();
		textFieldNewPN.setBounds(126, 6, 241, 26);
		frame.getContentPane().add(textFieldNewPN);
		textFieldNewPN.setColumns(10);
	}
}
