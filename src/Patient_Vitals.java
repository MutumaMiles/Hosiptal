import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.sql.*;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;


public class Patient_Vitals {

	public JFrame frame;
	private JTable table;
	private final String url="jdbc:mysql://localhost:3306/hospital_system";
	private final String userName="root";
	private final String password="wizard234";
	private Connection conn=null;
	PreparedStatement statement=null;
	ResultSet result=null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Patient_Vitals(String Pname,String sql) {
		try
		{
			
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 701, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		TableModel pCondition=new TableModel(sql);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 651, 203);
		frame.getContentPane().add(scrollPane);
		table = new JTable(pCondition);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setBounds(254, 280, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Patient Vitals");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		lblNewLabel.setBounds(151, 11, 281, 44);
		frame.getContentPane().add(lblNewLabel);
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
	}
}
