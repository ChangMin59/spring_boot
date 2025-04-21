package com.example.basic.controller;
import com.example.basic.dto.JoinDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// Model 컨트롤러에서 템플릿에 데이터를 전달시 필요한 스프릿 내장
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// (URL)을 받아서, HTML 화면(View 페이지) 를 반환
@Controller
// 인스턴스를 담을 멤버변수에 final 지정 가능케함
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    // join 으로 접속하면 이 메서드가 실행
    // 처음 조인폼 화면 출력하는 get 방식 요청
    @GetMapping("/join")
    public String join(){
        return "join";
    }

    // 해당 조인폼 화면에서 폼의 전송 이벤트 발생시 데이터 가공하는 post 방식 요청
    // 이때 폼의 모든 요소를 일일이 전달하는 것이 아닌 DTO 파일로 감싸고 전달
    // Model 타입의 파라미터, 서비스에 전달된 데이터를 뷰템플릿에 전달하기 위한 전용 클래스
    @PostMapping("/join/create")
    public String create(
            // JoinDTO 타입의 formDTO 라는 파라미터로 기존 @RequestParam 대체
            // formDTO 에는 모든 폼 데이터가 객체로 그룹화되어 전달됨
            @ModelAttribute JoinDTO formDTO,
            Model model //Model 타입의 파라미터 추가
    ){
        // 서비스 메서드에 DTO
        String msg = joinService.processJoin(formDTO);
        // 추가한 파라미터로 자동 전달된 인스턴스의 addAttribute 라는 메서드를 통해
        // ("data": 전달할 문자열) 형식의 데이터를 index 템플릿에 전달
        model.addAttribute("data", msg);
        List<JoinEntity> users = joinService.getAllUsers();
        System.out.println(users);
        return "redirect:/admin";
    /*  템플릿에 데이터 전달 흐름
        1.컨트롤러에서 Model 타입의 파라미터 연결
        2.서비스에서 템플릿 전달한 문자값 리턴
        3.컨트롤러에서 전달받은 문자열을 model 의 addAttribute 메서드를 통해 index 템플릿에 전달
    */
    }
    @GetMapping("/admin")
    // Spring 이 자동 주입하는 객체로, 컨트롤러 → 뷰 템플릿으로 데이터를 전달
    public String showAdminPage(Model model){
        // getAllUsers(): DB 에서 사용자 정보를 조회하는 메서드
        List<JoinEntity> users = joinService.getAllUsers();
        // ${users} 또는 th:each="user : ${users}" 로 사용
        model.addAttribute("users", users);
        return "admin";
    }
}
/*
[ 브라우저 요청: GET /admin ]
         ↓
[ 컨트롤러 showAdminPage() ]
         ↓
[ joinService.getAllUsers() → DB 조회 ]
         ↓
[ model 에 users 리스트 저장 ]
         ↓
[ return "admin" → admin.html 실행 ]
         ↓
[ 뷰 페이지 렌더링 → 사용자에게 응답 ]
 */
