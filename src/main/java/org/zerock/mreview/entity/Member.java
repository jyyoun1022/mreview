package org.zerock.mreview.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "m_member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    private String email;

    private String password;

    private String nickName;


}
