package org.zerock.mreview.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;
import org.zerock.mreview.reposiotry.MovieImageRepository;
import org.zerock.mreview.reposiotry.MovieRepository;

import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Test
    @DisplayName("무비삽입")
    @Commit
    @Transactional
    void insertMovies(){

        IntStream.rangeClosed(1,100).forEach(i->{

            Movie movie = Movie.builder().title("Movie...."+i).build();

            System.out.println("=====================================");

            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for(int j=0;j< count; j++){
                MovieImage movieImage = MovieImage.builder()
                        .uuId(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test"+j+",jpg")
                        .build();

                movieImageRepository.save(movieImage);
            }
            System.out.println("=====================================");
        });
    }
}
