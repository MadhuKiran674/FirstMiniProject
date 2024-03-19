package in.madhu.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.madhu.Entity.State;
@Repository
public interface StateRepo extends JpaRepository<State,Integer> {

	public List<State> findByCountryId(Integer countryId);
}
