import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;


public class NurseSwitchBoard {

	public JFrame frame;
	private String url="jdbc:mysql://localhost:3306/hospital_system";
	private String user="root";
	private String password="wizard234";
	private Connection conn=null;
	private ResultSet result=null;
	private PreparedStatement selectNurseName=null;
    private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
    public void FillNurse(String Pname)
    {
    	String name=null;
    	try
    	{
    		selectNurseName=conn.prepareStatement("SELECT * FROM employees WHERE Employee_Id='"+Pname+"'");
    		result=selectNurseName.executeQuery();
    		
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
	public NurseSwitchBoard(String Pname) {
		try
		{
		conn=DriverManager.getConnection(url,user,password);
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 508, 368);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("BookAppointment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name=String.format("%s",comboBox.getSelectedItem());
				BookAppointment book=new BookAppointment(name);
				book.frame.setVisible(true);
				book.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton.setBounds(23, 103, 151, 58);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Take Patient Vitals");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NurseEntry vitals=new NurseEntry();
				vitals.frame.setVisible(true);
				vitals.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton_1.setBounds(237, 103, 141, 58);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Welcome  Nurse ");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 20));
		lblNewLabel.setBounds(23, 11, 187, 45);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(237, 17, 157, 33);
		frame.getContentPane().add(comboBox);
		Calendar today=Calendar.getInstance();
		
		JLabel lblNewLabel_1 = new JLabel(String.format("%s",today.getTime()));
		lblNewLabel_1.setBounds(128, 243, 187, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("LogOut");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				LogIn login=new LogIn();
				login.frame.setVisible(true);
				login.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton_2.setBounds(142, 199, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		FillNurse(Pname);
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql);
		}
	}
}
