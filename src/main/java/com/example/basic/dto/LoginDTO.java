package com.example.basic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String uname;
    private String email;
}
/*
JoinDTO와는다르게 getter, setter만 쓰는 이유
단순히 이름, 이메일 정보만 받아서 서버쪽에서 DB정보와 비교 용도로만 쓰임
기존의 회원정보 저장 같이 객체에서 문자값을 뽑아서 화면에 출력할 필요가 없으므로 ToString 필요 없음
폼에서 전달받은 데이터를 단순 비교용도로만 활용하기 때문에 인스턴스 생성이 불필요해서 생성자 어노테이션도 필요 없음

 */