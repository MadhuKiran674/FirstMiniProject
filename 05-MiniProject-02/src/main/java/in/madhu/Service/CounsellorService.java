package in.madhu.Service;



	
	

import java.util.List;

import in.madhu.Bindings.DashboardResponse;
import in.madhu.Entity.Counsellor;



public interface CounsellorService {
	// to save the counsellor data
	public String saveCounsellor(Counsellor c);
	// to check the email and pwd
	public Counsellor loginCheck(String email,String pwd);

	//to recover the email if we forget the email then we will use this method
	public boolean recoveredpwd(String email);
	//to get the counsellor information
	public DashboardResponse getDashboard(Integer cid);
	


}

	 

