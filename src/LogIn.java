import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;


public class LogIn {

	public JFrame frame;
	private JTextField user;
	private JComboBox comboBox;
	private String employees[]={"Doctor","Pharmacist","Receptionist","Lab_Tech","Nurse","Admni"};
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 643, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox(employees);
		comboBox.setBounds(264, 114, 117, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Log In As");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(147, 112, 80, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(147, 206, 80, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		user = new JTextField();
		user.setBounds(264, 208, 109, 20);
		frame.getContentPane().add(user);
		user.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(147, 265, 80, 26);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(new ImageIcon("img/login.jpg"));
		lblNewLabel_3.setBounds(10, 114, 127, 169);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String euser,ejobCategory,epass;
				int result=0;
				try
				{
					euser=user.getText();
					epass=password.getText();
					ejobCategory=String.format("%s",comboBox.getSelectedItem());
					
					if(euser.equals("")||epass.equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please Enter all the fields");
					}
					else
					{
					
					SQL userLogIn=new SQL();
					result=userLogIn.logIn(euser,ejobCategory,epass);
					if(result==1)
					{
					frame.dispose();
					}
					}
					
					
					
				}
				catch(Exception sql)
				{
					
				}
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnNewButton.setBounds(188, 335, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("User Log In");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Stencil", Font.BOLD, 37));
		lblNewLabel_4.setBounds(35, 11, 322, 38);
		frame.getContentPane().add(lblNewLabel_4);
		
		password = new JPasswordField();
		password.setBounds(258, 270, 115, 20);
		frame.getContentPane().add(password);
		
		JLabel lblNewLabel_5 = new JLabel("Use your Emp id as user name");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setBounds(147, 145, 234, 50);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Stencil", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(320, 335, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
