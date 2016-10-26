import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.*;
import java.sql.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BookAppointment {

	public JFrame frame;
	 public JTextField patName;
	public  JTextField roomNumber;
	public JComboBox NurseName;
	public JComboBox patientId;
	public JComboBox DktName;
	private Connection conn=null;
	private ResultSet result=null;
	private ResultSet result1=null;
	private ResultSet result2=null;
	private ResultSet result3=null;
	private ResultSet result4=null;
	private PreparedStatement selectNurse=null;
	private PreparedStatement selectDoc=null;
	private PreparedStatement selectPatId=null;
	private PreparedStatement selectPatName=null;
	private PreparedStatement selectRoom=null;
    private String url="jdbc:mysql://localhost:3306/hospital_system";
    private String user="root";
    private String password="wizard234";
    private String Nurse;
	/**
	 * Launch the application.
	 */
		

	/**
	 * Create the application.
	 */
	public BookAppointment(String Nname) {
		Nurse=Nname;
		try
		{
			conn=DriverManager.getConnection(url,user,password);
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.getContentPane().setForeground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 572, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Appointment");
		lblNewLabel.setForeground(new Color(255, 51, 51));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		lblNewLabel.setBounds(51, 11, 434, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nurse Name");
		lblNewLabel_1.setBounds(217, 86, 97, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Patient Id");
		lblNewLabel_2.setBounds(217, 131, 97, 24);
		frame.getContentPane().add(lblNewLabel_2);
		
		NurseName = new JComboBox();
		NurseName.setBounds(334, 89, 122, 20);
		frame.getContentPane().add(NurseName);
		NurseName.addItem(Nurse);
		
		
		patientId = new JComboBox();
		patientId.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				String Pname=String.format("%s",patientId.getSelectedItem());
				String getName=null;
				
				try
				{
					selectPatName=conn.prepareStatement("SELECT * FROM patient WHERE Patient_Id='"+Pname+"'");
					result3=selectPatName.executeQuery();
					
					while(result3.next())
					{
						getName=result3.getString("First_Name");
					}
					patName.setText(getName);
					
					
				}
				catch(SQLException ex1)
				{
					JOptionPane.showMessageDialog(null,ex1);
				}
			}
		});
		patientId.setBounds(334, 133, 122, 20);
		frame.getContentPane().add(patientId);
		
		
		JLabel lblNewLabel_3 = new JLabel("Patient Name");
		lblNewLabel_3.setBounds(217, 183, 108, 24);
		frame.getContentPane().add(lblNewLabel_3);
		
		patName = new JTextField();
		patName.setBounds(334, 185, 122, 20);
		frame.getContentPane().add(patName);
		patName.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("DoctorName");
		lblNewLabel_4.setBounds(217, 238, 108, 24);
		frame.getContentPane().add(lblNewLabel_4);
		
		DktName = new JComboBox();
		DktName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event2) {
				String DkName=String.format("%s",DktName.getSelectedItem());
				String room=null;
				
				try
				{
					selectRoom=conn.prepareStatement("SELECT * FROM doctors WHERE doctors_Name='"+DkName+"'");
					result4=selectRoom.executeQuery();
					
					while(result4.next())
					{
						room=result4.getString("roomNo");
						
					}
					roomNumber.setText(room);
				}
				catch(SQLException sqlex)
				{
					JOptionPane.showMessageDialog(null,sqlex);
				}
			}
		});
		DktName.setBounds(334, 240, 122, 20);
		frame.getContentPane().add(DktName);
		
		
		JLabel lblNewLabel_5 = new JLabel("Room No");
		lblNewLabel_5.setBounds(217, 289, 97, 24);
		frame.getContentPane().add(lblNewLabel_5);
		
		roomNumber = new JTextField();
		roomNumber.setBounds(334, 291, 122, 20);
		frame.getContentPane().add(roomNumber);
		roomNumber.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel(new ImageIcon("img/nurse.jpg"));
		lblNewLabel_6.setBounds(32, 86, 152, 257);
		frame.getContentPane().add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Nname,PaId,PaName,doc,roomNum,Bstatus;
				try
				{
					Nname=String.format("%s",NurseName.getSelectedItem());
					PaId=String.format("%s",patientId.getSelectedItem());
					PaName=patName.getText();
					doc=String.format("%s",DktName.getSelectedItem());
					roomNum=roomNumber.getText();
					Bstatus="Pending";
					
					SQL bookAppointment=new SQL();
					bookAppointment.bookDoctor(Nname,PaId,PaName,doc,roomNum,Bstatus);
							
					
							
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		btnNewButton.setBounds(210, 377, 132, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(367, 377, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		setNurse();
		setDoc();
		setPatient();
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
	}

public void setNurse()
{
	String nuse="Nurse";
	String name=null;
	try
	{
		selectNurse=conn.prepareStatement("SELECT * FROM employees WHERE Job_Category='"+nuse+"'");
		result=selectNurse.executeQuery();
		while(result.next())
		{
			name=result.getString("First_Name");
			
		}
		
	}
	catch(SQLException sql)
	{
		JOptionPane.showMessageDialog(null,sql);
	}
}
public void setPatient()
{
	String name=null;
	try
	{
		selectPatId=conn.prepareStatement("SELECT * FROM patient");
		result1=selectPatId.executeQuery();
		
		while(result1.next())
		{
			name=result1.getString("Patient_Id");
			patientId.addItem(name);
		}
		
	}
	catch(SQLException sql1)
	{
		JOptionPane.showMessageDialog(null,sql1);
	}
	
}
public void setDoc()
{
	String Dname=null;
	try
	{
		selectDoc=conn.prepareStatement("SELECT * FROM doctors ");
		result2=selectDoc.executeQuery();
		
		while(result2.next())
		{
			Dname=result2.getString("doctors_Name");
			DktName.addItem(Dname);
		}
	}
	catch(SQLException sql2)
	{
		JOptionPane.showMessageDialog(null,sql2);
	}
}
}
