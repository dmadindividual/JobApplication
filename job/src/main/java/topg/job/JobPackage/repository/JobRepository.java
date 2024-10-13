package topg.job.JobPackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import topg.job.JobPackage.model.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
