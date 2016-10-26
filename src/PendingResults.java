import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class PendingResults {

	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public PendingResults(String sql) {
		try
		{
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 675, 346);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Here are the pending Requests");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 449, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 91, 629, 76);
		frame.getContentPane().add(scrollPane);
		TableModel model=new TableModel(sql);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(394, 33, 89, 23);
		frame.getContentPane().add(btnNewButton);
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
	}
}
