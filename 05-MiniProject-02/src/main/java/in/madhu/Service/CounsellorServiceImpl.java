package in.madhu.Service;

import java.util.List;




import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.madhu.Bindings.DashboardResponse;
import in.madhu.Entity.Counsellor;
import in.madhu.Entity.StudentEnq;
import in.madhu.Repo.CounsellorRepo;
import in.madhu.Repo.StudentRepo;
import in.madhu.Util.EmailUtil;
@Service
public class CounsellorServiceImpl implements CounsellorService{

	@Autowired
	private CounsellorRepo crepo;
	@Autowired
	private EmailUtil eu;
	@Autowired
	private StudentRepo srepo;
	@Override
	public String saveCounsellor(Counsellor c) {

		Counsellor obj1=crepo.findByEmail(c.getEmail());
		if(obj1!=null) {
			return "Duplicate Email Not Allowed";
		}
		Counsellor savedObj=crepo.save(c);
		if(savedObj.getCid()!=null) {
			return "Registration Sucessfull";
		}
		return "Registration Failed";
	}

	@Override
	public Counsellor loginCheck(String email, String pwd) {
		Counsellor obj=crepo.findByEmailAndPwd(email, pwd);
		return obj;
	}

	@Override
	public boolean recoveredpwd(String email) {
		Counsellor c=crepo.findByEmail(email);
		if(c==null) {
			return false;
		}
		String subject="recover password";
		String body="Your password"+c.getPwd()+"";
		
		return eu.sendEmail(subject, body, email);
	}

	@Override
	public DashboardResponse getDashboard(Integer cid) {
		

		
		List<StudentEnq> allEnqs=srepo.findByCid(cid);
		int enrolledEnq=allEnqs.stream()
				.filter(e->e.getEnqStatus().equals("Enrolled"))
				.collect(Collectors.toList())
				.size();
		DashboardResponse dsb=new DashboardResponse();
		dsb.setTotalEnq(allEnqs.size());
		dsb.setEnrolledEnq(enrolledEnq);
	    dsb.setLostEnq(allEnqs.size()-enrolledEnq);
		
		return dsb;
	}
	

}
