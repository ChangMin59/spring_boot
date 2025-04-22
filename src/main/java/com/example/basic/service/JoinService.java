package com.example.basic.service;

import com.example.basic.dto.JoinDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.repository.JoinRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        //초기에 반환되는 값이 null 인 경우 JoinEntity 타입이 아니기 때문에 에러발생
        //초기 에러를피하기 위해서 onElseThrow 를 통해 예외처리
        return joinRepo.findById(id).orElseThrow(()-> new RuntimeException("해당 아이디의 유저 없음"));
    }

    public  void updateUser(JoinEntity user){
        joinRepo.save(user);
    }

    // 페이지 번호에 따라 유저 데이터 가져오는 메서드
    public Page<JoinEntity> getUsersByPage(int page, int size){
        // page: 페이지 번호, size: 한페이지에 불러올 데이터 갯수
        return joinRepo.findAll(PageRequest.of(page, size));
    }
}
