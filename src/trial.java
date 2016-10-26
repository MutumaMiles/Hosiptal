import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.*;


public class trial {

	private JFrame frame;
	private JTextField Sname;
	private String names[]={"IF/16/14","1F/50/14"};
    private JComboBox comboBox;
    private Connection conn=null;
    private PreparedStatement selectnum=null;
    private ResultSet result=null;
    private String url="jdbc:mysql://localhost:3306/hospital_system";
    private String user="root";
    private String pass="wizard234";
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trial window = new trial();
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
	public trial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void setName()
	{
		String Dname=null;
		
		try
		{
			selectnum=conn.prepareStatement("SELECT * FROM doctors");
			result=selectnum.executeQuery();
			
			while(result.next())
			{
				Dname=result.getString("doctors_Name");
				comboBox.addItem(Dname);
			}
					
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
	}
	private void initialize() {
		try
		{
			conn=DriverManager.getConnection(url,user,pass);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("regNo");
		lblNewLabel.setBounds(162, 71, 80, 22);
		frame.getContentPane().add(lblNewLabel);
	
		
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String Pname=String.format("%s",comboBox.getSelectedItem());
				Sname.setText(Pname);
			}
		});
		
		comboBox.setBounds(265, 72, 104, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(154, 136, 8, -13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(162, 136, 58, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		Sname = new JTextField();
		Sname.setBounds(263, 137, 106, 20);
		frame.getContentPane().add(Sname);
		Sname.setColumns(10);
		setName();
		
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql);
		}
	}
}
