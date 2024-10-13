package topg.job.JobPackage.services;

import lombok.RequiredArgsConstructor;
import topg.job.CompanyPackage.model.Company;
import topg.job.CompanyPackage.repository.CompanyRepository;
import topg.job.JobPackage.dto.JobDto;
import topg.job.JobPackage.model.Job;
import topg.job.JobPackage.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public String createJob(JobDto jobDto, Integer companyId) {
        // Find the company by ID
        return companyRepository.findById(companyId)
                .map(company -> {
                    // Create a new Job entity and associate it with the company
                    Job job = Job.builder()
                            .jobDescription(jobDto.jobDescription())
                            .jobTitle(jobDto.jobTitle())
                            .minSalary(jobDto.minSalary())
                            .maxSalary(jobDto.maxSalary())
                            .location(jobDto.location())
                            .company(company) // Associate the job with the company
                            .build();

                    jobRepository.save(job);
                    return "Job created successfully for company: " + company.getCompanyName();
                })
                .orElse("Company not found");
    }

    public String updateJob(Integer jobId, JobDto jobDto, Integer companyId) {
        // Find the company and update the job
        return companyRepository.findById(companyId)
                .map(company -> jobRepository.findById(jobId)
                        .map(existingJob -> {
                            // Update job details
                            existingJob.setJobDescription(jobDto.jobDescription());
                            existingJob.setJobTitle(jobDto.jobTitle());
                            existingJob.setMinSalary(jobDto.minSalary());
                            existingJob.setMaxSalary(jobDto.maxSalary());
                            existingJob.setLocation(jobDto.location());
                            existingJob.setCompany(company); // Associate the updated job with the company

                            jobRepository.save(existingJob);
                            return "Job updated successfully for company: " + company.getCompanyName();
                        })
                        .orElse("Job not found"))
                .orElse("Company not found");
    }
}
