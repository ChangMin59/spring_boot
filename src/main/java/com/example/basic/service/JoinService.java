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
    public String processJoin(JoinDTO dto) {
        // Entity 클래스로 부터 각 항목에 맞는 데이터를 인자로 전달하여 실제 DB에 저장할 모델 인스턴스 생성
        JoinEntity user = new JoinEntity(null, dto.getUname(), dto.getEmail(), dto.getColors());

        // 위에서 생성한 user 라는 인스턴스를 joinRepo 의 save 메서드로 전달하기만 하면 DB 에 데이터 저장됨
        // save() 리포지토리에서 JPA 구현체가 자동으로 생성 DB 저장 전용 메서드
        joinRepo.save(user);

        // 컨트롤링를 통해 실제 템플릿에 전달된 데이터가 맵핑된 태그문자열 반환
        // JoinDTO 타입의 폼 객체를 전달받은 데이터를 활용한 태그 문자열 생성후 리턴
        String result = "name:" +dto.getUname()+"<br />"
                + "email:" +dto.getEmail()+ "<br />"
                + "my color:" +dto.getColors();
        return result;
    }

    public List<JoinEntity> getAllUsers(){
        return joinRepo.findAll();
    }

    // id 값으로 데이터삭제하는 jpa 메서드 호출
    public void delete(long id){
        joinRepo.deleteById(id);
    }

    // id 값으로 해당 데이터만 가져오는 메서드
    public JoinEntity getUserById(long id){
        // 컨트롤러부터 id 값을 전달받아 해당 id 값에 매칭되는 데이터를 반환
        // 초기에 반환되는 값이 null 인 경우 JoinEntity 타입이 아니기 때문에 에러발생
        // 초기 에러를 피하기 위해서 orElseThrow 을 통해 예외처리
        return joinRepo.findById(id).orElseThrow(()->new RuntimeException("해당 아이디의 유저 없음"));
    }

    public void updateUser(JoinEntity user){
        joinRepo.save(user);
    }
}
/*
    JPA Respository 의 dirty checking 을 통한 저장, 수정 구분
    - JPA 에서는 따로 저장과 수정 메서드가 구분되어 있지 않고 save 로 모두 해결
    - DB 상에 id 값에 매칭되는 데이터가 없으면 save 호출시 -> insert SQL 문으로 변환
    - DB 상에 id 값에 매칭되는 데이터가 있으면 save 호출시 -> update SQL 문으로 변환

    - DTO 는 아직 DB 에 저장되지 않은 상태에서 폼필드값으로 구분해서 생성한 데이터틀 (처음 DB 에 데이터 저장시, 폼 값 전달시 필요)
    - Entity 는 DB 에 저장된 데이터의 틀을 강제하는 스키마 개념 (수정시 필요)
 */