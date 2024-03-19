package in.madhu.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.madhu.Bindings.LoginForm;
import in.madhu.Bindings.RegisterForm;
import in.madhu.Bindings.ResetPwdForm;
import in.madhu.Entity.City;
import in.madhu.Entity.Country;
import in.madhu.Entity.State;
import in.madhu.Entity.User;
import in.madhu.Repo.CityRepo;
import in.madhu.Repo.CountryRepo;
import in.madhu.Repo.StateRepo;
import in.madhu.Repo.UserRepo;
import in.madhu.Util.EmailUtil;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private CountryRepo crepo; 
	@Autowired
	private StateRepo srepo;
	@Autowired
	private CityRepo cityRepo;
	@Autowired
	private UserRepo urepo;
	@Autowired
	private EmailUtil emailutils;
	
	

	@Override
	public Map<Integer, String> getCountries() {
		// TODO Auto-generated method stub
		Map<Integer,String> country=new HashMap<>();
		
		List<Country> list=crepo.findAll();
		list.forEach(c->{
			country.put(c.getCountryId(),c.getCountryName());
		});
		return country;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
	
		Map<Integer,String> states=new HashMap<>();
		
	    List<State> lis=srepo.findByCountryId(countryId);
		lis.forEach(s->{
			states.put(s.getStateId(), s.getStateName());
	});
		return states;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
	
		Map<Integer,String> cities=new HashMap<>();
		List<City> list=cityRepo.findByStateId(stateId);
		list.forEach(c->{
			cities.put(c.getCityId(), c.getCityName());
		});
		return cities;
	}

	@Override
	public User getUser(String email) {
		
		return urepo.findByemail(email);
	}

	@Override
	public boolean saveUser(RegisterForm formobj) {
		formobj.setPwd(generateRandomPwd());
		formobj.setPwdUpdated("no");
		User k=new User();
		BeanUtils.copyProperties(formobj, k);
		
		urepo.save(k);
		String subject="your account is created";
		String body="your pwd"+formobj.getPwd();
		return emailutils.sendEmail(subject, body, formobj.getEmail());
		
		
	}

	private String generateRandomPwd() {
		String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuv";
		 
	    StringBuffer randomString = new StringBuffer(5);
	    Random random = new Random();
	 
	    for (int i = 0; i < 5; i++) {
	        int randomIndex = random.nextInt(alphanumericCharacters.length());
	        char randomChar = alphanumericCharacters.charAt(randomIndex);
	        randomString.append(randomChar);
	    }
	 
	    return randomString.toString();
	}

	@Override
	public User login(LoginForm formobj) {
		return urepo.findByEmailAndPwd(formobj.getEmail(), formobj.getPwd());
		
	}

	@Override
	public boolean Resetpwd(ResetPwdForm formobj) {
	
		Optional<User> findbyid=urepo.findById(formobj.getUserId());
		if(findbyid.isPresent()) {
			User user=findbyid.get();
			user.setPwd(formobj.getNewPwd());
			user.setPwdUpdated("yes");
			urepo.save(user);
			return true;
			
		}
		return false;
	}

	@Override
	public String generatePwd() {
		// TODO Auto-generated method stub
		return null;
	}

}
