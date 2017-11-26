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

public class MainFrame {

	private JFrame frame;
	private JTable table;
	private JTextField textField;

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
		frame.setBounds(100, 100, 574, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnButton = new JButton("Load");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("location");
				model.addColumn("technican");
				model.addColumn("partnumber");
				
				List<WarehouseData> arr = new ArrayList<WarehouseData>();
				
				arr.add(new WarehouseData("32-10-100","Andreas","DA1245-10-0"));
				
				for (WarehouseData warehouseData : arr) {
					model.addRow(warehouseData.getObjectArr());
				}

				table.setModel(model);

				//JOptionPane.showMessageDialog(null, textField.getText());
			}
		});

		btnButton.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(btnButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 36, 562, 331);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(126, 6, 204, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
