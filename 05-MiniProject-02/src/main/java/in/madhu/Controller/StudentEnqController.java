package in.madhu.Controller;




import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.madhu.Bindings.SearchCriteria;
import in.madhu.Entity.StudentEnq;
import in.madhu.Service.StudentEnqService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class StudentEnqController {
	@Autowired
	private StudentEnqService es;
	
	@GetMapping("/enquiry")
	public String enqPage(Model model) {
		//model.addAttribute("student", new StudentEnq());
		model.addAttribute("enq",new StudentEnq());
		return "addEnq";
	}
	@PostMapping("/enquiry")
	public String addEnq(@ModelAttribute("enq") StudentEnq enq,HttpServletRequest req,Model model) {
		
		HttpSession session=req.getSession(false);
		Integer cid=(Integer)session.getAttribute("Cid");		
		if(cid==null) {
			return "redirect:/logout";
		}
		enq.setCid(cid);
		boolean add=es.addEnq(enq);
		if(add) {
			model.addAttribute("sucess", "Enquiry Added");
		}
		else
		{
			model.addAttribute("err", "notInserted");
		}
		
		
		return "addEnq";
	}
	@GetMapping("/enquiries")
	public String viewEnquiries(HttpServletRequest req,Model model) {
		HttpSession session=req.getSession(false);
		Integer cid=(Integer)session.getAttribute("Cid");
		if(cid==null) {
			return "redirect:/logout";
		}
		model.addAttribute("sc", new SearchCriteria());
		List<StudentEnq> enqList=es.getEnquiries(cid, new SearchCriteria());
		model.addAttribute("enquiries", enqList);
		
		
		return "displayEnq";
	}
	@PostMapping("/filter")
	public String filterEnquiries(@ModelAttribute("sc")SearchCriteria sc,HttpServletRequest req,Model model) {
		HttpSession session=req.getSession(false);
		Integer cid=(Integer)session.getAttribute("Cid");
		if(cid==null) {
			return "redirect:/logout";
		}
		List<StudentEnq> enqList=es.getEnquiries(cid, sc);
		model.addAttribute("enquiries", enqList);
		return "filterEnqView";
	}
	

}
