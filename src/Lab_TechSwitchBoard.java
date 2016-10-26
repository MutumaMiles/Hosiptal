import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;

import java.sql.*;

public class Lab_TechSwitchBoard {

	public JFrame frame;
	private Connection conn=null;
	private ResultSet result=null;
	private PreparedStatement selectDoc=null;
	private String url="jdbc:mysql://localhost:3306/hospital_system";
	private String user="root";
	private String password="wizard234";
	private String name;
	private JComboBox comboBox;
	

	/**
	 * Launch the application.
	 */
	public void FillCombo(String Lname)
	{
		name=Lname;
		String techsName=null;
		try
		{
			selectDoc=conn.prepareStatement("SELECT * FROM employees WHERE Employee_Id='"+name+"'");
			result=selectDoc.executeQuery();
			while(result.next())
			{
				techsName=result.getString("First_Name");
				comboBox.addItem(techsName);
				
			}
			
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
		
	}
	
	 
	public Lab_TechSwitchBoard(String Lname) {
		try
		{
			conn=DriverManager.getConnection(url,user,password);
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 537, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome ");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		lblNewLabel.setBounds(31, 11, 127, 56);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Pending requests");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String status="Pending";
				
				String sql="SELECT * FROM labrequest WHERE status='"+status+"'";
				PendingResults pending=new PendingResults(sql);
				pending.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(51, 121, 136, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Fill Results");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String LabName=String.format("%s",comboBox.getSelectedItem());
				
				LabResultsForm results=new LabResultsForm(LabName);
				results.frame.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(265, 121, 122, 33);
		frame.getContentPane().add(btnNewButton_1);
		
	    comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(181, 23, 163, 27);
		frame.getContentPane().add(comboBox);
		FillCombo(Lname);
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql);
		}
	}
}
