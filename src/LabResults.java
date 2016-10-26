import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.sql.*;
import javax.swing.*;


public class LabResults {

	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public LabResults(String sql)
	{
		try
		{
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 622, 256);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 104, 586, 103);
		frame.getContentPane().add(scrollPane);
		TableModel results=new TableModel(sql);
		
		table = new JTable(results);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Patient Labaratory Results");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		lblNewLabel.setBounds(23, 11, 487, 46);
		frame.getContentPane().add(lblNewLabel);
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
	}
}
