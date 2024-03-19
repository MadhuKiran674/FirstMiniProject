package in.madhu.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.madhu.Bindings.DashboardResponse;
import in.madhu.Entity.Counsellor;
import in.madhu.Service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	@Autowired
	private CounsellorService cs;
	@GetMapping("/logout")
	public String logout(HttpServletRequest req,Model model) {
		HttpSession session=req.getSession(false);
		session.invalidate();
		
		return "redirect:/";
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("counsellor", new Counsellor());

		return "loginView";
	}

	// After login if the credential is valid then go to dashBoard otherwise get the
	// error msg
	@PostMapping("/login")
	public String handleLogin(Counsellor c, HttpServletRequest req, Model model) {
		Counsellor obj = cs.loginCheck(c.getEmail(), c.getPwd());

		
		if (obj == null) {
			model.addAttribute("error", "Invalid Credentials");
			return "loginView";
		}
		HttpSession session = req.getSession(true);
		session.setAttribute("Cid", obj.getCid());

		return "redirect:dashboard";
	}

	@GetMapping("/dashboard")
	public String buildDashboard(HttpServletRequest req,Model model) {
		//get data for dashboard
	HttpSession sess=req.getSession(false);
	Object obj=sess.getAttribute("Cid");
	Integer cid=(Integer)obj;
		DashboardResponse dsr=cs.getDashboard(cid);

		
		model.addAttribute("dsr",dsr);
        return "dashboard";
	}

	@GetMapping("/register")
	public String regPage(Model model) {
		Counsellor c = new Counsellor();
		model.addAttribute("counsellor", c);
		return "register";
	}

	// to display the registered details of this method
	@PostMapping("/register")
	public String handlerRegister(Counsellor c, Model model) {
		String msg = cs.saveCounsellor(c);
		model.addAttribute("msg", msg);
		return "register";
	}

	@GetMapping("/forgotPwd")
	public String recoverPwdPage(Model model) {
		return "forgotPwd";
	}

	@GetMapping("/recover-pwd")
	public String recoverPwd(String email, Model model) {
		boolean status = cs.recoveredpwd(email);
		if (status) {
			model.addAttribute("smsg", "pwd sent to your email");

		} else {
			model.addAttribute("errmsg", "Invalid Email");
		}
		return "forgotPwd";
	}

}
