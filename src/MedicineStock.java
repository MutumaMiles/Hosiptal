import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MedicineStock {

	public JFrame frame;
	private JTextField medname;
	private JTextField medtyp;
	private JTextField regdate;
	private JTextField medstok;
	private JTextField medprice;
	private JTextField mafgdate;
	private JTextField expiryDate;
	private JTable table;
	private String url="jdbc:mysql://localhost:3306/hospital_system";
	private String user="root";
	private String password="wizard234";
	private String medIds[];
	JComboBox comboBox;
	
	private Connection conn=null;
	private Statement statement=null;
	private ResultSet result=null;
	private ResultSet result1=null;
	private ResultSetMetaData metaData=null;
	private PreparedStatement insertMedStock=null;
	private PreparedStatement selectMedId=null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicineStock window = new MedicineStock();
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
		try
		{
		insertMedStock=conn.prepareStatement("SELECT * FROM medicine_reg");
		result1=insertMedStock.executeQuery();
		
		while(result1.next())
		{
			String meds=result1.getString("med_Id");
			comboBox.addItem(meds);
		}
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql.getMessage());
		}
		
	}
	public MedicineStock() {
		try
		{
		 conn=DriverManager.getConnection(url,user,password);
		 
		 
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 102));
		frame.setBounds(100, 100, 735, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MEDICINE INVENTORY STOCK");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 22));
		lblNewLabel.setBounds(57, 11, 482, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(165, 78, 544, 241);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Medicine");
		lblNewLabel_1.setBounds(10, 11, 156, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Med Id");
		lblNewLabel_2.setBounds(10, 44, 68, 22);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Medine Name");
		lblNewLabel_3.setBounds(10, 87, 84, 22);
		panel.add(lblNewLabel_3);
		
		medname = new JTextField();
		medname.setBounds(105, 88, 99, 20);
		panel.add(medname);
		medname.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Medine Type");
		lblNewLabel_4.setBounds(10, 120, 99, 24);
		panel.add(lblNewLabel_4);
		
		medtyp = new JTextField();
		medtyp.setBounds(107, 122, 101, 20);
		panel.add(medtyp);
		medtyp.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Date");
		lblNewLabel_5.setBounds(10, 152, 68, 22);
		panel.add(lblNewLabel_5);
		
		regdate = new JTextField();
		regdate.setBounds(105, 153, 103, 20);
		panel.add(regdate);
		regdate.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(252, 28, 283, 191);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Stock");
		lblNewLabel_7.setBounds(10, 11, 86, 23);
		panel_1.add(lblNewLabel_7);
		
		medstok = new JTextField();
		medstok.setBounds(145, 12, 97, 20);
		panel_1.add(medstok);
		medstok.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Unit Price");
		lblNewLabel_8.setBounds(10, 45, 58, 25);
		panel_1.add(lblNewLabel_8);
		
		medprice = new JTextField();
		medprice.setBounds(145, 47, 97, 20);
		panel_1.add(medprice);
		medprice.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Manufacture Date");
		lblNewLabel_10.setBounds(10, 81, 121, 24);
		panel_1.add(lblNewLabel_10);
		
		mafgdate = new JTextField();
		mafgdate.setBounds(145, 88, 97, 20);
		panel_1.add(mafgdate);
		mafgdate.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Expiry Date");
		lblNewLabel_11.setBounds(10, 134, 86, 23);
		panel_1.add(lblNewLabel_11);
		
		expiryDate = new JTextField();
		expiryDate.setBounds(145, 135, 97, 20);
		panel_1.add(expiryDate);
		expiryDate.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Medicine Details");
		lblNewLabel_6.setBounds(252, 0, 103, 25);
		panel.add(lblNewLabel_6);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evnt) {
				String medName=null;
				String medType=null;
				
				String medid=String.format("%s",comboBox.getSelectedItem());
				try
				{
				selectMedId=conn.prepareStatement("SELECT * FROM medicine_reg WHERE med_Id=?");
				selectMedId.setString(1,medid);
				
				result=selectMedId.executeQuery();
				while(result.next())
				{
					medName=result.getString("med_name");
					medType=result.getString("med_type");
				}
				medname.setText(medName);
				medtyp.setText(medType);
				}
				catch(SQLException sql)
				{
					JOptionPane.showMessageDialog(null,sql.getMessage());
					
				}
				
				
			}
		});
		comboBox.setBounds(105, 44, 99, 20);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mediId,mediname,meditype,medidate,medimanfdate,mediexp;
				int medistock,mediunit,meditotal;
				
				try
				{
					mediId=String.format("%s",comboBox.getSelectedItem());
					mediname=medname.getText();
					meditype=medtyp.getText();
					medidate=regdate.getText();
					medistock=Integer.parseInt(medstok.getText());
					mediunit=Integer.parseInt(medprice.getText());
					meditotal=medistock*mediunit;
					medimanfdate=mafgdate.getText();
					mediexp=expiryDate.getText();
					int result=0;
					SQL insertStock=new SQL();
					insertStock.medStock(mediId,mediname,meditype,medidate,medistock,mediunit,meditotal,medimanfdate,mediexp);
					
					
					
					
				}
				catch(Exception sql)
				{
					JOptionPane.showMessageDialog(null,sql.getMessage());
				}
			}
		});
		btnNewButton.setBounds(102, 199, 89, 31);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_12 = new JLabel(new ImageIcon("img/stock.jpg"));
		lblNewLabel_12.setBounds(10, 89, 152, 202);
		frame.getContentPane().add(lblNewLabel_12);
		
		table = new JTable();
		table.setBounds(35, 364, 674, 115);
		frame.getContentPane().add(table);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(262, 330, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		FillCombo();
	}
	catch(SQLException sql)
	{
		JOptionPane.showMessageDialog(null,sql.getMessage());
	}
	}
}
