import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JScrollPane;
import java.util.Date;

public class MedicineRegestration {

	public JFrame frame;
	private JTextField medId;
	private JTextField medName;
	private JTextField meddate;
	private JTable table;
	private String medtype[]={"Capsule","powder","tablet","syrap"};
	private JComboBox comboBox;
	String sql="SELECT * FROM medicine_reg";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicineRegestration window = new MedicineRegestration();
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
	public MedicineRegestration() {
		try
		{
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(100, 100, 666, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Medicine Id");
		lblNewLabel.setBounds(212, 78, 94, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Medicine Type");
		lblNewLabel_1.setBounds(214, 169, 92, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Medicine Name");
		lblNewLabel_2.setBounds(212, 114, 94, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Date");
		lblNewLabel_4.setBounds(212, 225, 78, 25);
		frame.getContentPane().add(lblNewLabel_4);
		
		medId = new JTextField();
		medId.setBounds(361, 80, 125, 20);
		frame.getContentPane().add(medId);
		medId.setColumns(10);
		
		medName = new JTextField();
		medName.setBounds(361, 124, 124, 20);
		frame.getContentPane().add(medName);
		medName.setColumns(10);
		
		comboBox = new JComboBox(medtype);
		comboBox.setBounds(361, 171, 124, 20);
		frame.getContentPane().add(comboBox);
		Date today=new Date();
		String date=String.format("%s",today);
		
		meddate = new JTextField(date);
		meddate.setBounds(361, 227, 124, 20);
		frame.getContentPane().add(meddate);
		meddate.setColumns(10);
		TableModel tabelModel=new TableModel(sql);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 322, 548, 123);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(tabelModel);
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel_5 = new JLabel(new ImageIcon("img/pharmacy.jpg"));
		lblNewLabel_5.setBounds(28, 96, 147, 170);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Medicine Regeistration");
		lblNewLabel_6.setForeground(new Color(255, 0, 0));
		lblNewLabel_6.setFont(new Font("Stencil", Font.BOLD, 20));
		lblNewLabel_6.setBounds(125, 11, 428, 56);
		frame.getContentPane().add(lblNewLabel_6);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					String mdname,mdId,mdType,mdDate;
					try
					{
						mdname=medName.getText();
						mdId=medId.getText();
						mdType=String.format("%s",comboBox.getSelectedItem());
						mdDate=meddate.getText();
					
						
						
						
						if(mdname.isEmpty()||mdId.isEmpty()||mdType.isEmpty()||mdDate.isEmpty())
						{
							JOptionPane.showMessageDialog(null,"Please Enter all fields");
						}
						else
						{
							SQL insertMedicine=new SQL();
							int result=insertMedicine.medInsert(mdId,mdname,mdType,mdDate);
							if(result==1)
							{
								medName.setText("");
								medId.setText("");
								meddate.setText("");
								
							}
						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				
			}
		});
		btnNewButton.setBounds(196, 296, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(326, 293, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
		catch(SQLException sql)
		{
		JOptionPane.showMessageDialog(null,sql);
		}
	}
}
