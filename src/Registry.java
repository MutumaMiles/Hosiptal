import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;


public class Registry extends JFrame {

	public JFrame frame;
	private JTextField first;
	private JTextField last;
	private JTextField ptdid;
	private JTextField datereg;
	private JTextField DOB;
	private JTextField reside;
	private String sex[]={"Male","Female"};
	private String type[]={"Student","Staff","IGU"};
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JTextField phone;
	private JTextField Kin;
	private JTextField kinRel;
	private JTextField kincon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Registry window=new Registry();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Registry() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 874, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Patient Registration");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 46));
		lblNewLabel.setBounds(10, 11, 555, 68);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(194, 118, 100, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		first = new JTextField();
		first.setBounds(301, 118, 111, 21);
		frame.getContentPane().add(first);
		first.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Last Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(466, 115, 108, 22);
		frame.getContentPane().add(lblNewLabel_3);
		
		last = new JTextField();
		last.setBounds(614, 115, 153, 20);
		frame.getContentPane().add(last);
		last.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("PF/STD/IGU");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(194, 171, 82, 22);
		frame.getContentPane().add(lblNewLabel_4);
		
		ptdid = new JTextField();
		ptdid.setBounds(301, 174, 111, 20);
		frame.getContentPane().add(ptdid);
		ptdid.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Date");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(466, 174, 76, 20);
		frame.getContentPane().add(lblNewLabel_5);
		Date today=new Date();
		String date=String.format("%s",today);
		
		datereg = new JTextField(date);
		datereg.setBounds(614, 176, 153, 20);
		frame.getContentPane().add(datereg);
		datereg.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Gender");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(194, 227, 82, 28);
		frame.getContentPane().add(lblNewLabel_6);
		
		comboBox = new JComboBox(sex);
		comboBox.setBounds(301, 233, 111, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_7 = new JLabel("DOB");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(466, 243, 76, 22);
		frame.getContentPane().add(lblNewLabel_7);
		
		DOB = new JTextField();
		DOB.setBounds(614, 231, 153, 28);
		frame.getContentPane().add(DOB);
		DOB.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Cadre");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(194, 278, 70, 28);
		frame.getContentPane().add(lblNewLabel_8);
		
		 comboBox_1 = new JComboBox(type);
		comboBox_1.setBounds(301, 284, 111, 20);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblNewLabel_9 = new JLabel("Residence/Hall");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(466, 284, 91, 28);
		frame.getContentPane().add(lblNewLabel_9);
		
		reside = new JTextField();
		reside.setBounds(614, 286, 153, 20);
		frame.getContentPane().add(reside);
		reside.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel(new ImageIcon("img/patient.jpg"));
		lblNewLabel_10.setBounds(10, 160, 159, 165);
		frame.getContentPane().add(lblNewLabel_10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String firstName,lastName,paid,rdate,pgender,pdob,cadre,residence,paPhone,pnok,pkinrel,pkincon;
				try
				{
					firstName=first.getText();
					lastName=last.getText();
					paid=ptdid.getText();
					rdate=datereg.getText();
					pgender=String.format("%s",comboBox.getSelectedItem());
					pdob=DOB.getText();
					cadre=String.format("%s",comboBox_1.getSelectedItem());
					residence=reside.getText();
					paPhone=phone.getText();
					pnok=Kin.getText();
					pkinrel=kinRel.getText();
					pkincon=kincon.getText();
					if(firstName.isEmpty()||lastName.isEmpty()||paid.isEmpty()||rdate.isEmpty()||pgender.isEmpty()||pdob.isEmpty()||cadre.isEmpty()||residence.isEmpty()||paPhone.isEmpty()||pnok.isEmpty()||pkinrel.isEmpty()||pkincon.isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter all the Fields all the fields","error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
					
					SQL newPatient=new SQL();
					
					
					
					int res=newPatient.insertPatient(firstName,lastName,paid,rdate,pgender,pdob,cadre,residence,paPhone,pnok,pkinrel,pkincon);
					if(res==1)
					{
						JOptionPane.showMessageDialog(null,"success");
						first.setText("");last.setText("");ptdid.setText("");datereg.setText("");DOB.setText("");reside.setText("");
						phone.setText("");Kin.setText("");kinRel.setText("");kincon.setText("");
					}
					else
					{
					JOptionPane.showMessageDialog(null,"error");
					}
					}
					
					
					
				}
				catch(Exception sql1)
				{
					sql1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(383, 413, 159, 46);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_11 = new JLabel("Phone Number");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_11.setBounds(194, 317, 100, 37);
		frame.getContentPane().add(lblNewLabel_11);
		
		phone = new JTextField();
		phone.setBounds(301, 325, 117, 22);
		frame.getContentPane().add(phone);
		phone.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Next of Kin");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_12.setBounds(466, 325, 102, 25);
		frame.getContentPane().add(lblNewLabel_12);
		
		Kin = new JTextField();
		Kin.setBounds(614, 317, 153, 21);
		frame.getContentPane().add(Kin);
		Kin.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Relationship");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_13.setBounds(194, 365, 82, 20);
		frame.getContentPane().add(lblNewLabel_13);
		
		kinRel = new JTextField();
		kinRel.setBounds(301, 367, 111, 20);
		frame.getContentPane().add(kinRel);
		kinRel.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Next_Kin_Contacts");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_14.setBounds(466, 361, 122, 29);
		frame.getContentPane().add(lblNewLabel_14);
		
		kincon = new JTextField();
		kincon.setBounds(614, 365, 153, 20);
		frame.getContentPane().add(kincon);
		kincon.setColumns(10);
	}
}
