package in.madhu.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.madhu.Entity.City;
@Repository
public interface CityRepo extends JpaRepository<City,Integer> {

	public List<City> findByStateId(Integer stateid);
}
