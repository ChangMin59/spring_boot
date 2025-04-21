package com.example.basic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JoinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uname;
    private String email;
    private String colors;
}

/*
    jakarta 패키지로부터 Entity 생성하는 모듈 import
    클래스 안쪽에 원하는 필드명을 멤버변수로 등록
    이때 id 값을 @Id, @GeneratedValue 어노테이션 활용해서 auto_increment, primary key 설정 필수
 */