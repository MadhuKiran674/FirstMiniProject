package in.madhu.Service;

import java.util.Map;

import in.madhu.Bindings.LoginForm;
import in.madhu.Bindings.RegisterForm;
import in.madhu.Bindings.ResetPwdForm;
import in.madhu.Entity.User;

public interface UserService {
	public Map<Integer,String> getCountries();
	public Map<Integer,String> getStates(Integer countryId);
    public Map<Integer,String> getCities(Integer state_id);
    public User getUser(String email);
    public boolean saveUser(RegisterForm formobj);
    public User login(LoginForm formobj);
    public boolean Resetpwd(ResetPwdForm formobj);
    public String generatePwd();
    
    
	
	
	
}
