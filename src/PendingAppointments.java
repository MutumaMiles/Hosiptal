import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PendingAppointments {

	public JFrame frame;
	private JTable table;
	private Connection conn=null;
	private ResultSet result=null;
	private PreparedStatement selectDoc=null;
	private String url="jdbc:mysql://localhost:3306/hospital_system";
	private String user="root";
	private String password="wizard234"; 
	String name;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public PendingAppointments(String Pname) {
		try
		{
			conn=DriverManager.getConnection(url,user,password);

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 668, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		TableModel peddingAppoint=new TableModel(Pname);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 632, 222);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(peddingAppoint);
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel = new JLabel("Here are the Pending Appointments");
		lblNewLabel.setForeground(new Color(255, 51, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		lblNewLabel.setBounds(46, 11, 421, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(210, 299, 104, 30);
		frame.getContentPane().add(btnNewButton);
		
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
	}
}
