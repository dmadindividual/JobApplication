package topg.job.ReviewPackage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import topg.job.CompanyPackage.model.Company;
import topg.job.CompanyPackage.service.CompanyService;
import topg.job.ReviewPackage.model.Review;
import topg.job.ReviewPackage.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public List<Review> getAllReview(Integer companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    public void createReview(Integer companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("Company not found");
        }
    }

    public Review getReviewById(Integer companyId, Integer reviewId) {
        Company company = companyService.getCompanyById(companyId);

        if (company == null) {
            throw new IllegalArgumentException("Company not found");
        }

        Optional<Review> optionalReview = reviewRepository.findByIdAndCompanyId(reviewId, companyId);

        if (optionalReview.isPresent()) {
            return optionalReview.get();
        } else {
            throw new IllegalArgumentException("Review not found for this company");
        }
    }

    public void editReviewById(Integer companyId, Integer reviewId, Review review) {
        // Fetch the company by ID
        Company company = companyService.getCompanyById(companyId);

        // Check if the company exists
        if (company == null) {
            throw new IllegalArgumentException("Company not found");
        }

        // Find the review by reviewId and companyId
        Optional<Review> optionalReview = reviewRepository.findByIdAndCompanyId(reviewId, companyId);

        // Check if the review exists
        if (optionalReview.isPresent()) {
            Review existingReview = optionalReview.get();

            // Update the fields of the existing review with the new review data
            existingReview.setTitle(review.getTitle()); // Assuming Review has a title field
            existingReview.setDescription(review.getDescription()); // Assuming Review has a description field
            existingReview.setRating(review.getRating()); // Assuming Review has a rating field

            // Save the updated review
            reviewRepository.save(existingReview);
        } else {
            throw new IllegalArgumentException("Review not found for this company");
        }
    }

    public void deleteReviewById(Integer companyId, Integer reviewId) {
        // Fetch the company by ID
        Company company = companyService.getCompanyById(companyId);

        // Check if the company exists
        if (company == null) {
            throw new IllegalArgumentException("Company not found");
        }

        // Find the review by its ID and the company ID to ensure it belongs to the correct company
        Optional<Review> reviewOptional = reviewRepository.findByIdAndCompanyId(reviewId, companyId);

        // Check if the review exists
        if (reviewOptional.isPresent()) {
            // Delete the review
            reviewRepository.deleteById(reviewId);
        } else {
            throw new IllegalArgumentException("Review not found for this company");
        }
    }
}
