import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

import java.sql.*;

import javax.swing.JOptionPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;


public class UniversityMember {
    
	private Connection conn=null;
	private PreparedStatement selectMed=null;
	private PreparedStatement selectMeds=null;
	private PreparedStatement selectPat=null;
	private PreparedStatement selectAmt=null;
	private ResultSet result=null;
	private ResultSet result1=null;
	private ResultSet result2=null;
	private ResultSet result3=null;
	private ResultSetMetaData metaData=null;
	public JFrame frame;
	private JTextField pid;
	private JTextField patName;
	private JTextField medId;
	private JTextField medType;
	private JTextField medQuant;
	private JTextArea med;
	private JComboBox comboBox;
	private final String url="jdbc:mysql://localhost:3306/hospital_system";
	private final String userName="root";
	private final String password="wizard234";
	private JTextField entryDate;
	private String patNumber;
	


	public void FillCombo()
	{
		String meds;
		try
		{
		selectMed=conn.prepareStatement("SELECT * FROM medicinestock");
		result=selectMed.executeQuery();
		while(result.next())
		{
			meds=result.getString("medName");
			comboBox.addItem(meds);
		}
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
	}
	public void FillPatient(String PatId)
	{
		String patid=null,pName=null,pres=null;
		try
		{
		selectPat=conn.prepareStatement("SELECT * FROM patienttreatment WHERE PatientId='"+PatId+"'");
		result2=selectPat.executeQuery();
		while(result2.next())
		{
			patid=result2.getString("PatientId");
			pName=result2.getString("PatientName");
			pres=result2.getString("Prescription");
			
		}
		pid.setText(patid);
		patName.setText(pName);
		med.setText(pres);
		
		
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
			
	}
	public UniversityMember(String PatId) {
		try
		{
			patNumber=PatId;
		conn=DriverManager.getConnection(url,userName,password);
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("University Member Medicne Issue");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 19));
		lblNewLabel.setBounds(10, 11, 543, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.RED, 2));
		panel.setBounds(10, 72, 284, 146);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Medicine");
		lblNewLabel_2.setBounds(0, 82, 80, 28);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Patient Id");
		lblNewLabel_3.setBounds(0, 11, 80, 28);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Patient Name");
		lblNewLabel_4.setBounds(0, 41, 80, 28);
		panel.add(lblNewLabel_4);
		
		pid = new JTextField();
		pid.setBounds(100, 15, 136, 20);
		panel.add(pid);
		pid.setColumns(10);
		pid.setEditable(false);
		
		patName = new JTextField();
		patName.setBounds(98, 49, 138, 20);
		panel.add(patName);
		patName.setColumns(10);
		patName.setEditable(false);
		
		med = new JTextArea();
		med.setBounds(100, 84, 167, 53);
		panel.add(med);
		med.setEditable(false);
		
		JLabel lblNewLabel_1 = new JLabel("Medicine Prescription");
		lblNewLabel_1.setBounds(10, 46, 141, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel("Medine Details");
		lblNewLabel_5.setBounds(346, 46, 87, 25);
		frame.getContentPane().add(lblNewLabel_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.RED));
		panel_1.setBounds(332, 69, 289, 267);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Medinine Name");
		lblNewLabel_6.setBounds(10, 11, 109, 28);
		panel_1.add(lblNewLabel_6);
		
	    comboBox = new JComboBox();
	    comboBox.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent arg0) {
	    		String medID,MedName=null,MedType=null,Medunit=null;
	    		medID=String.format("%s",comboBox.getSelectedItem());
	    		
	    		try
	    		{
	    			selectMeds=conn.prepareStatement("SELECT * FROM medicinestock WHERE medName='"+medID+"'");
	    			result1=selectMeds.executeQuery();
	    			while(result1.next())
	    			{
	    				MedName=result1.getString("medId");
	    				MedType=result1.getString("medType");
	    				Medunit=result1.getString("unitPrice");
	    				
	    			}
	    			medId.setText(MedName);
	    			medType.setText(MedType);
	    			
	    			
	    			
	    		}
	    		catch(SQLException sql)
	    		{
	    			JOptionPane.showMessageDialog(null,sql);
	    		}
	    	}
	    });
		comboBox.setBounds(129, 15, 133, 20);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel_7 = new JLabel("Medicine Id");
		lblNewLabel_7.setBounds(10, 50, 82, 22);
		panel_1.add(lblNewLabel_7);
		
		medId = new JTextField();
		medId.setBounds(129, 46, 133, 20);
		panel_1.add(medId);
		medId.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Medicine Type");
		lblNewLabel_8.setBounds(10, 83, 82, 28);
		panel_1.add(lblNewLabel_8);
		
		medType = new JTextField();
		medType.setBounds(129, 87, 133, 20);
		panel_1.add(medType);
		medType.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Quantity");
		lblNewLabel_9.setBounds(10, 128, 82, 20);
		panel_1.add(lblNewLabel_9);
		
		medQuant = new JTextField();
		medQuant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String quant=null;
				int Amount=0;
				int total=0;
				int amt=0;
				int unit=0;
				int quantity=0;
				quantity=Integer.parseInt(event.getActionCommand());
				
				amt=quantity*unit;
				
				try
				{
				selectAmt=conn.prepareStatement("SELECT * FROM igu_patients WHERE Patient_Id='"+patNumber+"'");
				result3=selectAmt.executeQuery();
				while(result3.next())
				{
					Amount=result3.getInt("Amount");
					total=total+Amount;
				}
				
				}
				catch(SQLException ex)
				{
					JOptionPane.showMessageDialog(null,ex);
				}
				
				
			}
		});
		medQuant.setBounds(129, 118, 127, 20);
		panel_1.add(medQuant);
		medQuant.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("* On entering quantity press Enter");
		lblNewLabel_14.setForeground(Color.RED);
		lblNewLabel_14.setBounds(26, 208, 193, 28);
		panel_1.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Date");
		lblNewLabel_15.setBounds(10, 189, 103, 20);
		panel_1.add(lblNewLabel_15);
		Date today=new Date();
		String date=String.format("%s",today);
		
		entryDate = new JTextField();
		entryDate.setBounds(129, 192, 133, 20);
		panel_1.add(entryDate);
		entryDate.setColumns(10);
		entryDate.setText(date);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pnum,pname,pres,mednum,medname,medtype,date;
				int medQuan,unity,amount;
				try
				{
					pnum=pid.getText();
					pname=patName.getText();
					pres=med.getText();
					medname=String.format("%s",comboBox.getSelectedItem());
					medtype=medType.getText();
					mednum=medId.getText();
					date=entryDate.getText();
					medQuan=Integer.parseInt(medQuant.getText());
					
					SQL uni=new SQL();
					uni.uniMember(pnum, pname, medname, mednum, medtype,medQuan, date);
					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Stencil", Font.PLAIN, 11));
		btnNewButton.setBounds(280, 347, 89, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Stencil", Font.BOLD, 11));
		btnNewButton_1.setBounds(393, 347, 89, 35);
		frame.getContentPane().add(btnNewButton_1);
		frame.setBounds(100, 100, 690, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FillCombo();
		FillPatient(PatId);
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql);
		}
	}
}
