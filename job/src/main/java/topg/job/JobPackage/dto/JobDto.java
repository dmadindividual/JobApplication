package topg.job.JobPackage.dto;

import jakarta.validation.constraints.NotBlank;
import topg.job.CompanyPackage.dto.CompanyDto;

public record JobDto(
        @NotBlank(message = "Job description must not be empty")
        String jobDescription,

        @NotBlank(message = "Job title must not be empty")
        String jobTitle,

        @NotBlank(message = "Minimum salary must not be empty")
        String minSalary,

        @NotBlank(message = "Maximum salary must not be empty")
        String maxSalary,

        @NotBlank(message = "Location must not be empty")
        String location,

        CompanyDto company // Adding the CompanyDto reference to JobDto
) {}
