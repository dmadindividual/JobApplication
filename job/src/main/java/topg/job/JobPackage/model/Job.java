package topg.job.JobPackage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import topg.job.CompanyPackage.model.Company;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String jobDescription;
    private String jobTitle;
    private String minSalary;
    private String maxSalary;
    private String location;
    @ManyToOne
    @JoinColumn(name = "company_id") // Foreign key column
    private Company company;
}
