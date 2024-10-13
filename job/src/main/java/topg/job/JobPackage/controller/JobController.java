package topg.job.JobPackage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import topg.job.JobPackage.dto.JobDto;
import topg.job.JobPackage.model.Job;
import topg.job.JobPackage.services.JobService;
import topg.job.JobPackage.repository.JobRepository;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JobController {
    private final JobRepository jobRepository;
    private final JobService jobService;

    // Get all jobs
    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Create a new job with a company ID
    @PostMapping("/createjobs/{companyId}")
    public ResponseEntity<String> createJobs(@Valid @RequestBody JobDto jobDto, @PathVariable("companyId") Integer companyId) {
        // Delegate job creation to the service with companyId
        String result = jobService.createJob(jobDto, companyId);
        if (result.contains("created")) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    // Get job by ID
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getByJobId(@PathVariable("id") Integer id) {
        return jobRepository.findById(id)
                .map(job -> ResponseEntity.ok(job))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete job by ID
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable("id") Integer id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }
    }

    // Update job by ID with a company ID
    @PutMapping("/jobs/{id}/{companyId}")
    public ResponseEntity<String> updateJobById(@PathVariable("id") Integer id, @Valid @RequestBody JobDto jobDto, @PathVariable("companyId") Integer companyId) {
        String result = jobService.updateJob(id, jobDto, companyId);

        if (result.contains("updated")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
