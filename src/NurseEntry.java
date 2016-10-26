import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTable;

import java.awt.Font;
import java.sql.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;


public class NurseEntry {

	public JFrame frame;
	private JTextField pname;
	private JTextField patage;
	private JTextField pheight;
	private JTextField pattemp;
	private JTextField bloodpressure;
	private JTextField pblsugar;
	private JTextField pregdate;
	private JTextField pweight;
	private Connection conn=null;
	private Statement statement=null;
	private ResultSet result=null;
	private ResultSet result1=null;
	private ResultSetMetaData metaData=null;
	private PreparedStatement selectPid=null;
	private PreparedStatement selectPname=null;
	private String url="jdbc:mysql://localhost:3306/hospital_system";
	private String user="root";
	private String password="wizard234";
	private JComboBox comboBox;
	private JComboBox pbloodtype;
	private String bloodtypes[]={"O","A","B","AB"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NurseEntry window = new NurseEntry();
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
		String name=null;
		String nuse="Nurse";
		try
		{
			String sqlstate="SELECT * FROM patient ";
			selectPid=conn.prepareStatement(sqlstate);
			result=selectPid.executeQuery();
			
			while(result.next())
			{
				 name=result.getString("Patient_Id");
				 comboBox.addItem(name);
				 
			}
			
		}
		catch(SQLException sqlex)
		{
			JOptionPane.showMessageDialog(null,sqlex);
		}
		
	}
	public NurseEntry() {
		try
		{
		 conn=DriverManager.getConnection(url,user,password);
		 
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 739, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 60, 250, 219);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Patient Id");
		lblNewLabel.setBounds(10, 11, 95, 25);
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
						String name=String.format("%s",comboBox.getSelectedItem());
						String Pname=null;
						
						try
						{
							String sqlstate="SELECT * FROM patient WHERE Patient_Id=?";
							selectPid=conn.prepareStatement(sqlstate);
							
							selectPid.setString(1,name);
							result1=selectPid.executeQuery();
							
							while(result1.next())
							{
								Pname=result1.getString("First_Name");
								
							}
							pname.setText(Pname);
						}
						catch(SQLException ex)
						{
							
							JOptionPane.showMessageDialog(null,ex);
						}
					}
				});
		comboBox.setBounds(115, 13, 125, 20);
		panel.add(comboBox);
		
		
		JLabel lblNewLabel_1 = new JLabel("Patient Name");
		lblNewLabel_1.setBounds(10, 71, 105, 25);
		panel.add(lblNewLabel_1);
		
		pname = new JTextField();
		pname.setBounds(115, 73, 125, 20);
		panel.add(pname);
		pname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Age");
		lblNewLabel_2.setBounds(10, 126, 79, 25);
		panel.add(lblNewLabel_2);
		
		patage = new JTextField();
		patage.setBounds(115, 126, 125, 25);
		panel.add(patage);
		patage.setColumns(10);
		
		JButton btnNewButton = new JButton("Go>>");
		btnNewButton.setBounds(59, 171, 89, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(260, 60, 435, 253);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Weight");
		lblNewLabel_3.setBounds(10, 11, 75, 24);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Height");
		lblNewLabel_4.setBounds(225, 11, 69, 24);
		panel_1.add(lblNewLabel_4);
		FillCombo();
		
		pheight = new JTextField();
		pheight.setBounds(298, 13, 95, 20);
		panel_1.add(pheight);
		pheight.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Temperature");
		lblNewLabel_5.setBounds(10, 70, 75, 24);
		panel_1.add(lblNewLabel_5);
		
		pattemp = new JTextField();
		pattemp.setBounds(95, 72, 95, 20);
		panel_1.add(pattemp);
		pattemp.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Blood Type");
		lblNewLabel_6.setBounds(225, 70, 69, 24);
		panel_1.add(lblNewLabel_6);
		
		pbloodtype = new JComboBox(bloodtypes);
		pbloodtype.setBounds(298, 72, 95, 20);
		panel_1.add(pbloodtype);
		
		JLabel lblNewLabel_7 = new JLabel("Blood Pressure");
		lblNewLabel_7.setBounds(10, 123, 87, 28);
		panel_1.add(lblNewLabel_7);
		
		bloodpressure = new JTextField();
		bloodpressure.setBounds(95, 125, 95, 24);
		panel_1.add(bloodpressure);
		bloodpressure.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Blood Sugar");
		lblNewLabel_8.setBounds(213, 123, 81, 24);
		panel_1.add(lblNewLabel_8);
		
		pblsugar = new JTextField();
		pblsugar.setBounds(298, 123, 95, 20);
		panel_1.add(pblsugar);
		pblsugar.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Paid,Paname,Pblood_type,Pblood_presure,Pblood_sugar,Pdate;
				int Page,Pweight,Pheight,Ptemp;
				
				try
				{
					Paid=String.format("%s",comboBox.getSelectedItem());
					Paname=pname.getText();
					Page=Integer.parseInt(patage.getText());
					Pweight=Integer.parseInt(pweight.getText());
					Pheight=Integer.parseInt(pheight.getText());
					Ptemp=Integer.parseInt(pattemp.getText());
					Pblood_type=String.format("%s",comboBox.getSelectedItem());
					Pblood_presure=bloodpressure.getText();
					Pblood_sugar=pblsugar.getText();
					Pdate=pregdate.getText();
					
					if(Paid.isEmpty()||Paname.isEmpty()||Pblood_presure.isEmpty()||Pblood_sugar.isEmpty()||Pdate.isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter all the fields","error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						SQL insertPatCon=new SQL();
						insertPatCon.patientCondition(Paid,Paname,Page,Pweight,Pheight,Ptemp,Pblood_type,Pblood_presure,Pblood_sugar,Pdate);
						
					}
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Please enter valid values for age weight height","error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(126, 219, 89, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_10 = new JLabel("Date");
		lblNewLabel_10.setBounds(10, 162, 46, 14);
		panel_1.add(lblNewLabel_10);
		Date today=new Date();
		String date=String.format("%s",today);
		
		pregdate = new JTextField(date);
		pregdate.setBounds(95, 159, 95, 20);
		panel_1.add(pregdate);
		pregdate.setColumns(10);
		
		pweight = new JTextField();
		pweight.setBounds(89, 13, 104, 20);
		panel_1.add(pweight);
		pweight.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Patient Physical Conditions");
		lblNewLabel_9.setForeground(new Color(255, 0, 0));
		lblNewLabel_9.setFont(new Font("Stencil", Font.BOLD, 23));
		lblNewLabel_9.setBounds(51, 11, 557, 41);
		frame.getContentPane().add(lblNewLabel_9);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(253, 341, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
	}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql.getMessage());
		}
	}
}
