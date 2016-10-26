import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.sql.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LabResultsForm {

	public JFrame frame;
	private JTextField date;
	private JComboBox patId;
	private JComboBox TechsName;
	private String url="jdbc:mysql://localhost:3306/hospital_system";
	private String user="root";
	private String password="wizard234";
	private Connection conn=null;
	private ResultSet result=null;
	private ResultSet result1=null;
	private PreparedStatement selectDoc=null;
	private PreparedStatement selectStatus=null;
	private JTextField speci;
	private JTextArea testResults;

	
	public void FillCombo()
	{
		String Pname=null;
		String status="Pending";
		try
		{
			selectDoc=conn.prepareStatement("SELECT * FROM labrequest WHERE Status='"+status+"'");
			result=selectDoc.executeQuery();
			while(result.next())
			{
				Pname=result.getString("PatientId");
				patId.addItem(Pname);
			}
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql);
		}
	}
	public LabResultsForm(String Lname) {
	     try
	     {
	    	 conn=DriverManager.getConnection(url,user,password);
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Dialog", Font.BOLD, 15));
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 596, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Patient ID");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(190, 75, 106, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Specimen");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setBounds(185, 126, 70, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Results");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_2.setBounds(188, 188, 94, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		testResults = new JTextArea();
		testResults.setText("");
		testResults.setBounds(356, 192, 171, 57);
		frame.getContentPane().add(testResults);
		
		patId = new JComboBox();
		patId.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String name=String.format("%s",patId.getSelectedItem());
				
				String testSpeci=null;
				try
				{
					selectStatus=conn.prepareStatement("SELECT * FROM labrequest WHERE PatientId='"+name+"'");
					result=selectStatus.executeQuery();
					while(result.next())
					{
						testSpeci=result.getString("Specimen");
					}
					speci.setText(testSpeci);
					
					
				}
				catch(SQLException ex)
				{
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		patId.setBounds(356, 81, 129, 20);
		frame.getContentPane().add(patId);
		
		JLabel lblNewLabel_3 = new JLabel("LabTechnician's Name");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_3.setBounds(175, 285, 171, 29);
		frame.getContentPane().add(lblNewLabel_3);
		
		TechsName = new JComboBox();
		TechsName.setBounds(356, 291, 171, 20);
		frame.getContentPane().add(TechsName);
		TechsName.addItem(Lname);
		
		JButton btnNewButton = new JButton("Send To Doctor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PID,testSpec,testResult,techName,testDate;
				
				try
				{
					PID=String.format("%s",patId.getSelectedItem());
					testSpec=speci.getText();
					testResult=testResults.getText();
					techName=String.format("%s",TechsName.getSelectedItem());
					testDate=date.getText();
					
					if(testResult.isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Fill the Results");
					}
					else
					{
						SQL results=new SQL();
						results.LabResults(PID,testSpec,testResult,techName,testDate);
					}
					
				}
				catch(Exception ex1)
				{
					JOptionPane.showMessageDialog(null,ex1);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBackground(new Color(255, 255, 51));
		btnNewButton.setBounds(223, 375, 146, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel(new ImageIcon("img/lab.jpg"));
		lblNewLabel_4.setBounds(10, 150, 162, 181);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Patient Lab Results Form");
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setFont(new Font("Stencil", Font.BOLD, 22));
		lblNewLabel_5.setBounds(59, 11, 407, 57);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Date");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(175, 338, 97, 22);
		frame.getContentPane().add(lblNewLabel_6);
		Date today=new Date();
		String date1=String.format("%s",today);
		
		date = new JTextField(date1);
		date.setBounds(353, 338, 171, 26);
		frame.getContentPane().add(date);
		date.setColumns(10);
		
		speci = new JTextField();
		speci.setBounds(356, 129, 129, 20);
		frame.getContentPane().add(speci);
		speci.setColumns(10);
		FillCombo();
	     }
	     catch(SQLException sql1)
	     {
	    	 JOptionPane.showMessageDialog(null,sql1);
	     }
	}
}
