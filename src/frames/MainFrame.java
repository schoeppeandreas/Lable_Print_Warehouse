package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame {

	private JFrame frmAsusSupportTool;
	private JTable table;
	private JTextField textFieldNewPN;
	private JScrollPane scrollPane;
	private static DefaultTableModel model = new DefaultTableModel();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmAsusSupportTool.setVisible(true);
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

		frmAsusSupportTool = new JFrame();
		frmAsusSupportTool.setResizable(false);
		frmAsusSupportTool.setTitle("Support Tool (lable print for allocated parts) - You need support? Call Andreas.Schoeppe@RTS-Services.de");
		frmAsusSupportTool.setBounds(100, 100, 844, 502);
		frmAsusSupportTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAsusSupportTool.getContentPane().setLayout(null);

		JButton btnButton = new JButton("Search and Print P/N Label");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new DefaultTableModel();
				model = ProzessAllocated.printByPnTextSearch(textFieldNewPN.getText());
				table.setModel(model);
			}
		});
	
		btnButton.setBounds(6, 6, 204, 29);
		frmAsusSupportTool.getContentPane().add(btnButton);
				
				scrollPane = new JScrollPane();
				scrollPane.setBounds(6, 34, 832, 440);
				frmAsusSupportTool.getContentPane().add(scrollPane);
		
				table = new JTable();
				scrollPane.setViewportView(table);

		textFieldNewPN = new JTextField();
		
		textFieldNewPN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){ 
					model = new DefaultTableModel();
					model = ProzessAllocated.printByPnTextSearch(textFieldNewPN.getText());
					table.setModel(model);
				}
			}
		});
		
		textFieldNewPN.setBounds(222, 5, 204, 29);
		frmAsusSupportTool.getContentPane().add(textFieldNewPN);
		textFieldNewPN.setColumns(10);
		
		JButton btnAlleLabelDucken = new JButton("Print all");
		btnAlleLabelDucken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Print all");
				model = new DefaultTableModel();
				model = ProzessAllocated.printAllParts();
				table.setModel(model);
			}
		});

		btnAlleLabelDucken.setBounds(746, 6, 92, 29);
		frmAsusSupportTool.getContentPane().add(btnAlleLabelDucken);
		
		JButton btnKeyPartsN = new JButton("Print Key Parts N");
		btnKeyPartsN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = ProzessAllocated.printReturnN_Parts();
				table.setModel(model);
			}
		});
		btnKeyPartsN.setBounds(586, 6, 148, 29);
		frmAsusSupportTool.getContentPane().add(btnKeyPartsN);
		

	}
}