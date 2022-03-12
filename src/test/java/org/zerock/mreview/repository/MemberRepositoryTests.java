package org.zerock.mreview.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.reposiotry.MemberRepository;
import org.zerock.mreview.reposiotry.ReviewRepository;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("멤버삽입")
    void insertMember(){

        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("r"+i+"@naver.com")
                    .password("1111")
                    .nickName("reviewer"+i)
                    .build();

            memberRepository.save(member);
        });
    }
    @Test
    @DisplayName("회원삭제와 동시에 리뷰삭제")
    @Transactional
    @Commit
    void testDeleteMember(){

        Long mid =1L;

        Member member = Member.builder().mid(mid).build();

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}
