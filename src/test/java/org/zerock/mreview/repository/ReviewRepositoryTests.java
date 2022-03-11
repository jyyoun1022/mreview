package org.zerock.mreview.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;
import org.zerock.mreview.reposiotry.ReviewRepository;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("댓글 삽입")
    void insertReview(){

        IntStream.rangeClosed(1,200).forEach(i->{
            //영화번호
            Long mno = (long) (Math.random() * 100) + 1;
            //리뷰어 번호
            long mid = (long) (Math.random() * 100) + 1;

            Member member = Member.builder()
                    .mid(mid).build();

            Review review =Review.builder()
                    .movie(Movie.builder().mno(mno).build())
                    .member(member)
                    .grade((int) (Math.random()*5)+1)
                    .text("느낀점..."+i)
                    .build();

            reviewRepository.save(review);
        });

    }
    @Test
    @DisplayName("특정 영화의 모든 리뷰와 회원 닉네임")
    void testGetMovieReviews(){

        Movie movie =Movie.builder().mno(72L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview->{
            System.out.print(movieReview.getReviewNum());
            System.out.println("\t"+movieReview.getGrade());
            System.out.println("\t"+movieReview.getText());
            System.out.println("\t"+movieReview.getMember().getEmail());
        });
    }

}
