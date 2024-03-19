package in.madhu.Service;



import java.util.List;

import in.madhu.Bindings.SearchCriteria;
import in.madhu.Entity.StudentEnq;



public interface StudentEnqService {
	//To save student Enquiries Data
	public boolean addEnq(StudentEnq sq);
	//To Get the student details by id 
	public List<StudentEnq> getEnquiries(Integer enqid,SearchCriteria sc);

}
