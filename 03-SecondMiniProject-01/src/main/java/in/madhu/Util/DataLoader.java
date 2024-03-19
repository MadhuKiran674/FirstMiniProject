package in.madhu.Util;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.madhu.Entity.City;
import in.madhu.Entity.Country;
import in.madhu.Entity.State;
import in.madhu.Repo.CityRepo;
import in.madhu.Repo.CountryRepo;
import in.madhu.Repo.StateRepo;
@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CountryRepo crepo;
	@Autowired
	private StateRepo srepo;
	@Autowired
	private CityRepo ctrepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		crepo.deleteAll();
		srepo.deleteAll();
		ctrepo.deleteAll();
		Country c1=new Country(1,"India");
		Country c2=new Country(2,"USA");
		crepo.saveAll(Arrays.asList(c1,c2));
		State s1=new State(1,"AP",1);
		State s2=new State(2,"TN",1);
		State s3=new State(3,"Alaska",2);
		State s4=new State(4,"Texas",2);
		srepo.saveAll(Arrays.asList(s1,s2,s3,s4));
		City ct1=new City(1,"Kurnool",1);
		City ct2=new City(2,"Nandhyal",1);
		City ct3=new City(3,"Hyderabad",2);
		City ct4=new City(4,"warangal",2);
		City ct5=new City(5,"sitka",3);
		City ct6=new City(6,"kenai",3);
		City ct7=new City(7,"Houston",4);
		City ct8=new City(8,"Dallas ",4);
		ctrepo.saveAll(Arrays.asList(ct1,ct2,ct3,ct4,ct5,ct6,ct7,ct8));
	
		
	}

}
