package org.zerock.mreview.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.reposiotry.MemberRepository;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

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
}
