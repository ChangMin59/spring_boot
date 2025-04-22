package com.example.basic.service;

import com.example.basic.dto.JoinDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.repository.JoinRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final JoinRepo joinRepo;

    public void processJoin(JoinDTO dto) {
        JoinEntity user = new JoinEntity(null, dto.getUname(), dto.getEmail(), dto.getColors() );
        joinRepo.save(user);
    }

    // 모든 DB 데이터 조회해서 반환
    public List<JoinEntity> getAllUsers(){
        return joinRepo.findAll();
    }

    //id 값으로 데이터삭제하는 jpa 메서드 호출
    public void delete(Long id){
        joinRepo.deleteById(id);
    }

    //id 값으로 해당 데이터만 가져오는 메서드
    public JoinEntity getUserById(Long id){
        //컨트롤러부터 id 값을 전달받아 해당 id 값에 매칭되는 데이터를 반환
        //초기에 반환되는 값이 null인 경우 JoinEntity 타입이 아니기 때문에 에러발생
        //초기 에러를피하기 위해서 onElseThrow 를 통해 예외처리
        return joinRepo.findById(id).orElseThrow(()-> new RuntimeException("해당 아이디의 유저 없음"));
    }

    public  void updateUser(JoinEntity user){
        joinRepo.save(user);
    }
}
/*
    JPA Respository 의 dirty checking 을 통한 저장, 수정 구분
    - JPA 에서는 따로 저장과 수정 메서드가 구분되어 있지 않고 save 로 모두 해결
    - DB 상에 id 값에 매칭되는 데이터가 없으면 save 호출시 -> insert SQL 문으로 변환
    - DB 상에 id 값에 매칭되는 데이터가 있으면 save 호출시 -> update SQL 문으로 변환

    - DTO 는 아직 DB에 저장되지 않은 상태에서 폼필드값으로 구분해서 생성한 데이터틀 (처음 DB에 데이터 저장시, 폼 값 전달시 필요)
    - Entity 는 DB에 저장된 데이터의 틀을 강제하는 스키마 개념 (수정시 필요)
 */