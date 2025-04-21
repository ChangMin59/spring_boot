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

    // id값으로 데이터삭제하는 jpa 메서드 호출
    public void delete(long id){
        joinRepo.deleteById(id);
    }
}