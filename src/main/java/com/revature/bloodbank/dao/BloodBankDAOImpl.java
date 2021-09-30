package com.revature.bloodbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.revature.bloodbank.exception.BloodBankInvalidDetailsException;
import com.revature.bloodbank.exception.BloodBankNotFoundException;
import com.revature.bloodbank.exception.BloodBankDuplicateCenterIdException;
import com.revature.bloodbank.model.BloodBankCenter;
import com.revature.bloodbank.util.DBUtil;


public class BloodBankDAOImpl  implements BloodBankDAO{

	static final Logger logger=Logger.getLogger("BloodBankDAOImpl.class");
	
	
	//insert block starts here-----------------------------------------------------------------------
	public void addBloodBankCenter(BloodBankCenter bloodBankCenter) throws BloodBankDuplicateCenterIdException{
			try {
			addBloodBankCenterImp(bloodBankCenter);
			}	catch(Exception exceptionObject) {
				System.out.println(exceptionObject.getMessage());
				logger.warn(exceptionObject.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
			}
	
	}
	private void addBloodBankCenterImp(BloodBankCenter bloodBankCenter) throws Exception{
		
		try {
		Connection con=DBUtil.getConnection();
		//if the given center id already exists, throw duplicate center id exception,or else add to the database
		PreparedStatement pst=con.prepareStatement("select * from bloodbankcenter where centerid=?");
		pst.setInt(1,bloodBankCenter.getCenterId());
		ResultSet rst=pst.executeQuery();
		if(rst.next()) {
			throw new BloodBankDuplicateCenterIdException("Sorry...Entered center id already exists.");
		}
		else {
			//adding an entry
		 pst=con.prepareStatement("insert into BloodBankCenter values(?,?,?,?,?,?)");
		pst.setInt(1,bloodBankCenter.getCenterId());
		pst.setString(2,bloodBankCenter.getCenterName());
		pst.setString(3,bloodBankCenter.getStreet());
		pst.setString(4,bloodBankCenter.getCity());
		pst.setString(5,bloodBankCenter.getState());
		pst.setString(6,bloodBankCenter.getPincode());
		int rowsChanged=pst.executeUpdate();
		System.out.println("Adding details for center id"+bloodBankCenter.getCenterId()+" is completed..");
		logger.info("Inside addBloodBankCenterImp method "+rowsChanged+"'s affected/inserted for center id "+bloodBankCenter.getCenterId());
		}
	}catch(BloodBankDuplicateCenterIdException exceptionObject) {
		System.out.println(exceptionObject.getMessage());
		logger.warn(exceptionObject.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
	}
		catch(Exception exceptionObject) {
			System.out.println(exceptionObject.getMessage());
			logger.warn(exceptionObject.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
		}
}
	
	
	//insert block ends here---------------------------------------------------------------------------	
		
	//delete block starts here-----------------------------------------------------------------------------	
		
	public void deleteBloodBankCenterByCenterId(BloodBankCenter bloodBankCenter) throws BloodBankInvalidDetailsException {
		deleteBloodBankCenterByCenterIdImp(bloodBankCenter);
	}
	
	 private void deleteBloodBankCenterByCenterIdImp(BloodBankCenter bloodBankCenter) {
		 try {
			 	Connection con=DBUtil.getConnection();
			 	//check whether given center exists, so that we can delete
				PreparedStatement pst=con.prepareStatement("select * from BloodBankCenter where centerid=?");
				pst.setInt(1,bloodBankCenter.getCenterId());
				ResultSet res=pst.executeQuery();
				 if(res.next()) {
					//deleting a row for the given center id
				  pst=con.prepareStatement("delete from BloodBankCenter where centerId=?");
				 pst.setInt(1,bloodBankCenter.getCenterId());
				 pst.execute();
				 System.out.println("deletion is completed for center id "+bloodBankCenter.getCenterId());
				 logger.info("inside deleteBloodBankCenterByCenterId, deletion is completed for center id "+bloodBankCenter.getCenterId());
				  }
				 else {
						throw new BloodBankInvalidDetailsException("Couldn't delete... Entered center id doesn't exist...");
						}
			}catch(BloodBankInvalidDetailsException ex) {
					 System.out.println(ex.getMessage());
					 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
		}
			 
		 catch(Exception e) {
			 e.getMessage();
			 logger.warn(e.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
		 }
	 
		 }
	 //delete block ends here-------------------------------------------------------------------------------------
	 
	//update block starts here  ------------------------------------------------------------------------------------------------------------------
	  public void updateBloodBankCenterById(BloodBankCenter bloodBankCenter) throws BloodBankInvalidDetailsException{
		try {
			updateBloodBankCenterByIdImp(bloodBankCenter);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void updateBloodBankCenterByIdImp(BloodBankCenter bloodBankCenter) throws Exception{
		Connection con=DBUtil.getConnection();
		//check whether the given center id already exists, so that we can update details or else throw exception
		PreparedStatement pst=con.prepareStatement("select * from BloodBankCenter where centerid=?");
		pst.setInt(1,bloodBankCenter.getCenterId());
		ResultSet res=pst.executeQuery();
		 if(res.next()) { 
			 	//check whichever field is not empty and accordingly update that value
				if((bloodBankCenter.getCenterName().equals(""))==false){
					 pst=con.prepareStatement("update BloodBankCenter set centerName=? where centerid=?");
					pst.setString(1,bloodBankCenter.getCenterName());
					pst.setInt(2,bloodBankCenter.getCenterId());
					pst.executeUpdate();
				}
				if((bloodBankCenter.getStreet().equals(""))==false){
					 pst=con.prepareStatement("update BloodBankCenter set street=? where centerid=?");
					pst.setString(1,bloodBankCenter.getStreet());
					pst.setInt(2,bloodBankCenter.getCenterId());
					pst.executeUpdate();
				}
			
				 if((bloodBankCenter.getCity().equals(""))==false){
					 pst=con.prepareStatement("update BloodBankCenter set city=? where centerid=?");
					pst.setString(1,bloodBankCenter.getCity());
					pst.setInt(2,bloodBankCenter.getCenterId());
					pst.executeUpdate();
				}
			
				 if((bloodBankCenter.getState().equals(""))==false){
					pst=con.prepareStatement("update BloodBankCenter set state=? where centerid=?");
					pst.setString(1,bloodBankCenter.getState());
					pst.setInt(2,bloodBankCenter.getCenterId());
					pst.executeUpdate();
				}
				 if((bloodBankCenter.getPincode().equals(""))==false){
					pst=con.prepareStatement("update BloodBankCenter set pincode=? where centerid=?");
					pst.setString(1,bloodBankCenter.getPincode());
					pst.setInt(2,bloodBankCenter.getCenterId());
					pst.executeUpdate();
				}  
				System.out.println("Updating blood bank center details is completed..");
				logger.info("inside updateBloodBankCenter,update is completed");
			
			}
	 		else {
	 			try {
	 				throw new BloodBankInvalidDetailsException("Can't update...Entered center id doesn't exist...");
	 		}catch(BloodBankInvalidDetailsException ex) {
	 				System.out.println(ex.getMessage());
	 				 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
	 		}
				
				
		}
	}

	//update block ends here -------------------------------------------------------------------------------------------------------------------------------------
	 
	//Read operations starts here------------------------------------------------------------------------------------------------
	
	//Read by city starts------------------------------------------------------------------------------------------------------------------
	

	public void getBloodBankCenterByCity(BloodBankCenter bloodBankCenter) throws Exception{
		List<BloodBankCenter> resultList=getBloodBankCenterByCityImp(bloodBankCenter);
		try {
		if(resultList.size()>0) {
				resultList.stream().forEach(p->{	
							System.out.println("Center ID: "+p.getCenterId());
							System.out.println("Center name: "+p.getCenterName());
							System.out.println("Center street: "+p.getStreet());
							System.out.println("Center city: "+p.getCity());
							System.out.println("Center state: "+p.getState());
							System.out.println("Center pincode: "+p.getPincode());
							System.out.println("**************************************************");
			});
		}
		logger.info("inside getBloodBankCenterbyCity, results are printed on the screen");
		}catch(NullPointerException ex) {
			System.out.println("");
			 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
		}

	}


	private List<BloodBankCenter> getBloodBankCenterByCityImp(BloodBankCenter bloodBankCenter) throws Exception{
		
		Connection con=DBUtil.getConnection();
		//check whether given city is present in the table, if it exists add to the list as a object of BloodBankCenter type
		PreparedStatement pst=con.prepareStatement("select * from BloodBankCenter where city=?");
		pst.setString(1,bloodBankCenter.getCity());
		ResultSet rst=pst.executeQuery();
		boolean checkVariable=false;//for checking purpose, if checkVariable turns true, it means it entered inside rst.next() while loop, so at least an entry is there for given city
		List<BloodBankCenter> resultList=new ArrayList<BloodBankCenter>();
						while(rst.next()){
							checkVariable=true;
							resultList.add(new BloodBankCenter(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
							}
						
					if(checkVariable==false) {
						try {
							throw new BloodBankNotFoundException("Hmm...Entered city deoesn't exist in our database...");
						}catch(BloodBankNotFoundException ex) {
							System.out.println(ex.getMessage());
							 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
						}
					}
		return resultList;
	}

	//Read by city ends----------------------------------------------------------------------------------------------------------------
	
	//Read by state starts----------------------------------------------------------------------------------------------------------------

	

	public void getBloodBankCenterByState(BloodBankCenter bloodBankCenter) throws Exception{
		List<BloodBankCenter> resultList=getBloodBankCenterByStateImp(bloodBankCenter);
		try {
		if(resultList.size()>0) {
			resultList.stream().forEach(p->{	
						System.out.println("Center ID: "+p.getCenterId());
						System.out.println("Center name: "+p.getCenterName());
						System.out.println("Center street: "+p.getStreet());
						System.out.println("Center city: "+p.getCity());
						System.out.println("Center state: "+p.getState());
						System.out.println("Center pincode: "+p.getPincode());
						System.out.println("**************************************************");
		});
		}
		logger.info("inside getBloodBankCenterbyState, results are printed on the screen");
		}catch(NullPointerException ex) {
			System.out.println("");
			 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
		}
	}
	private List<BloodBankCenter> getBloodBankCenterByStateImp(BloodBankCenter bloodBankCenter) throws Exception{
		Connection con=DBUtil.getConnection();
		//check whether given state is present in the table, if it exists add to the list as a object of BloodBankCenter type
		PreparedStatement pst=con.prepareStatement("select * from BloodBankCenter where state=?");
		pst.setString(1,bloodBankCenter.getState());
		ResultSet rst=pst.executeQuery();
		List<BloodBankCenter> resultList=null;
		if(rst.next()) {
						rst=pst.executeQuery();
						resultList=new ArrayList<BloodBankCenter>();
						while(rst.next()){
							
							resultList.add(new BloodBankCenter(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
							}
						
			}else {
				try {
					throw new BloodBankNotFoundException("Hmm...Entered state deoesn't exist in our database...");
				}catch(BloodBankNotFoundException ex) {
					System.out.println(ex.getMessage());
					 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
				}
			}
		return resultList;
	}
	//Read by state ends----------------------------------------------------------------------------------------------------
	
	//Read by pincode starts-------------------------------------------------------------------------------------------------------------------
	

	public void getBloodBankCenterByPincode(BloodBankCenter bloodBankCenter) throws Exception{
		List<BloodBankCenter> resultList=getBloodBankCenterByPincodeImp(bloodBankCenter);
		try {
		if(resultList.size()>0) {
			resultList.stream().forEach(p->{	
				System.out.println("Center ID: "+p.getCenterId());
				System.out.println("Center name: "+p.getCenterName());
				System.out.println("Center street: "+p.getStreet());
				System.out.println("Center city: "+p.getCity());
				System.out.println("Center state: "+p.getState());
				System.out.println("Center pincode: "+p.getPincode());
		System.out.println("**************************************************");
		});
		}
		logger.info("inside getBloodBankCenterbyPincode, results are printed on the screen");
		}catch(NullPointerException ex) {
			System.out.println("");
			 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
		}
	}
	private List<BloodBankCenter> getBloodBankCenterByPincodeImp(BloodBankCenter bloodBankCenter) throws Exception{
		
		Connection con=DBUtil.getConnection();
		//check whether given pincode is present in the table, if it exists add to the list as a object of BloodBankCenter type
		PreparedStatement pst=con.prepareStatement("select * from BloodBankCenter where pincode=?");
		pst.setString(1,bloodBankCenter.getPincode());
		ResultSet rst=pst.executeQuery();
		List<BloodBankCenter> resultList=null;
		if(rst.next()) {
						rst=pst.executeQuery();
						resultList=new ArrayList<BloodBankCenter>();
						while(rst.next()){
							
							resultList.add(new BloodBankCenter(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
							}
						
			}else {
				try {
					throw new BloodBankNotFoundException("Hmm...Entered pincode doesn't exist in our database...");
				}catch(BloodBankNotFoundException ex) {
					System.out.println(ex.getMessage());
					 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
				}
			}
		return resultList;
	}

	//Read by pincode ends----------------------------------------------------------------------------------------------------
	
	//Read by street starts-----------------------------------------------------------------------------------------------------------------------------------------------
	

	public void getBloodBankCenterByStreet(BloodBankCenter bloodBankCenter) throws Exception{
		List<BloodBankCenter> resultList=getBloodBankCenterByStreetImp(bloodBankCenter);
		try {
		if(resultList.size()>0) {
			resultList.stream().forEach(p->{	
						System.out.println("Center ID: "+p.getCenterId());
				System.out.println("Center name: "+p.getCenterName());
				System.out.println("Center street: "+p.getStreet());
				System.out.println("Center city: "+p.getCity());
				System.out.println("Center state: "+p.getState());
				System.out.println("Center pincode: "+p.getPincode());
		System.out.println("**************************************************");
		});
		}
		logger.info("inside getBloodBankCenterbyStreet, results are printed on the screen");
		}catch(NullPointerException ex) {
			System.out.println("");
			 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
		}
	}
	private List<BloodBankCenter> getBloodBankCenterByStreetImp(BloodBankCenter bloodBankCenter) throws Exception{
		Connection con=DBUtil.getConnection();
		//check whether given street is present in the table, if it exists add to the list as a object of BloodBankCenter type
		PreparedStatement pst=con.prepareStatement("select * from BloodBankCenter where street=?");
		pst.setString(1,bloodBankCenter.getStreet());
		ResultSet rst=pst.executeQuery();
		List<BloodBankCenter> resultList=null;
		if(rst.next()) {
						rst=pst.executeQuery();
						resultList=new ArrayList<BloodBankCenter>();
						while(rst.next()){
							
							resultList.add(new BloodBankCenter(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
							}
						
			}else {
				try {
					throw new BloodBankNotFoundException("Hmm...Entered street doesn't exist in our database...");
				}catch(BloodBankNotFoundException ex) {
					System.out.println(ex.getMessage());
					 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
				}
			}
		return resultList;
	}

	//Read by street ends--------------------------------------------------------------------------------------------------
	
	//Read by center name starts------------------------------------------------------------------------------------------------------------------------------------------
	

	public void getBloodBankCenterByCenterName(BloodBankCenter bloodBankCenter) throws Exception{
		List<BloodBankCenter> resultList=getBloodBankCenterByCenterNameImp(bloodBankCenter);
		try {
			if(resultList.size()>0) {
			resultList.stream().forEach(p->{	
						System.out.println("Center ID: "+p.getCenterId());
				System.out.println("Center name: "+p.getCenterName());
				System.out.println("Center street: "+p.getStreet());
				System.out.println("Center city: "+p.getCity());
				System.out.println("Center state: "+p.getState());
				System.out.println("Center pincode: "+p.getPincode());
		System.out.println("**************************************************");
		});
		}
			logger.info("inside getBloodBankCenterbyCentername, results are printed on the screen");
		}catch(NullPointerException ex) {
			System.out.println("");
			 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
		}
	}
	private List<BloodBankCenter> getBloodBankCenterByCenterNameImp(BloodBankCenter bloodBankCenter) throws Exception{
		Connection con=DBUtil.getConnection();
		//check whether given center name is present in the table, if it exists add to the list as a object of BloodBankCenter type
		PreparedStatement pst=con.prepareStatement("select * from BloodBankCenter where centerName=?");
		pst.setString(1,bloodBankCenter.getCenterName());
		ResultSet rst=pst.executeQuery();
		List<BloodBankCenter> resultList=null;
		if(rst.next()) {
						rst=pst.executeQuery();
						resultList=new ArrayList<BloodBankCenter>();
						while(rst.next()){
							resultList.add(new BloodBankCenter(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
							}
						
			}else {
				try {
					throw new BloodBankNotFoundException("Hmm...Entered center name doesn't exist in our database...");
				}catch(BloodBankNotFoundException ex) {
					System.out.println(ex.getMessage());
					 logger.warn(ex.getMessage()+"error for center id "+bloodBankCenter.getCenterId());
					
				}
			}
		return resultList;
	}

	//Read by center name ends---------------------------------------------------------------------------------------------------
	
	//Read all centers------------------------------------------------------------------------------------------------------------------------------------

	

	public void getAllBloodBankCenters() throws Exception{
		List<BloodBankCenter> resultList=getAllBloodBankCentersImp();
		try {
		if(resultList.size()>0) {
			resultList.stream().forEach(p->{	
						System.out.println("Center ID: "+p.getCenterId());
				System.out.println("Center name: "+p.getCenterName());
				System.out.println("Center street: "+p.getStreet());
				System.out.println("Center city: "+p.getCity());
				System.out.println("Center state: "+p.getState());
				System.out.println("Center pincode: "+p.getPincode());
				System.out.println("**************************************************");
				
				});
		}
		logger.info("inside getall method, results are printed on the screen successfully..");
		}catch(NullPointerException ex) {
			 logger.warn(ex.getMessage());
			System.out.println("");
		}
	}
	private List<BloodBankCenter> getAllBloodBankCentersImp() throws Exception{
		Connection con=DBUtil.getConnection();
		//check whether any one of entry is present in the table, if it exists add to the list as a object of BloodBankCenter type
		PreparedStatement pst=con.prepareStatement("select * from BloodBankCenter");
		ResultSet rst=pst.executeQuery();
		List<BloodBankCenter> resultList=null;
		if(rst.next()) {
						rst=pst.executeQuery();
						resultList=new ArrayList<BloodBankCenter>();
						while(rst.next()){
							
							resultList.add(new BloodBankCenter(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));	
						}
						
			}else {
				try {
					throw new BloodBankNotFoundException("Hmm...No record found in our database...");
				}catch(BloodBankNotFoundException ex) {
					System.out.println(ex.getMessage());
					logger.warn(ex.getMessage());
				}
			}
		return resultList;
	}
	//Read all centers ends----------------------------------------------------------------------------------------------
//Read operation ends here-----------------------------------------------------------------------------------------------
	

	
}
