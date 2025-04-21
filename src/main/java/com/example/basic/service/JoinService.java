package com.example.basic.service;

import com.example.basic.dto.JoinDTO;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    public String processJoin(JoinDTO dto) {
        // 컨트롤링를 통해 실제 템플릿에 전달된 데이터가 맵핑된 태그문자열 반환
        // JoinDTO타입의 폼 객체를 전달받은 데이터를 활용한 태그 문자열 생성후 리턴
        String result = "name:" +dto.getUname()+"<br />"
                + "email:" +dto.getEmail()+ "<br />"
                + "my color:" +dto.getColors();
        return result;
    }
}