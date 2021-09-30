package com.revature.bloodbank.dao;
import com.revature.bloodbank.exception.BloodBankDuplicateCenterIdException;
import com.revature.bloodbank.exception.BloodBankInvalidDetailsException;
import com.revature.bloodbank.model.BloodBankCenter;

public interface BloodBankDAO {
	public void addBloodBankCenter(BloodBankCenter bloodBankCenter) throws BloodBankDuplicateCenterIdException;
	public void deleteBloodBankCenterByCenterId(BloodBankCenter bloodBankCenter) throws BloodBankInvalidDetailsException;
	public void updateBloodBankCenterById(BloodBankCenter bloodBankCenter) throws BloodBankInvalidDetailsException;
	public void getAllBloodBankCenters() throws Exception;
	public void getBloodBankCenterByCenterName(BloodBankCenter bloodBankCenter) throws Exception;
	public void getBloodBankCenterByStreet(BloodBankCenter bloodBankCenter) throws Exception;
	public void getBloodBankCenterByPincode(BloodBankCenter bloodBankCenter) throws Exception;
	public void getBloodBankCenterByState(BloodBankCenter bloodBankCenter) throws Exception;
	public void getBloodBankCenterByCity(BloodBankCenter bloodBankCenter) throws Exception;
	
}
