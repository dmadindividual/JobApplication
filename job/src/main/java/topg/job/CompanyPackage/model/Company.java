package topg.job.CompanyPackage.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import topg.job.JobPackage.model.Job;
import topg.job.ReviewPackage.model.Review;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String companyName;
    private String companyDescription;
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;
    @OneToMany(mappedBy = "company")
    private List<Review> reviews;
}
