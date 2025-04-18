package com.example.basic.dto;

// 모든 폼 항목들을 멤버변수로 등록한뒤
// 각 항목마다 getter, setter메서드 등록
// 이런게 폼별로 DTO를 미리 준비해놓으면 폼 데이터를 파일단위로 구조적 관리 가능
// 컨트롤러에서 파라미터 값 전달을 효율적으로 처리 가능
public class JoinDTO {
    private String uname;
    private String email;
    private String colors;

    // 인스턴스 복사하는 생성자 함수
    public JoinDTO(){}

    // 각 폼의 항목별로 getter, setter등록
    public String getUname(){
        return uname;
    }
    public void setUname(String uname){
        this.uname = uname;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getColors(){
        return colors;
    }
    public void setColors(String colors){
        this.colors = colors;
    }
}
