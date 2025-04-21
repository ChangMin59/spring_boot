package com.example.basic.repository;

import com.example.basic.entity.JoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// jPA 인터페이스만 정의
// 이떄 JpaRepository를 상속하면서 전달되는 데이터를 JoinEntity 내용으로 강제하는 인터페이스 생성
public interface JoinRepo extends JpaRepository<JoinEntity, Long> {
}
