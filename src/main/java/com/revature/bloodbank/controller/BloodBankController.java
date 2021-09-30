package com.revature.bloodbank.controller;
import com.revature.bloodbank.exception.BloodBankDuplicateCenterIdException;
import com.revature.bloodbank.exception.BloodBankInvalidDetailsException;
import com.revature.bloodbank.model.BloodBankCenter;
import com.revature.bloodbank.service.BloodBankService;
import com.revature.bloodbank.service.BloodBankServiceImpl;
import org.apache.log4j.Logger;

public class BloodBankController {
	
	BloodBankService bloodBankServiceImpl=new BloodBankServiceImpl();
	static final Logger logger=Logger.getLogger("BloodBankController.class");
	 public  void addBloodBankCenter(BloodBankCenter bloodBankCenter ) throws BloodBankDuplicateCenterIdException {
		   
		  try {
			bloodBankServiceImpl.addBloodBankCenter(bloodBankCenter);
			logger.info("inside BloodBankController class,addBloodBankCenter method is executed");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	   }
	 
		public void deleteBloodBankCenterByCenterId(BloodBankCenter bloodBankCenter) throws BloodBankInvalidDetailsException {
			
			  try {
				  bloodBankServiceImpl.deleteBloodBankCenterByCenterId(bloodBankCenter);
				  logger.info("inside BloodBankController class,deleteBloodBankCenterByCenterId method is executed");
				} catch (Exception e) {
					logger.warn(e.getMessage());
				}
		}
		
		public void updateBloodBankCenterById(BloodBankCenter bloodBankCenter) throws BloodBankInvalidDetailsException{
			try {
				bloodBankServiceImpl.updateBloodBankCenterById(bloodBankCenter);
				logger.info("inside BloodBankController class,updateBloodBankCenterById method is executed");
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
		}
		
		public void getAllBloodBankCenters() throws Exception{
			try {
				bloodBankServiceImpl.getAllBloodBankCenters();
				logger.info("inside BloodBankController class,getAllBloodBankCenters method is executed");
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
		}
		
		public void getBloodBankCenterByCenterName(BloodBankCenter bloodBankCenter) throws Exception{
		try {
			bloodBankServiceImpl.getBloodBankCenterByCenterName(bloodBankCenter);
			logger.info("inside BloodBankController class,getBloodBankCenterByCenterName method is executed");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
		
		public void getBloodBankCenterByStreet(BloodBankCenter bloodBankCenter) throws Exception{
			try {
				bloodBankServiceImpl.getBloodBankCenterByStreet(bloodBankCenter);
				logger.info("inside BloodBankController class,getBloodBankCenterByStreet method is executed");
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
		}
		
		public void getBloodBankCenterByPincode(BloodBankCenter bloodBankCenter) throws Exception{
			try {
				bloodBankServiceImpl.getBloodBankCenterByPincode(bloodBankCenter);
				logger.info("inside BloodBankController class,getBloodBankCenterByPincode method is executed");
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
		}
		public void getBloodBankCenterByState(BloodBankCenter bloodBankCenter) throws Exception{
			try {
				bloodBankServiceImpl. getBloodBankCenterByState(bloodBankCenter);
				logger.info("inside BloodBankController class,getBloodBankCenterByState method is executed");
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
		}
		public void getBloodBankCenterByCity(BloodBankCenter bloodBankCenter) throws Exception{
			try {
				bloodBankServiceImpl.getBloodBankCenterByCity(bloodBankCenter);
				logger.info("inside BloodBankController class,getBloodBankCenterByCity method is executed");
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
		}
	

}
