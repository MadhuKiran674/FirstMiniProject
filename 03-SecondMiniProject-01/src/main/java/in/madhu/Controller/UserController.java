package in.madhu.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.madhu.Bindings.LoginForm;
import in.madhu.Bindings.RegisterForm;
import in.madhu.Bindings.ResetPwdForm;
import in.madhu.Entity.User;
import in.madhu.Props.AppProp;
import in.madhu.Service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService us;
	@Autowired
	private AppProp props;

	
	@GetMapping("/")
	public String index(Model model) {
		LoginForm lo=new LoginForm();
		model.addAttribute("log",lo );
		return "login";
		
	}
	@PostMapping("/login")
	public String login(@ModelAttribute("log") LoginForm form,Model model) {
		
		User user=us.login(form);
		if(user==null) {
		
		
			model.addAttribute("error","Invalid Crediantials");
		return "login";
		}
		if(user.getPwdUpdated().equals("no")) {
			ResetPwdForm formobj=new ResetPwdForm();
			formobj.setUserId(user.getUserId());
			model.addAttribute("pwd", formobj);
			return "ResetPwd";
		}
		return "redirect:dashboard";
		
	}
	@PostMapping("/updatePwd")
	public String updatePwd(@ModelAttribute("pwd") ResetPwdForm rsp,Model model) {
		
		if(!rsp.getNewPwd().equals(rsp.getConfirmPwd())) {
			model.addAttribute("erro", "Both are should be same");
			return "ResetPwd";
		}
		
		boolean status=us.Resetpwd(rsp);
		if(status) {
			return "redirect:dashboard";
		}
		model.addAttribute("err", "pwd update failed");
		
		return "ResetPwd";
		
	}
	@GetMapping("/register")
	public String Register(Model model) {
		model.addAttribute("reg", new RegisterForm());
		Map<Integer,String> countries=us.getCountries();
		model.addAttribute("country", countries);
		return "register";
		
	}
	
	@PostMapping("/register")
	public String UserRegister(@ModelAttribute("reg") RegisterForm form,Model model) {
		boolean saveUser=us.saveUser(form);
		if(saveUser) {
			model.addAttribute("success", "Registration SucessFull");
		}
		else {
		model.addAttribute("errmsg", "Registration Failed");
		}
		Map<Integer,String> countries=us.getCountries();
		model.addAttribute("country", countries);
		
		return "register";
		
	}

	@GetMapping("/ResetPwd")
	public String ResetPwd(Model model) {
		return "ResetPwd";
		
	}
	@GetMapping("/getStates/{CountryId}")
	@ResponseBody
	public Map<Integer,String> loadStates(@PathVariable("CountryId")Integer CountryId){
		return us.getStates(CountryId);
		
	}
	@GetMapping("/getCities/{StateId}")
	@ResponseBody
	public Map<Integer,String> loadCities(@PathVariable("StateId") Integer StateId){
		return us.getCities(StateId);
		
	}
	
	
	public String UpdatePwd(ResetPwdForm form,Model model) {
		return null;
		
	}
	public String logout(Model model) {
		return null;
		
	}
	
	public String index() {
		return null;
	}
}
