package com.revature.bloodbank.service;
import com.revature.bloodbank.exception.BloodBankDuplicateCenterIdException;
import com.revature.bloodbank.exception.BloodBankInvalidDetailsException;
import com.revature.bloodbank.dao.BloodBankDAO;
import com.revature.bloodbank.dao.BloodBankDAOImpl;
import com.revature.bloodbank.model.BloodBankCenter;
import org.apache.log4j.Logger;

public class BloodBankServiceImpl implements BloodBankService{
	BloodBankDAO bloodBankDaoImpl=new BloodBankDAOImpl();
	static final Logger logger=Logger.getLogger("BloodBankServiceImpl.class");
	public void addBloodBankCenter(BloodBankCenter bloodBankCenter) throws BloodBankDuplicateCenterIdException {
		try {
			bloodBankDaoImpl.addBloodBankCenter(bloodBankCenter);
			logger.info("inside BloodBankCenterService class, add method is executed");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	
	public void deleteBloodBankCenterByCenterId(BloodBankCenter bloodBankCenter) throws BloodBankInvalidDetailsException {	
		try {
			bloodBankDaoImpl.deleteBloodBankCenterByCenterId(bloodBankCenter);
			logger.info("inside BloodBankCenterService class,delete method is executed");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	
	public void updateBloodBankCenterById(BloodBankCenter bloodBankCenter) throws BloodBankInvalidDetailsException{
		try {
			bloodBankDaoImpl.updateBloodBankCenterById(bloodBankCenter);
			logger.info("inside BloodBankCenterService class,update method is executed");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	
	public void getAllBloodBankCenters() throws Exception{
		try {
			bloodBankDaoImpl.getAllBloodBankCenters();
			logger.info("inside BloodBankCenterService class,getAllBloodBankCenters method is executed");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	
	public void getBloodBankCenterByCenterName(BloodBankCenter bloodBankCenter) throws Exception{
	try {
		bloodBankDaoImpl.getBloodBankCenterByCenterName(bloodBankCenter);
		logger.info("inside BloodBankCenterService class,getBloodBankCenterByCenterName method is executed");
	} catch (Exception e) {
		logger.warn(e.getMessage());
	}
}
	
	public void getBloodBankCenterByStreet(BloodBankCenter bloodBankCenter) throws Exception{
		try {
			bloodBankDaoImpl.getBloodBankCenterByStreet(bloodBankCenter);
			logger.info("inside BloodBankCenterService class,getBloodBankCenterByStreet method is executed");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	
	public void getBloodBankCenterByPincode(BloodBankCenter bloodBankCenter) throws Exception{
		try {
			bloodBankDaoImpl.getBloodBankCenterByPincode(bloodBankCenter);
			logger.info("inside BloodBankCenterService class,getBloodBankCenterByPincode method is executed");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	public void getBloodBankCenterByState(BloodBankCenter bloodBankCenter) throws Exception{
		try {
			bloodBankDaoImpl.getBloodBankCenterByState(bloodBankCenter);
			logger.info("inside BloodBankCenterService class,getBloodBankCenterByState method is executed");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	public void getBloodBankCenterByCity(BloodBankCenter bloodBankCenter) throws Exception{
		try {
			bloodBankDaoImpl.getBloodBankCenterByCity( bloodBankCenter);
			logger.info("inside BloodBankCenterService class,getBloodBankCenterByCity method is executed");
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

}

