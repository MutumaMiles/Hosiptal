import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PharmacySwitchBoard {

	public JFrame frame;
	private JComboBox comboBox;
	private final String url="jdbc:mysql://localhost:3306/hospital_system";
	private final String userName="root";
	private final String password="wizard234";
	private Connection conn=null;
	private PreparedStatement selectId=null;
	private PreparedStatement select=null;
	private PreparedStatement selectname=null;
	private ResultSet result=null;
	private ResultSet result1=null;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public void FillCombo(String Pname)
	{
		String name=null;
		try
		{
			selectId=conn.prepareStatement("SELECT * FROM employees WHERE Employee_Id='"+Pname+"'");
			result=selectId.executeQuery();
			while(result.next())
			{
				name=result.getString("First_Name");
				comboBox.addItem(name);
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
		
	}
	public PharmacySwitchBoard(String Pname) {
		try
		{
			conn=DriverManager.getConnection(url,userName,password);
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 464, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome ");
		lblNewLabel.setForeground(new Color(255, 51, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 128, 47);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(133, 18, 203, 33);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Issue Medicine");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name=String.format("%s",comboBox.getSelectedItem());
				String cadre=null;
				
				
			  String string=null;
			  string=JOptionPane.showInputDialog("Enter Patient Number");
			  try
			  {
				  select=conn.prepareStatement("SELECT * FROM patient WHERE Patient_Id='"+string+"'");
				  result1=select.executeQuery();
				  
				  while(result1.next())
				  {
					  cadre=result1.getString("Cadre");
				  }
				  switch(cadre)
				  {
				  case "IGU":
					  IguPurchase igu=new IguPurchase(string);
					  igu.frame.setVisible(true);
					  igu.frame.setLocationRelativeTo(null);
					  
					  break;
				 
				  case "Student":
					  UniversityMember uni=new UniversityMember(string);
					  uni.frame.setVisible(true);
					  uni.frame.setLocationRelativeTo(null);
					  break;
					
				  default:
				  JOptionPane.showMessageDialog(null,"Patient Number could not be found");
				  }
			  }
			  catch(SQLException sql)
			  {
				  JOptionPane.showMessageDialog(null,sql);
			  }
			  
			}
		});
		btnNewButton.setBackground(new Color(51, 153, 0));
		btnNewButton.setBounds(25, 84, 128, 55);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Register Medicine");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MedicineRegestration register=new MedicineRegestration();
				register.frame.setVisible(true);
				register.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton_1.setBounds(213, 84, 137, 55);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Take Stock");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MedicineStock TakeStock=new MedicineStock();
				TakeStock.frame.setVisible(true);
				TakeStock.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton_2.setBounds(133, 178, 128, 40);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Log Out");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				LogIn login=new LogIn();
				login.frame.setVisible(true);
				login.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton_3.setBounds(154, 258, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		FillCombo(Pname);
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql);
		}
	}

}
