import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Test;

import com.revature.bloodbank.dao.BloodBankDAO;
import com.revature.bloodbank.dao.BloodBankDAOImpl;
import com.revature.bloodbank.model.BloodBankCenter;
import com.revature.bloodbank.util.DBUtil;

public class BloodBankDAOImplTest {
//remember change center id for continuous add and delete check,and for update change content value
	@Test
	public void testAddBloodBankCenter() {
		int countBeforeInsert=0,countAfterInsert=0;
		try {
			Connection con=DBUtil.getConnection();
			PreparedStatement pst=con.prepareStatement("select count(centerid) from bloodbankcenter");
			ResultSet rst=pst.executeQuery();
			//before adding center id's count
			countBeforeInsert=(rst.next()?rst.getInt(1):0);
		
			//adding
			BloodBankDAO bloodBankDAOImpl=new BloodBankDAOImpl();
			BloodBankCenter bloodBankCenter=new BloodBankCenter();
			bloodBankCenter.setCenterId(136);//next time 137 //while testing everytime change center id to avoid duplicate primary key error
			bloodBankCenter.setCenterName("IMA Blood Bank");
			bloodBankCenter.setStreet("Behind Maharaja`s College");
			bloodBankCenter.setCity("Ernakulam");
			bloodBankCenter.setState("Kerala");
			bloodBankCenter.setPincode("682011");
			bloodBankDAOImpl.addBloodBankCenter(bloodBankCenter);//calling addbloodbankcenter method
		 	
			
			//after adding a center, center id's count
			 pst=con.prepareStatement("select count(centerid) from bloodbankcenter");
			 rst=pst.executeQuery();
			countAfterInsert=(rst.next()?rst.getInt(1):0);//after adding a center, count of center id, suppose rst.next() returns false then count is zero
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		assertNotEquals(countBeforeInsert,countAfterInsert);//there should be different
		assertEquals(countAfterInsert-countBeforeInsert,1);//there should be one row difference
		
	}  

	@Test
	public void testDeleteBloodBankCenterByCenterId() {
		boolean flag1=false,flag2=true;
		try {
			Connection con=DBUtil.getConnection();
			//before delete availability of candidateid
			PreparedStatement pst=con.prepareStatement("select * from bloodbankcenter where centerid=?");
			pst.setInt(1,132);//next time 133
			ResultSet rst=pst.executeQuery();
			if(rst.next())
				flag1=true;
			//deleting
			BloodBankDAO bloodBankDAOImpl=new BloodBankDAOImpl();
			BloodBankCenter bloodBankCenter=new BloodBankCenter();
			bloodBankCenter.setCenterId(132);//next time 133
			bloodBankDAOImpl.deleteBloodBankCenterByCenterId(bloodBankCenter);
			
			
			//after deletion, center name
			con.prepareStatement("select * from bloodbankcenter where centerid=?");
			pst.setInt(1,132);//next time 133
			rst=pst.executeQuery();
			flag2=rst.next();//check after deletion
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertTrue(flag1);//before deletion candidate details should be there for given center id
		assertFalse(flag2);//after deletion candidate details should not be there for given center id
		assertNotSame(flag1,flag2);//flag1 should be true and flag2 should be false, so they are not same
	}

	@Test
	public void testUpdateBloodBankCenterById() {
		String beforeCenterName=null,afterCenterName=null;
		try {
			Connection con=DBUtil.getConnection();
			//before update centername value
			PreparedStatement pst=con.prepareStatement("select centername from bloodbankcenter where centerid=?");
			pst.setInt(1,117);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				beforeCenterName=rst.getString(1);
			
			}
			
			//updating
			BloodBankDAO bloodBankDAOImpl=new BloodBankDAOImpl();
			BloodBankCenter bloodBankCenter=new BloodBankCenter();
			bloodBankCenter.setCenterName("punjab");//next time change to NSS
			bloodBankCenter.setCenterId(117);
			bloodBankDAOImpl.updateBloodBankCenterById(bloodBankCenter);
			
			
			//after updating, center name
			 pst=con.prepareStatement("select centername from bloodbankcenter where centerid=?");
				pst.setInt(1,117);
				rst=pst.executeQuery();
				if(rst.next()) {
					afterCenterName=rst.getString(1);
					
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertNotSame(beforeCenterName,afterCenterName);//previous centername and current updated centername should not be same
		
	}

	@Test
	public void testGetBloodBankCenterByCity() {
		boolean flag1=false,flag2=false;
		try {
			Connection con=DBUtil.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from bloodbankcenter where city=?");
			
			pst.setString(1,"Chennai");//this entry exists, so flag=true
			ResultSet rst=pst.executeQuery();
			if(rst.next())
				flag1=true;
			
			pst.setString(1,"Madurai");//this entry is not there, so flag=false
			rst=pst.executeQuery();
			if(rst.next())
				flag2=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertTrue(flag1);
		assertFalse(flag2);
	}

	@Test
	public void testGetBloodBankCenterByState() {
		boolean flag1=false,flag2=false;
		try {
			Connection con=DBUtil.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from bloodbankcenter where state=?");
			
			pst.setString(1,"kerala");//this entry exists, so flag=true
			ResultSet rst=pst.executeQuery();
			if(rst.next())
				flag1=true;
			
			pst.setString(1,"mumbai");//this entry is not there, so flag=false
			rst=pst.executeQuery();
			if(rst.next())
				flag2=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertTrue(flag1);
		assertFalse(flag2);
	}

	@Test
	public void testGetBloodBankCenterByPincode() {
		boolean flag1=false,flag2=false;
		try {
			Connection con=DBUtil.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from bloodbankcenter where pincode=?");
			
			pst.setString(1,"600008");//this entry exists, so flag=true
			ResultSet rst=pst.executeQuery();
			if(rst.next())
				flag1=true;
			
			pst.setString(1,"567834");//this entry is not there, so flag=false
			rst=pst.executeQuery();
			if(rst.next())
				flag2=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertTrue(flag1);
		assertFalse(flag2);
	}

	@Test
	public void testGetBloodBankCenterByStreet() {
		boolean flag1=false,flag2=false;
		try {
			Connection con=DBUtil.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from bloodbankcenter where street=?");
			
			pst.setString(1,"Gollavar Agraharam Road");//this entry exists, so flag=true
			ResultSet rst=pst.executeQuery();
			if(rst.next())
				flag1=true;
			
			pst.setString(1,"Valasaravaakkam");//this entry is not there, so flag=false
			rst=pst.executeQuery();
			if(rst.next())
				flag2=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertTrue(flag1);
		assertFalse(flag2);
	}

	@Test
	public void testGetBloodBankCenterByCenterName() {
		boolean flag1=false,flag2=false;
		try {
			Connection con=DBUtil.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from bloodbankcenter where centername=?");
			
			pst.setString(1,"Rotary Central TTK VHS Blood Bank");//this entry exists, so flag=true
			ResultSet rst=pst.executeQuery();
			if(rst.next())
				flag1=true;
			
			pst.setString(1,"City Blood Bank");//this entry is not there, so flag=false
			rst=pst.executeQuery();
			if(rst.next())
				flag2=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertTrue(flag1);
		assertFalse(flag2);
		
	}

	@Test
	public void testGetAllBloodBankCenters() {
		boolean flag=false;
		try {
			Connection con=DBUtil.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from bloodbankcenter");
			ResultSet rst=pst.executeQuery();
			if(rst.next())
				flag=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertTrue(flag);
		
	}  

}
