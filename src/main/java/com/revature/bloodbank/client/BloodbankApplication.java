package com.revature.bloodbank.client;

import com.revature.bloodbank.exception.BloodBankDuplicateCenterIdException;
import java.util.Scanner;
import com.revature.bloodbank.controller.BloodBankController;
import com.revature.bloodbank.model.BloodBankCenter;
import com.revature.bloodbank.exception.BloodBankInvalidDetailsException;
import org.apache.log4j.Logger;

public class BloodbankApplication {
	static final Logger logger=Logger.getLogger("BloodBankApplication.class");
	public static void main(String[] args)  throws BloodBankDuplicateCenterIdException,BloodBankInvalidDetailsException,Exception{
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("====BloodBank Application===========");
		logger.info("inside BloodBankCenterApplication class, application started");
		BloodBankController bcontroller=new BloodBankController();
		BloodBankCenter bloodBankCenter=new  BloodBankCenter();
		
		System.out.println("'a' to add a blood bank center, 'b' to update a center, 'c' to delete a center, 'd' to read centers ");
		char entryChoice=sc.nextLine().charAt(0);
		switch(entryChoice) {
		
				case 'a':
					{
							System.out.println("Enter boold bank details");
							int centerId=sc.nextInt();sc.nextLine();
							String centerName=sc.nextLine();
							 String street=sc.nextLine();
							 String city=sc.nextLine();
							 String state=sc.nextLine();
							 String pincode=sc.nextLine();
							 bloodBankCenter.setCenterId(centerId);
							 bloodBankCenter.setCenterName(centerName);
							 bloodBankCenter.setCity(city);
							 bloodBankCenter.setStreet(street);
							 bloodBankCenter.setState(state);
							 bloodBankCenter.setPincode(pincode); 
							bcontroller.addBloodBankCenter(bloodBankCenter); 
							logger.info("inside BloodBankApplication class, add method is ended");
							 break;
					}
				case 'b':{
					
						System.out.println("Enter center id to update boold bank details");
						int centerId=sc.nextInt();sc.nextLine();
						bloodBankCenter.setCenterId(centerId);

						System.out.println("Enter whichever fields(in the order of center name,street,city,state,pincode) you want to update for the given center id, otherwise leave an entry and move next.. ");
						//if centername is to be updated
						String centerName=sc.nextLine();
						 bloodBankCenter.setCenterName(centerName);
			
						//if street is to be updated
						String	street=sc.nextLine();
						bloodBankCenter.setStreet(street);
						 
					
						//if city is to be updated
						String	city=sc.nextLine();
						bloodBankCenter.setCity(city);
					
						//if state is to be updated
						String state=sc.nextLine();
						bloodBankCenter.setState(state);
						
					
						//if pincode is to be updated
						String	pincode=sc.nextLine();
						bloodBankCenter.setPincode(pincode);
						  
						bcontroller.updateBloodBankCenterById(bloodBankCenter);
						logger.info("inside BloodBankApplication class,update method is ended");
						break;
				
				}
				case 'c':{
					
					 System.out.println("enter center id to delete boold bank details");
					 int deleteCenterId=sc.nextInt();sc.nextLine();
					 bloodBankCenter.setCenterId(deleteCenterId);
					 bcontroller.deleteBloodBankCenterByCenterId(bloodBankCenter);
					 logger.info("inside BloodBankApplication class, delete method is ended");
					 break;
					 
				}
		 
				case 'd':{
					 System.out.println("enter 'a' - get all centers, 'b'-by center name, 'c'-by street, 'd'-by pincode, 'e'-by state, 'f'-by city");  
					 char choice=sc.nextLine().charAt(0);
					 switch(choice) {
							 case 'a':{
								 bcontroller.getAllBloodBankCenters();break;
							 }
								
							 case'b':{
								 String getCenterName=sc.nextLine();
								 bloodBankCenter.setCenterName(getCenterName);
								 bcontroller.getBloodBankCenterByCenterName(bloodBankCenter);break;
							 }
							 case 'c':{
								 String getCenterStreet=sc.nextLine();
								 bloodBankCenter.setStreet(getCenterStreet);
								 bcontroller.getBloodBankCenterByStreet(bloodBankCenter);break;
							 }
							 case 'd':{
								 String getCenterPincode=sc.nextLine();
								 bloodBankCenter.setPincode(getCenterPincode);
								 bcontroller.getBloodBankCenterByPincode(bloodBankCenter);break;
							 }
							 case 'e':{
								 String getCenterState=sc.nextLine();
								 bloodBankCenter.setState(getCenterState);
								 bcontroller.getBloodBankCenterByState(bloodBankCenter);break;
							 }
							 case 'f':{
								 String getCenterCity=sc.nextLine();
								 bloodBankCenter.setCity(getCenterCity);
								  bcontroller.getBloodBankCenterByCity(bloodBankCenter);break;
							 }
							 default: System.out.println("Invalid option....");
						} 
					 		logger.info("inside BloodBankApplication class, read method is ended");
							 System.out.println("Read operation is completed");	
							 break;
						}
			default: System.out.println("Hmm...Entered a wrong key...");
		 
		}
		logger.info("inside BloodBankApplication class,application closed");
		 sc.close();
		
	
	}
	
}
