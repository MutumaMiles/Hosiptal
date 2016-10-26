import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;



public class DoctorSwitchBoard extends JFrame{

	public JFrame frame;
	private Connection conn=null;
	private ResultSet result=null;
	private PreparedStatement selectDoc=null;
	private PreparedStatement selectStatus=null;
	private String url="jdbc:mysql://localhost:3306/hospital_system";
	private String user="root";
	private String password="wizard234";
	private JComboBox comboBox; 
	private String name;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public void FillCombo(String Pname)
	
	{
		name=Pname;
		String Dname=null;
		try
		{
			selectDoc=conn.prepareStatement("SELECT * FROM doctors WHERE doctors_Id='"+name+"'");
			result=selectDoc.executeQuery();
			while(result.next())
			{
				Dname=result.getString("doctors_Name");
				comboBox.addItem(Dname);
			}
			
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql);
		}
		
	}
	public DoctorSwitchBoard(final String Pname) {
		try
		{
			
			conn=DriverManager.getConnection(url,user,password);
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 519, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome Doctor");
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 226, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("View Pending Appointments");
		btnNewButton.setFont(new Font("Stencil", Font.BOLD, 16));
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.setForeground(new Color(0, 0, 153));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String status="Pending";
				String Dname=String.format("%s",comboBox.getSelectedItem());
				String sql=null;
				try
				{
					sql="SELECT * FROM appointments WHERE Status='"+status+"' AND Doctors_Name='"+Dname+"'";
					PendingAppointments pedding=new PendingAppointments(sql);
					pedding.frame.setVisible(true);
					pedding.frame.setLocationRelativeTo(null);
					
				}
				catch(Exception ex1)
				{
					JOptionPane.showMessageDialog(null,ex1);
				}
				
			}
		});
		btnNewButton.setBounds(53, 71, 399, 52);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Treat Patient");
		btnNewButton_1.setFont(new Font("Stencil", Font.BOLD, 16));
		btnNewButton_1.setBackground(new Color(255, 255, 0));
		btnNewButton_1.setForeground(new Color(0, 0, 153));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String doctor=String.format("%s",comboBox.getSelectedItem());
				DoctorsEntryForm treatPat=new DoctorsEntryForm(doctor);
				treatPat.frame.setVisible(true);
				treatPat.frame.setLocationRelativeTo(null);
				
			}
		});
		btnNewButton_1.setBounds(53, 148, 399, 52);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View Patient Vitals");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pid=JOptionPane.showInputDialog("Enter The Patient Id to View patient conditions");
				String sqlStatement="SELECT * FROM patient_conditions WHERE patient_id='"+pid+"'";
				String pname=String.format("%s",comboBox.getSelectedItem());
				Patient_Vitals vitals=new Patient_Vitals(pname,sqlStatement);
				vitals.frame.setVisible(true);
				vitals.frame.setLocationRelativeTo(null);
				
			}
		});
		btnNewButton_2.setFont(new Font("Stencil", Font.BOLD, 16));
		btnNewButton_2.setBackground(new Color(255, 255, 0));
		btnNewButton_2.setForeground(new Color(0, 0, 153));
		btnNewButton_2.setBounds(53, 225, 399, 39);
		frame.getContentPane().add(btnNewButton_2);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Stencil", Font.BOLD, 16));
		comboBox.setBounds(222, 10, 203, 30);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton_3 = new JButton("Log Out");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
				LogIn log=new LogIn();
				log.frame.setVisible(true);
				log.frame.setLocationRelativeTo(null);
				frame.dispose();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		btnNewButton_3.setForeground(new Color(255, 0, 0));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(309, 306, 116, 23);
		frame.getContentPane().add(btnNewButton_3);
		FillCombo(Pname);
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
	}
}
