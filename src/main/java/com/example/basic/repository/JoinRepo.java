package com.example.basic.repository;

import com.example.basic.entity.JoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//Jpa 라는 인터페이스에 JoinEntity 내용을 기반으로한 JoinRepo 라는 커스텀 인터페이스 확장
public interface JoinRepo extends JpaRepository<JoinEntity, Long> {
}

/*
    interface : 클래스에서 어떤 메서드가 필수로 정의되야 될지 지정하는 설계서
*/