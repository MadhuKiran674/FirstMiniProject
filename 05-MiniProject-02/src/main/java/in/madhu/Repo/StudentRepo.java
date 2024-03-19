package in.madhu.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.madhu.Entity.StudentEnq;
@Repository
public interface StudentRepo extends JpaRepository<StudentEnq,Integer>{

	public List<StudentEnq> findByCid(Integer cid);
}
