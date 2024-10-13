package topg.job.ReviewPackage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import topg.job.CompanyPackage.model.Company;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String title;
    private double rating;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


}
