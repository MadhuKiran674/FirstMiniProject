package in.madhu.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.madhu.Entity.Country;
@Repository
public interface CountryRepo extends JpaRepository<Country,Integer> {

}
