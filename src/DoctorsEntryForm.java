import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;

import java.sql.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class DoctorsEntryForm {

	public JFrame frame;
	private final String url="jdbc:mysql://localhost:3306/hospital_system";
	private final String userName="root";
	private final String password="wizard234";
	private Connection conn=null;
	private PreparedStatement selectPid=null;
	private PreparedStatement selectPName=null;
	private ResultSet result=null;
	private ResultSet result1=null;
	private JComboBox patientId;
	 private JTextField PatName;
	 private JTextField dot;
	 private JTextArea diagnosis;
	 private JTextArea presription;
	 private JTextArea observe;
	 private JTextArea prescription;
	 private JTextArea patComp;
	 private JComboBox doctname;
	

	/**
	 * Create the application.
	 */
	public void FillCombo(String Dname)
	{
		String paID=null;
		String status="Pending";
		try
		{
			selectPid=conn.prepareStatement("SELECT * FROM appointments WHERE Doctors_Name='"+Dname+"' AND Status='"+status+"'");
			result=selectPid.executeQuery();
			while(result.next())
			{
				paID=result.getString("Patient_Id");
				patientId.addItem(paID);
				
			}
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql);
		}
	}
	
	public DoctorsEntryForm(String Dname) {
		try
		{
	    conn=DriverManager.getConnection(url,userName,password);
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 630, 709);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Patient ID");
		lblNewLabel.setBounds(206, 139, 89, 26);
		frame.getContentPane().add(lblNewLabel);
		
		patientId = new JComboBox();
		patientId.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				String PId=String.format("%s",patientId.getSelectedItem());
				String Pname=null;
				
				try
				{
					selectPName=conn.prepareStatement("SELECT * FROM appointments WHERE Patient_Id='"+PId+"'");
					result1=selectPName.executeQuery();
					
					while(result1.next())
					{
						Pname=result1.getString("Patient_Name");
					
				    }
					PatName.setText(Pname);
					
				}
				catch(SQLException sql1)
				{
					JOptionPane.showMessageDialog(null,sql1);
				}
			}
		});
		patientId.setBounds(327, 141, 237, 23);
		frame.getContentPane().add(patientId);
		
		JLabel lblNewLabel_1 = new JLabel("Observation");
		lblNewLabel_1.setBounds(206, 338, 101, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		observe = new JTextArea();
		observe.setLineWrap(true);
		observe.setBounds(327, 338, 237, 40);
		frame.getContentPane().add(observe);
		
		JLabel lblNewLabel_2 = new JLabel("Diagnosis");
		lblNewLabel_2.setBounds(206, 419, 89, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		diagnosis = new JTextArea();
		diagnosis.setBounds(327, 405, 237, 34);
		frame.getContentPane().add(diagnosis);
		
		JLabel lblNewLabel_3 = new JLabel("Prescription");
		lblNewLabel_3.setBounds(204, 484, 91, 26);
		frame.getContentPane().add(lblNewLabel_3);
		
		prescription = new JTextArea();
		prescription.setBounds(327, 470, 237, 40);
		frame.getContentPane().add(prescription);
		
		JButton btnNewButton = new JButton("Send to Pharmacy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String PId,Pname,comp,observation,diag,pres,Dname,DOT;
				int count=0;
				PId=String.format("%s",patientId.getSelectedItem());
				Pname=PatName.getText();
				comp=patComp.getText();
				observation=observe.getText();
				diag=diagnosis.getText();
				pres=prescription.getText();
				Dname=String.format("%s",doctname.getSelectedItem());
				DOT=dot.getText();
				if(comp.isEmpty()||observation.isEmpty()||diag.isEmpty()||pres.isEmpty()||pres.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Enter all the fields");
				}
				else
				{
					SQL treat=new SQL();
					count=treat.treatpatient(PId,Pname,comp,observation,diag,pres,Dname,DOT);
					if(count==1)
					{
						frame.dispose();
					}
					
				}
			}
		});
		btnNewButton.setBounds(10, 634, 125, 26);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Send To Lab");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Patient=String.format("%s",patientId.getSelectedItem());
				String Doctor=String.format("%s",doctname.getSelectedItem());
				
				LabRequest request=new LabRequest(Patient,Doctor);
				request.frame.setVisible(true);
				request.frame.setLocationRelativeTo(null);
				request.frame.setLocationRelativeTo(null);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(145, 636, 128, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel(new ImageIcon("img/doc.jpg"));
		lblNewLabel_4.setBounds(29, 141, 161, 201);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Patient Treatment Form");
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setFont(new Font("Stencil", Font.BOLD, 25));
		lblNewLabel_5.setBounds(79, 11, 483, 58);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Complaints");
		lblNewLabel_6.setBounds(206, 272, 89, 26);
		frame.getContentPane().add(lblNewLabel_6);
		
		
		
	    patComp = new JTextArea();
		patComp.setBounds(327, 250, 237, 48);
		frame.getContentPane().add(patComp);
		
		JLabel lblNewLabel_7 = new JLabel("Patient Name");
		lblNewLabel_7.setBounds(206, 193, 89, 28);
		frame.getContentPane().add(lblNewLabel_7);
		
		PatName = new JTextField();
		PatName.setBounds(327, 194, 235, 26);
		frame.getContentPane().add(PatName);
		PatName.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Doctor Name");
		lblNewLabel_8.setBounds(206, 534, 101, 26);
		frame.getContentPane().add(lblNewLabel_8);
		
		doctname = new JComboBox();
		doctname.setBounds(331, 537, 198, 20);
		frame.getContentPane().add(doctname);
		doctname.addItem(Dname);
		
		JLabel lblNewLabel_9 = new JLabel("Date");
		lblNewLabel_9.setBounds(204, 577, 91, 23);
		frame.getContentPane().add(lblNewLabel_9);
		Date today=new Date();
		String date=String.format("%s",today);
		
		dot = new JTextField(date);
		dot.setBounds(327, 575, 202, 26);
		frame.getContentPane().add(dot);
		dot.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Back To SwitchBoard");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String DocName=String.format("%s",doctname.getSelectedItem());
				try
				{
					
					frame.dispose();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,ex);
				}
			}
			
		});
		btnNewButton_2.setBounds(438, 636, 141, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View Lab Results");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String patIdent=JOptionPane.showInputDialog("Enter the patient Number");
				String sqlstate="SELECT * FROM labresults WHERE PatientId='"+patIdent+"'";
				LabResults lab=new LabResults(sqlstate);
				lab.frame.setVisible(true);
				lab.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton_3.setBounds(283, 634, 145, 26);
		frame.getContentPane().add(btnNewButton_3);
		FillCombo(Dname);
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
	}
}
