import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;


public class AdmniSwitchBoard {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmniSwitchBoard window = new AdmniSwitchBoard();
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
	public AdmniSwitchBoard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 51));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eden.jpg"));
		frame.setBounds(100, 100, 587, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Receptionist");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					NewEmployee employeeR=new NewEmployee();
					employeeR.frame.setVisible(true);
				
				
			}
		});
		btnNewButton.setBounds(30, 43, 106, 64);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(new ImageIcon("img/doc.jpg"));
		btnNewButton_1.setToolTipText("\"Register Doctor\"");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					NewEmployee employeeR=new NewEmployee();
					employeeR.frame.setVisible(true);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(183, 43, 149, 115);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Pharmacist");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					NewEmployee employeeR=new NewEmployee();
					employeeR.frame.setVisible(true);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(342, 43, 106, 54);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Lab Technician");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					NewEmployee employeeR=new NewEmployee();
					employeeR.frame.setVisible(true);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(30, 205, 118, 45);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Admni");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					NewEmployee employeeR=new NewEmployee();
					employeeR.frame.setVisible(true);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(194, 206, 106, 42);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Nurse");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					NewEmployee employeeR=new NewEmployee();
					employeeR.frame.setVisible(true);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(342, 204, 96, 47);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Remove Employee");
		btnNewButton_6.setBounds(183, 283, 151, 32);
		frame.getContentPane().add(btnNewButton_6);
	}
}
