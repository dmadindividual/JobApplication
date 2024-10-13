package topg.job.CompanyPackage.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import topg.job.CompanyPackage.dto.CompanyDto;
import topg.job.CompanyPackage.model.Company;
import topg.job.CompanyPackage.repository.CompanyRepository;
import topg.job.CompanyPackage.service.CompanyService;
import topg.job.JobPackage.dto.JobDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();

        // Convert the list of Company objects to CompanyDto objects
        List<CompanyDto> companyDtos = companies.stream()
                .map(company -> {
                    // Map jobs to JobDto and assign the corresponding CompanyDto
                    List<JobDto> jobDtos = company.getJobs().stream()
                            .map(job -> new JobDto(
                                    job.getJobDescription(),
                                    job.getJobTitle(),
                                    job.getMinSalary(),
                                    job.getMaxSalary(),
                                    job.getLocation(),
                                    null // If needed, you can include company data for jobs
                            ))
                            .collect(Collectors.toList());

                    // Create CompanyDto with the list of jobs
                    return new CompanyDto(
                            company.getCompanyName(),
                            company.getCompanyDescription(),
                            jobDtos
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(companyDtos);
    }

    @PostMapping("/company")
    public ResponseEntity<String> createCompany(@Valid @RequestBody CompanyDto dto) {
        companyService.createCompany(dto);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable("id") Integer id, @RequestBody CompanyDto dto) {
        String responseMessage = companyService.updateCompany(id, dto);
        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        // Check if the company exists
        if (companyRepository.existsById(id)) {
            // If it exists, delete the company
            companyRepository.deleteById(id);
            return ResponseEntity.ok("Company deleted successfully.");
        } else {
            // If it does not exist, return a not found response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found.");
        }
    }

//    @DeleteMapping("/company/{id}")
//    public ResponseEntity<String>  getCompanyById(@PathVariable("id") Integer id){
//       if(companyRepository.existsById(id)){
//           companyRepository.deleteById(id);
//           return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
//       } else {
//           return new ResponseEntity<>("Company found", HttpStatus.NOT_FOUND);
//       }
//    }
}
