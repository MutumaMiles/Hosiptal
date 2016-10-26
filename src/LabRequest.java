import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.sql.*;
import java.util.Date;

public class LabRequest {

	public JFrame frame;
	private JTextField dateSent;
	private String test[]={"sputum","blood","stool","urine"};
	private JComboBox patientId;
	private JComboBox Dname;
	private JComboBox testSpecimen;
	private JTextArea briefHx;
	private JTextArea provHx; 
	


	/**
	 * Create the application.
	 */
	public LabRequest(String Pid,String doctorName) {
		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 755, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Patient Id");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(241, 69, 114, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Brief Hx");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(241, 129, 114, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Provisional Hx");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(241, 221, 114, 26);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Specimen");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(241, 282, 114, 26);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Date");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(241, 332, 114, 24);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Doctors's Name");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(237, 386, 118, 30);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Submit Request");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(51, 204, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String PID,brief,prov,speci,reqdate,DoctName,reqstatus;
				try
				{
					PID=String.format("%s",patientId.getSelectedItem());
					brief=briefHx.getText();
					prov=provHx.getText();
					speci=String.format("%s",testSpecimen.getSelectedItem());
					reqdate=dateSent.getText();
					DoctName=String.format("%s",Dname.getSelectedItem());
					reqstatus="Pending";
					
					if(brief.isEmpty()||prov.isEmpty()||speci.isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter All the fields");
					}
					else
					{
					SQL lab=new SQL();
					lab.requestLab(PID,brief,prov,speci,reqdate,DoctName,reqstatus);
					frame.dispose();
					}
					
				}
				catch(Exception sql)
				{
					JOptionPane.showMessageDialog(null,"error");
				}
			}
		});
		btnNewButton.setBounds(315, 470, 177, 31);
		frame.getContentPane().add(btnNewButton);
		
		patientId = new JComboBox();
		patientId.setBounds(377, 72, 154, 20);
		frame.getContentPane().add(patientId);
		
		briefHx = new JTextArea();
		briefHx.setBounds(377, 130, 267, 47);
		frame.getContentPane().add(briefHx);
		
		provHx = new JTextArea();
		provHx.setBounds(377, 188, 267, 60);
		frame.getContentPane().add(provHx);
		Date today=new Date();
		String date=String.format("%s",today);
		
		dateSent = new JTextField(date);
		dateSent.setBounds(377, 334, 187, 20);
		frame.getContentPane().add(dateSent);
		dateSent.setColumns(10);
		
		Dname = new JComboBox();
		Dname.setBounds(377, 391, 187, 20);
		frame.getContentPane().add(Dname);
		
		JLabel lblNewLabel_6 = new JLabel(new ImageIcon("img/lab.jpg"));
		lblNewLabel_6.setBounds(10, 129, 213, 282);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Labaratory Request Form");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setFont(new Font("Stencil", Font.BOLD, 30));
		lblNewLabel_7.setBounds(30, 11, 501, 47);
		frame.getContentPane().add(lblNewLabel_7);
		
		testSpecimen = new JComboBox(test);
		testSpecimen.setBounds(377, 287, 187, 20);
		frame.getContentPane().add(testSpecimen);
		Dname.addItem(doctorName);
		patientId.addItem(Pid);
	}
}
