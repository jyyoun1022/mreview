package org.zerock.mreview.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;
import org.zerock.mreview.reposiotry.MovieImageRepository;
import org.zerock.mreview.reposiotry.MovieRepository;

import java.util.Arrays;
import java.util.List;
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

    @Test
    @DisplayName("리스트 출력 테스트")
    void testListPage(){

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        List<Object[]> content = result.getContent();

        content.forEach(i-> System.out.println(Arrays.toString(i)));
    }
    @Test
    @DisplayName("영화 상세")
    void testGetMovieWithAll(){
        Long mno =43L;

        List<Object[]> result = movieRepository.getMovieWithAll(mno);

        result.forEach(i-> System.out.println(Arrays.toString(i)));
    }
}
