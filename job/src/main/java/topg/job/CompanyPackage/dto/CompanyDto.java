package topg.job.CompanyPackage.dto;

import jakarta.validation.constraints.NotBlank;
import topg.job.JobPackage.dto.JobDto;

import java.util.List;

public record CompanyDto(
        @NotBlank String companyName,
        @NotBlank String companyDescription,
        List<JobDto> jobs) {
}
