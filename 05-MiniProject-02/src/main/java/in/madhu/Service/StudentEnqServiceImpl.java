package in.madhu.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.madhu.Bindings.SearchCriteria;
import in.madhu.Entity.StudentEnq;
import in.madhu.Repo.StudentRepo;
@Service
public class StudentEnqServiceImpl implements StudentEnqService {

	@Autowired
	private StudentRepo srepo;
	@Override
	public boolean addEnq(StudentEnq sq) {

		StudentEnq saved=srepo.save(sq);
		return saved.getCid()!=null;
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer enqid, SearchCriteria sc) {
		StudentEnq ste=new StudentEnq();
		ste.setCid(enqid);
		if(sc.getEnqMode()!=null && !sc.getEnqMode().equals("")) {
			ste.setEnqMode(sc.getEnqMode());
		}
		if(sc.getEnqCourse()!=null && !sc.getEnqCourse().equals("")) {
			ste.setEnqCourse(sc.getEnqCourse());
		}
		if(sc.getEnqStatus()!=null && !sc.getEnqStatus().equals("")) {
			ste.setEnqStatus(sc.getEnqStatus());
		}
	   Example<StudentEnq> of=Example.of(ste);
		
		List<StudentEnq> lis=srepo.findAll(of);
		return lis;
	}

	
}
