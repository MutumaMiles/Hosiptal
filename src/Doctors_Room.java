import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

import java.sql.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Doctors_Room {
	private final String url="jdbc:mysql://localhost:3306/hospital_system";
	private final String userName="root";
	private final String password="wizard234";
	private Connection conn=null;
	private PreparedStatement selectDocId=null;
	private PreparedStatement selectdocName=null;
	private Statement statement=null;
	private ResultSet result=null;
	private ResultSet result1=null;
	private JFrame frame;
	private JTextField DoctorName;
	private JTextField roomNo;
    private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctors_Room window = new Doctors_Room();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public void FillCombo()
	{
		String name="Doctor";
		String docId=null;
		try
		{
			selectDocId=conn.prepareStatement("SELECT * FROM employees WHERE Job_Category='"+name+"'");
			result=selectDocId.executeQuery();
			
			while(result.next())
			{
				docId=result.getString("Employee_Id");
				comboBox.addItem(docId);
			}
			
		}
		catch(SQLException sql1)
		{
			JOptionPane.showMessageDialog(null,sql1);
		}
	}
	public Doctors_Room() {
		try
		{
		conn=DriverManager.getConnection(url,userName,password);
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 450, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Doctors Room Registration");
		lblNewLabel.setForeground(new Color(255, 51, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 22));
		lblNewLabel.setBounds(23, 11, 350, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Doctors Id");
		lblNewLabel_1.setBounds(110, 61, 107, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Doctors Name");
		lblNewLabel_2.setBounds(110, 116, 91, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		DoctorName = new JTextField();
		DoctorName.setBounds(227, 121, 146, 25);
		frame.getContentPane().add(DoctorName);
		DoctorName.setColumns(20);
		
		JLabel lblNewLabel_3 = new JLabel("Room No");
		lblNewLabel_3.setBounds(110, 176, 91, 29);
		frame.getContentPane().add(lblNewLabel_3);
		
		roomNo = new JTextField();
		roomNo.setBounds(227, 180, 146, 25);
		frame.getContentPane().add(roomNo);
		roomNo.setColumns(10);
		
		JButton btnNewButton = new JButton("Register Room");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Did,Dname,Droomno;
				
				try
				{
					Did=String.format("%s",comboBox.getSelectedItem());
					Dname=DoctorName.getText();
					Droomno=roomNo.getText();
					
					if(Droomno.isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter All the Fields","error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						SQL doctorsRoom=new SQL();
						doctorsRoom.docRoom(Did,Dname,Droomno);
						
					}
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnNewButton.setBounds(145, 237, 124, 23);
		frame.getContentPane().add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String doc_Name=String.format("%s",comboBox.getSelectedItem());
				String doctor=null;;
				try
				{
					selectdocName=conn.prepareStatement("SELECT * FROM employees WHERE Employee_Id=?");
					selectdocName.setString(1,doc_Name);
					result1=selectdocName.executeQuery();
					
					while(result1.next())
					{
						doctor=result1.getString("First_Name");
					
					DoctorName.setText(doctor);
					}
					
					
				}
				catch(SQLException sql2)
				{
					JOptionPane.showMessageDialog(null,sql2);
					
				}
			}
		});
		comboBox.setBounds(227, 66, 146, 20);
		frame.getContentPane().add(comboBox);
		FillCombo();
		
		JLabel lblNewLabel_4 = new JLabel(new ImageIcon("img/doctor.jpg"));
		lblNewLabel_4.setBounds(10, 69, 90, 136);
		frame.getContentPane().add(lblNewLabel_4);
	}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql);
		}
	}
}
