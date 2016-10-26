

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{
	private Connection conn;
	private ResultSetMetaData metaData;
	private Statement statement;
	private ResultSet resultSet;
	private int numofRows;
	
	String url="jdbc:mysql://localhost:3306/hospital_system";
	String userName="root";
	String password="wizard234";
	
	private boolean connected=false;
	
	public TableModel(String query) throws SQLException
	{
		conn=DriverManager.getConnection(url,userName,password);
		
		statement=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		connected=true;
		
		setQuery(query);
	}
	public Class getColumnClass(int column) throws IllegalStateException
	{
		if(!connected)
			throw new IllegalStateException("Not connected to the Database");
		
		try
		{
			String className=metaData.getColumnClassName(column+1);
			return Class.forName(className);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Object.class;
	}
	public int getColumnCount() throws IllegalStateException
	{
		if(!connected)
          throw new IllegalStateException("Not connected to the Database");
		
		try
		{
			return metaData.getColumnCount();
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
		}
		return 0;
	}
	public String getColumnName(int column) throws IllegalStateException
	{
		if(!connected)
			throw new IllegalStateException("Not connected to the Database");
		
		try
		{
			return metaData.getColumnName(column + 1);
					
		}
		catch(SQLException e2)
		{
			e2.printStackTrace();
		}
		return "";
	}
	public int getRowCount() throws IllegalStateException
	{
		if(!connected)
			throw new IllegalStateException("Not connected to the Database");
		
		return numofRows;
	}
	public Object getValueAt(int row,int column) throws IllegalStateException
	{
		if(!connected)
			throw new IllegalStateException("Not connected to the Database");
		
		try
		{
			resultSet.absolute(row+1);
			return resultSet.getObject(column + 1);
			
		}
		catch(SQLException e3)
		{
			e3.printStackTrace();
		}
		return "";
	}
	public void setQuery(String query) throws SQLException,IllegalStateException
	{
		if(!connected)
			throw new IllegalStateException("Not connected to the Database");
		
		resultSet=statement.executeQuery(query);
		
		metaData=resultSet.getMetaData();
		
		resultSet.last();
		numofRows=resultSet.getRow();
		
		fireTableStructureChanged();
		
	}
	public void disconnectFromDatabase()
	{
		if(connected)
		{
			try
			{
				resultSet.close();
				statement.close();
				conn.close();
			}
			catch(SQLException e4)
			{
				e4.printStackTrace();
				
			}
			finally
			{
				connected=false;
			}
		}
	}


}
