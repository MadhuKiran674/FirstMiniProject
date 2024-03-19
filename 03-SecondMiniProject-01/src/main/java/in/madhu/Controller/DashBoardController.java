package in.madhu.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.madhu.Service.DashBoardService;

@Controller
public class DashBoardController {
	@Autowired
	private DashBoardService ds;
	

	@GetMapping("/dashboard")
	public String BuildDashBoard(Model model) {
		String Quote=ds.getQuote();
		model.addAttribute("quote", Quote);
		return "dashboard";
		
	}
	public String getNewQuote(Model model) {
		return null;
		
	}
}
