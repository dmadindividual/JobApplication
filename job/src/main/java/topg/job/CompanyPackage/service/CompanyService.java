package topg.job.CompanyPackage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import topg.job.CompanyPackage.dto.CompanyDto;
import topg.job.CompanyPackage.model.Company;
import topg.job.CompanyPackage.repository.CompanyRepository;
import topg.job.JobPackage.model.Job;
import topg.job.JobPackage.dto.JobDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public void createCompany(CompanyDto companyDto) {
        // Convert JobDto to Job entity
        List<Job> jobs = companyDto.jobs().stream()
                .map(this::mapToJobEntity)
                .collect(Collectors.toList());

        // Build Company entity
        Company company = Company.builder()
                .companyName(companyDto.companyName())
                .companyDescription(companyDto.companyDescription())
                .jobs(jobs)
                .build();

        // Save the company to the repository
        companyRepository.save(company);
    }

    // Helper method to convert JobDto to Job entity
    private Job mapToJobEntity(JobDto jobDto) {
        return Job.builder()
                .jobDescription(jobDto.jobDescription())
                .jobTitle(jobDto.jobTitle())
                .minSalary(jobDto.minSalary())
                .maxSalary(jobDto.maxSalary())
                .location(jobDto.location())
                .build();
    }

    public String updateCompany(Integer id, CompanyDto companyDto) {
        return companyRepository.findById(id)
                .map(existingCompany -> {
                    existingCompany.setCompanyName(companyDto.companyName());
                    existingCompany.setCompanyDescription(companyDto.companyDescription());

                    // Update jobs if needed (optional)
                    List<Job> updatedJobs = companyDto.jobs().stream()
                            .map(this::mapToJobEntity) // Convert JobDto to Job entity
                            .collect(Collectors.toList());
                    existingCompany.setJobs(updatedJobs);
                    companyRepository.save(existingCompany);
                    return "Company updated successfully.";
                })
                .orElse("Company not found.");
    }

    public Company getCompanyById(Integer id) {
        // Fetch company by ID from the repository
        Optional<Company> companyOptional = companyRepository.findById(id);
        // Return the company if found, otherwise return null
        return companyOptional.orElse(null);
    }
}
