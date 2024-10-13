package topg.job.ReviewPackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import topg.job.ReviewPackage.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByCompanyId(Integer companyid);

    Optional<Review> findByIdAndCompanyId(Integer companyid, Integer reviewId);
}
