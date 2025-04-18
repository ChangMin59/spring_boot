package com.example.basic.service;

import com.example.basic.dto.JoinDTO;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    public String processJoin(JoinDTO dto) {
        // 컨트롤링를 통해 실제 템플릿에 전달된 데이터가 맵핑된 태그문자열 반환
        String result = "name:" +dto.getUname()+"<br />"
                + "email:" +dto.getEmail()+ "<br />"
                + "my color:" +dto.getColors();
        return result;
    }
}