import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NewEmployee extends JFrame{

	public JFrame frame;
	private JTextField first_Name;
	private JTextField last_Name;
	private JTextField email;
	private JTextField password;
	private JTextField EmpId;
	private JComboBox Job_Category;
	private JComboBox gender;
	private JComboBox marital_status;
	private String employees[]={"Doctor","Pharmacist","Receptionist","Lab_Tech","Nurse","Admni"};
	private String maritalStatus[]={"Married","Single"};
	private String sex[]={"Male","Female"};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewEmployee window = new NewEmployee();
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
	public NewEmployee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 102));
		frame.setSize(624,601);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add New Employee");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 23));
		lblNewLabel.setBounds(27, 29, 590, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(241, 123, 109, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(241, 178, 72, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(244, 225, 89, 26);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Job Category");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(241, 278, 92, 34);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Marital Status");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(241, 334, 92, 20);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Email ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(241, 377, 72, 20);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Password");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(241, 422, 109, 29);
		frame.getContentPane().add(lblNewLabel_7);
		
		first_Name = new JTextField();
		first_Name.setBounds(375, 131, 117, 20);
		frame.getContentPane().add(first_Name);
		first_Name.setColumns(10);
		
		last_Name = new JTextField();
		last_Name.setBounds(375, 179, 117, 20);
		frame.getContentPane().add(last_Name);
		last_Name.setColumns(10);
		
		gender = new JComboBox(sex);
		gender.setBounds(375, 229, 117, 20);
		frame.getContentPane().add(gender);
		
		Job_Category=new JComboBox(employees);
		Job_Category.setBounds(375, 286, 117, 20);
		frame.getContentPane().add(Job_Category);
		
		marital_status = new JComboBox(maritalStatus);
		marital_status.setBounds(375, 335, 117, 20);
		frame.getContentPane().add(marital_status);
		
		email = new JTextField();
		email.setBounds(375, 378, 117, 20);
		frame.getContentPane().add(email);
		email.setColumns(10);
		
		password = new JTextField();
		password.setBounds(375, 427, 117, 20);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel(new ImageIcon("img/dk.jpg"));
		lblNewLabel_8.setBounds(54, 136, 163, 298);
		frame.getContentPane().add(lblNewLabel_8);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String empid,faname,laname,egender,ejob,emaritalstatus,emmail,epass;
				try
				{
					empid=EmpId.getText();
					faname=first_Name.getText();
					laname=last_Name.getText();
					egender=String.format("%s",gender.getSelectedItem());
					ejob=String.format("%s",Job_Category.getSelectedItem());
					emaritalstatus=String.format("%s",marital_status.getSelectedItem());
					emmail=email.getText();
					epass=password.getText();
					
					SQL insert=new SQL();
					int result=insert.insertEmployee(empid,faname,laname,egender,ejob,emaritalstatus,emmail,epass);
					
					if(result==1)
					{
						JOptionPane.showMessageDialog(null,faname + "  Has been successfully registered","Success",JOptionPane.PLAIN_MESSAGE);
						NewEmployee employee=new NewEmployee();
						employee.frame.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"please enter all the fields","error",JOptionPane.ERROR_MESSAGE);
						
					}
					
					
							
				}
				catch(Exception ex)
				{
					ex.getMessage();
				}
				
			}
		});
		btnNewButton.setBackground(new Color(153, 204, 51));
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 13));
		btnNewButton.setBounds(276, 477, 117, 34);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_9 = new JLabel("Employee Id");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setBounds(241, 96, 109, 14);
		frame.getContentPane().add(lblNewLabel_9);
		
		EmpId = new JTextField();
		EmpId.setBounds(375, 94, 117, 20);
		frame.getContentPane().add(EmpId);
		EmpId.setColumns(10);
	}
}
