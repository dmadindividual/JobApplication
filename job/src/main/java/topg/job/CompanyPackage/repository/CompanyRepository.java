package topg.job.CompanyPackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import topg.job.CompanyPackage.dto.CompanyDto;
import topg.job.CompanyPackage.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
