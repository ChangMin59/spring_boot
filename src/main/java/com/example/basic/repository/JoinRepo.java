package com.example.basic.repository;

import com.example.basic.entity.JoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//Jpa라는 인터페이스에 JoinEntity내용을 기반으로한 JoinRepo라는 커스텀 인터페이스 확장
public interface JoinRepo extends JpaRepository<JoinEntity, Long> {
    //uname값과 email정보를 활용해서 기존 DB상의 데이터 유무를 찾는 커스텀 메서드 인터페이스에 등록
    JoinEntity findByUnameAndEmail(String uname, String email);
}
/*
    interface : 클래스에서 어떤 메서드가 필수로 정의되야 될지 지정하는 설계서
*/