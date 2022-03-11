package org.zerock.mreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iNum;

    private String uuId;

    private String imgName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;



}
