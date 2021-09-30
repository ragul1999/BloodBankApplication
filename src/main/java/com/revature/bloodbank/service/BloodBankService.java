package com.revature.bloodbank.service;
import com.revature.bloodbank.exception.BloodBankDuplicateCenterIdException;
import com.revature.bloodbank.model.BloodBankCenter;
import com.revature.bloodbank.exception.BloodBankInvalidDetailsException;
public interface BloodBankService {
	
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
