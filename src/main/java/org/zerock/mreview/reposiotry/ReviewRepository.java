package org.zerock.mreview.reposiotry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mreview.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
