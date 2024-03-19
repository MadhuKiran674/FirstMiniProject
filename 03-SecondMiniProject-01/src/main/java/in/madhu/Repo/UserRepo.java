package in.madhu.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.madhu.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

	public User findByemail(String email);
	public User findByEmailAndPwd(String email,String pwd);
}
