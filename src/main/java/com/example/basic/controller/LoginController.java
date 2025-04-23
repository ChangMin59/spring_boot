package com.example.basic.controller;

import com.example.basic.dto.LoginDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginPage(Model model){
        //get 방식 요청이므로 아직 넘겨받은 폼 값이 없어서 new 연산자로 빈 인스턴스 객체를 생성후 view 템플릿에 전달
        //해당 빈 DTO 객체는 추후 뷰화면에서 사용자가 입력한 값으로 담기게 됨
        model.addAttribute("loginDTO", new LoginDTO());

        return "login";
    }


    @PostMapping("/login/check")
    public String checkLogin(@ModelAttribute LoginDTO loginDTO, Model model) {
        JoinEntity user = loginService.checkUser(loginDTO.getUname(), loginDTO.getEmail());

        if (user != null) {

            return "redirect:/admin";
        } else {
            model.addAttribute("error", "일치하는 사용자가 없습니다.");

            return "login";
        }
    }
}


/*
 세션, 쿠키
 쿠키 : 사용자의 브라우저에 간단한 텍스트 형식의 데이터, 서버에서 생성되서 클라이언트(브라우저) 전달
 - 사용자(브라우저) url을 통해 서버에 접속하면 서버에선 특정 정보를 계속 활용하기 위해 사용자 브라우저에 쿠키를 전달해 저장

세션 : 서버상에서 사용자 정보를 서버 메모리에 저장
- 브라우저가 서버에 접속시 사용자마다의 고유  ID룰 부여해서 로그인된 사용자 정보를 서버 메모리에 저장
- 일반적인 로그인 로직은 사용자의 로그인 정보는 세션으로 관리하고 세션 유지를 위한 세션 ID값만 쿠키에 저장해서 브라우저에 전달

실제 브라우저, 서버간 쿠키, 세션이 전달되는 흐름
1. 서버에서 쿠키 생성
- 사용자가 서버에 처음 접속시 서버는 세션을 생성하고 새로운 세션 ID발급
- 이후 서버에서 생성한 쿠키에 세션 ID등의 정보를 담아서HTTP 응답 헤더에 전달
- 클라이언트에서 서버에 접속할때마다 해당 정보가 담겨있는 쿠키를 다시 서버에 전달해서 인증처리
 */

/*
    현재 까지 로그인 작업단계에서의 문제점
    - 현재의 로그인 인증은 DB에 저장된 데이터 매칭되는 유저 정보가 있는지만 확인
    - 로그인 성공시 유저 정보를 저장하지 않음
    - 때문에 이후 특정 사용자 요청이 와도 누가 로그인된 상태인지 기억할 방법이 없음
    - 결론 : 로그인 인증 이후에 현재 사용자가 인증된 사용자 인지 확인할 브라우저, 서버간의 정보공유 필요
    - 이때 필요한 것이 바로 쿠키, 세션, (세션활용 : 보안이슈)
 */
