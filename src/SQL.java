

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;

public class SQL {
	private final String url="jdbc:mysql://127.0.0.1:3306/hospital_system";
	private final String userName="root";
	private final String password="wizard234";
	
	private Connection conn=null;
	private PreparedStatement insertNewEmployee=null;
	private PreparedStatement insertNewPatient=null;
	private PreparedStatement insertMed=null;
	private PreparedStatement insertmedStock=null;
	private PreparedStatement insertPatientCondition=null;
	private PreparedStatement insertDocRoom=null;
	private PreparedStatement patientTreatment=null;
	private PreparedStatement appointmentUpdate=null;
	private PreparedStatement issueMed=null;
	private PreparedStatement labRequest=null;
	private PreparedStatement labResults=null;
	private PreparedStatement requestUpdate=null;
	private PreparedStatement bookDoc=null;
	private PreparedStatement iguPatient=null;
	private PreparedStatement universityMember=null;
	private Statement statement=null;
	private ResultSet result=null;
	private ResultSetMetaData metaData=null;
	
	public SQL()
	{
		try
		{
			conn=DriverManager.getConnection(url,userName,password);
			insertNewEmployee=conn.prepareStatement("INSERT INTO employees"+
			"(Employee_Id,First_Name,Last_Name,Gender,Job_Category,Marital_Status,Email,Password)"+
					"VALUES(?,?,?,?,?,?,?,?)");
			statement=conn.createStatement();
			insertNewPatient=conn.prepareStatement("INSERT INTO patient"+"(First_Name,Last_Name,Patient_Id,Date,Gender,DOB,Cadre,Residence,Phone_Num,Next_Kin,Relationship,Kin_Contacts)"+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			insertMed=conn.prepareStatement("INSERT INTO medicine_reg"+"(med_id,med_name,med_type,date)"+"VALUES(?,?,?,?)");
			insertmedStock=conn.prepareStatement("INSERT INTO medicinestock"+"(medId,medName,medType,Date,Stock,unitPrice,TotalPrice,ManfgDate,expiryDate)"+"VALUES(?,?,?,?,?,?,?,?,?)");
			insertPatientCondition=conn.prepareStatement("INSERT INTO patient_conditions"+"(patient_id,patient_name,Age,weight,height,temperature,blood_type,blood_presure,blood_sugar,Date)"+"VALUES(?,?,?,?,?,?,?,?,?,?)");
			insertDocRoom=conn.prepareStatement("INSERT INTO doctors"+"(doctors_Id,doctors_Name,roomNo)"+"VALUES(?,?,?)");
			bookDoc=conn.prepareStatement("INSERT INTO appointments"+"(Nurse_Name,Patient_Id,Patient_Name,Doctors_Name,Room_No,Status)"+"VALUES(?,?,?,?,?,?)");
			patientTreatment=conn.prepareStatement("INSERT INTO patienttreatment"+"(PatientId,PatientName,Complaints,Observation,Diagnosis,Prescription,DoctorName,DOT)"+"VALUES(?,?,?,?,?,?,?,?)");			
			labRequest=conn.prepareStatement("INSERT INTO labrequest"+"(PatientId,BriefHx,ProvisionalHx,Specimen,Date,DoctorsName,Status)"+"VALUES(?,?,?,?,?,?,?)");
			labResults=conn.prepareStatement("INSERT INTO labresults"+"(PatientId,Specimen,Results,TechName,Date)"+"VALUES(?,?,?,?,?)");
			issueMed=conn.prepareStatement("INSERT INTO medissue"+"(PatientId,PatientName,Medicine,Date,IssueBy)"+"VALUES(?,?,?,?,?)");
			iguPatient=conn.prepareStatement("INSERT INTO igu_patients"+"(Patient_Id,Patient_Name,MedicineName,Medicine_Type,Medicine_Id,Quantity,Unit_Price,Amount,Date)"+"VALUES(?,?,?,?,?,?,?,?,?)");
			universityMember=conn.prepareStatement("INSERT INTO university_member"+"(PatientId,PatientName,MedicineName,MedicineId,MedicineType,Quantity,Da"
					+ "te)"+"VALUES(?,?,?,?,?,?,?)");
		}
		catch(SQLException error)
		{
			JOptionPane.showMessageDialog(null,error.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
			System.exit(1);


		}
	}
	public int insertEmployee(String eid, String fname,String lname,String gender,String job,String mstatus,String mail,String pass)
	{
		int result=0;
		try 
		{
			insertNewEmployee.setString(1,eid);
			insertNewEmployee.setString(2,fname);
			insertNewEmployee.setString(3,lname);
			insertNewEmployee.setString(4,gender);
			insertNewEmployee.setString(5,job);
			insertNewEmployee.setString(6,mstatus);
			insertNewEmployee.setString(7,mail);
			insertNewEmployee.setString(8,pass);
			
			result=insertNewEmployee.executeUpdate();	
		}
		catch(SQLException e1)
		{
			e1.getMessage();
		
			
		}
		return result;
		}
	public int logIn(String user,String jobCategory,String passWord)
	{
		int count=0;
		String job="";
		
		try
		{
			String sql="SELECT Employee_Id,Job_Category,Password FROM employees WHERE Employee_Id='"+user+"' AND Job_Category='"+jobCategory+"' AND Password='"+passWord+"'";
			result=statement.executeQuery(sql);
			metaData=result.getMetaData();
			
			while(result.next())
			{
				count=count+1;
			}
			if(count==1)
			{
				JOptionPane.showMessageDialog(null,"successfully logged in");
				String sql1="SELECT Job_Category FROM employees WHERE Job_Category='"+jobCategory+"'";
				result=statement.executeQuery(sql1);
				while(result.next())
				{
					job=String.format("%s",result.getObject(count));
					
					
					switch(job)
					{
					case "Receptionist":
						ReceptionSwitchBoard patient=new ReceptionSwitchBoard();
						patient.frame.setVisible(true);
						patient.frame.setLocationRelativeTo(null);
						break;
					case "Doctor":
						DoctorSwitchBoard doctor=new DoctorSwitchBoard(user);
						doctor.frame.setVisible(true);
						doctor.frame.setLocationRelativeTo(null);
						break;
					case "Pharmacist":
						PharmacySwitchBoard pharmacy=new PharmacySwitchBoard(user);
						pharmacy.frame.setVisible(true);
						pharmacy.frame.setLocationRelativeTo(null);
						break;
					case "Lab_Tech":
						Lab_TechSwitchBoard lab=new Lab_TechSwitchBoard(user);
						lab.frame.setVisible(true);
						lab.frame.setLocationRelativeTo(null);
						
						break;
						
					case "Nurse":
						NurseSwitchBoard nurse=new NurseSwitchBoard(user);
						nurse.frame.setVisible(true);
						nurse.frame.setLocationRelativeTo(null);
						break;
					case "Admni":
						AdmniSwitchBoard admniSwitch=new AdmniSwitchBoard();
						admniSwitch.frame.setVisible(true);
						admniSwitch.frame.setLocationRelativeTo(null);
						
						break;
					
						
					}
 
					

				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"please check your password or user Id");
			}
			
			
		}
		catch(SQLException sqlEx)
		{
			JOptionPane.showMessageDialog(null,sqlEx.getMessage());
		}
		return count;
		
	}
	
	public int insertPatient(String fname,String lname,String pid,String Rdate,String gender,String DOB,String cadre,String residence,String phone,String nok,String relation,String kinCon )
	{
		int result=0;
				
				try
		       {
					insertNewPatient.setString(1,fname);
					insertNewPatient.setString(2,lname);
					insertNewPatient.setString(3,pid);
					insertNewPatient.setString(4,Rdate);
					insertNewPatient.setString(5,gender);
					insertNewPatient.setString(6,DOB);
					insertNewPatient.setString(7,cadre);
					insertNewPatient.setString(8,residence);
					insertNewPatient.setString(9,phone);
					insertNewPatient.setString(10,nok);
					insertNewPatient.setString(11,relation);
					insertNewPatient.setString(12,kinCon);
					
					result=insertNewPatient.executeUpdate();
					
		       }
		   catch(SQLException sql)
		   {
			   JOptionPane.showMessageDialog(null,sql.getMessage());
		   }
		return result;
		
		}
	public int medInsert(String medid,String medname,String medtype,String medregdate)
	{
		int result=0;
		
		try
		{
			insertMed.setString(1,medid);
			insertMed.setString(2,medname);
			insertMed.setString(3,medtype);
			insertMed.setString(4,medregdate);
			
			result=insertMed.executeUpdate();
			
			if(result==1)
			{
				JOptionPane.showMessageDialog(null,"success");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"error");
			}
			
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql.getMessage());
		}
		return result;
	}
	public int medStock(String medId,String medname,String medtype,String date,int medstock,int unit,int total,String manfgdate,String expirydte)
	{
		int result=0;
		
		try
		{
			insertmedStock.setString(1,medId);
			insertmedStock.setString(2,medname);
			insertmedStock.setString(3,medtype);
			insertmedStock.setString(4,date);
			insertmedStock.setInt(5,medstock);
			insertmedStock.setInt(6,unit);
			insertmedStock.setInt(7,total);
			insertmedStock.setString(8,manfgdate);
			insertmedStock.setString(9,expirydte);
			
			result=insertmedStock.executeUpdate();
			
			if(result==1)
			{
				JOptionPane.showMessageDialog(null,"success");
				
			}
			else
			{
				JOptionPane.showMessageDialog(null,"error");
				
			}
			
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		return result;
	}
	public int patientCondition(String pid,String Pname,int page,int weight,int height,int temp,String blood_type,String blood_presure,String blood_sugar,String date)
	{
		int result=0;
			try
			{
			insertPatientCondition.setString(1,pid);
			insertPatientCondition.setString(2,Pname);
			insertPatientCondition.setInt(3,page);
			insertPatientCondition.setInt(4,weight);
			insertPatientCondition.setInt(5,height);
			insertPatientCondition.setInt(6,temp);
			insertPatientCondition.setString(7,blood_type);
			insertPatientCondition.setString(8,blood_presure);
			insertPatientCondition.setString(9,blood_sugar);
			insertPatientCondition.setString(10,date);
			
			result=insertPatientCondition.executeUpdate();
			if(result==1)
			{
				JOptionPane.showMessageDialog(null,Pname +" 's recorded successfully");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"An error has occurered please refill again","error",JOptionPane.ERROR_MESSAGE);
			}
			}
			catch(SQLException sql)
			{
				JOptionPane.showMessageDialog(null,sql);
			}
			return result;
	}
	public int docRoom(String docId,String docName,String docroomNo)
	{
		int result=0;
		
		try
		{
			insertDocRoom.setString(1,docId);
			insertDocRoom.setString(2,docName);
			insertDocRoom.setString(3,docroomNo);
			
			 result=insertDocRoom.executeUpdate();
			 if(result==1)
			 {
				 JOptionPane.showMessageDialog(null,"Dr."+docName+" has been allocated room no "+docroomNo,"success",JOptionPane.INFORMATION_MESSAGE);
			 }
			 else
			 {
				 JOptionPane.showMessageDialog(null,"error please refill the fields","error",JOptionPane.ERROR_MESSAGE);
			 }
			
			
		}
		catch(SQLException sql2)
		{
			JOptionPane.showMessageDialog(null,sql2);
			
		}
		return result;
	}
	public void bookDoctor(String nurseName,String patientId,String patName,String DocName,String room,String Bstatus )
	{
		int result=0;
		
		try
		{
			bookDoc.setString(1,nurseName);
			bookDoc.setString(2,patientId);
			bookDoc.setString(3,patName);
			bookDoc.setString(4,DocName);
			bookDoc.setString(5,room);
			bookDoc.setString(6,Bstatus);
			
			result=bookDoc.executeUpdate();
			
			if(result==1)
			{
				JOptionPane.showMessageDialog(null,patName +" to go to Dr."+DocName+ " in "+room,"Booking successfull",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"error Booking please try again");
			}
			
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);
		}
		
	}
	public int treatpatient(String patId,String patName,String complaints,String observe,String diagnosis,String prescribe,String Dname,String DOT)
	{
		int result=0;
		String status="Seen";
		try
		{
			patientTreatment.setString(1,patId);
			patientTreatment.setString(2,patName);
			patientTreatment.setString(3,complaints);
			patientTreatment.setString(4,observe);
			patientTreatment.setString(5,diagnosis);
			patientTreatment.setString(6,prescribe);
			patientTreatment.setString(7,Dname);
			patientTreatment.setString(8,DOT);
			
			result=patientTreatment.executeUpdate();
			
			if(result==1)
			{
				JOptionPane.showMessageDialog(null,patName +"sent to pharmacy","success",JOptionPane.INFORMATION_MESSAGE);
				appointmentUpdate=conn.prepareStatement("UPDATE appointments SET Status='"+status+"' WHERE Patient_Id='"+patId+"'");
				appointmentUpdate.executeUpdate();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"error");
				
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex);		
		}
		return result;
	}
	public void requestLab(String Pid,String BrfHx,String provHx,String Specimen,String labDate,String doctName,String status)
	{
		int result=0;
		try
		{
			labRequest.setString(1,Pid);
			labRequest.setString(2,BrfHx);
			labRequest.setString(3,provHx);
			labRequest.setString(4,Specimen);
			labRequest.setString(5,labDate);
			labRequest.setString(6,doctName);
			labRequest.setString(7,status);
			
			result=labRequest.executeUpdate();
			
			if(result==1)
			{
				JOptionPane.showMessageDialog(null,"request successfull","error",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"error");
			}
		}
		catch(SQLException sql2)
		{
			JOptionPane.showMessageDialog(null,sql2);	
		}
	}
	public void LabResults(String pId,String Spec,String results,String techName,String testDate)
	{
		int result=0;
		String status="Approved";
		try
		{
			labResults.setString(1,pId);
			labResults.setString(2,Spec);
			labResults.setString(3,results);
			labResults.setString(4,techName);
			labResults.setString(5,testDate);
			
			result=labResults.executeUpdate();
			if(result==1)
			{
				JOptionPane.showMessageDialog(null,"Results sent to the Doctor");
				requestUpdate=conn.prepareStatement("UPDATE labrequest SET Status='"+status+"' WHERE PatientId='"+pId+"'");
				requestUpdate.executeUpdate();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"ERROR");
			}
			
		}
		catch(SQLException sql3)
		{
			JOptionPane.showMessageDialog(null,sql3);
		}
		
	}
	public void issueMedicine(String pid,String pName,String med,String date,String pharm)
	{
		int result=0;
		try
		{
			issueMed.setString(1,pid);
			issueMed.setString(2,pName);
			issueMed.setString(3,med);
			issueMed.setString(4,date);
			issueMed.setString(5,pharm);
			
			result=issueMed.executeUpdate();
			
			if(result==1)
			{
				JOptionPane.showMessageDialog(null,"Medicine issued successfully","Success",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"error","Please try again",JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(null,sql);
		}
	}
  public int iguPatient(String patId,String patName,String medName,String medTyp,String medId,int quant,int unit,int amount,String date)
  {
	  int results=0;
	  try
	  {
		iguPatient.setString(1,patId);  
		iguPatient.setString(2,patName);  
		iguPatient.setString(3,medName);  
		iguPatient.setString(4,medTyp);  
		iguPatient.setString(5,medId);  
		iguPatient.setInt(6,quant);
		iguPatient.setInt(7,unit);  
		iguPatient.setInt(8,amount); 
		iguPatient.setString(9,date);
		
		results=iguPatient.executeUpdate();
		if(results==1)
		{
			JOptionPane.showMessageDialog(null,"Successfully Saved");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Error");
		}
	  }
	  catch(SQLException ex3)
	  {
		  JOptionPane.showMessageDialog(null,ex3);
	  }
	  return results;
  }
  public void uniMember(String patid,String pname,String medname,String mednum,String medtype,int quan,String date)
  {
	 int results=0; 
	 try
	 {
		 universityMember.setString(1,patid);
		 universityMember.setString(2,pname);
		 universityMember.setString(3,medname);
		 universityMember.setString(4,mednum);
		 universityMember.setString(5,medtype);
		 universityMember.setInt(6,quan);
		 universityMember.setString(7,date);
		 
		 results=universityMember.executeUpdate();
		 if(results==1)
			{
				JOptionPane.showMessageDialog(null,"Successfully Saved");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Error");
			}
	 }
	 catch(SQLException sql)
	 {
		 JOptionPane.showMessageDialog(null,sql);
	 }
  }
  
	
}
	

