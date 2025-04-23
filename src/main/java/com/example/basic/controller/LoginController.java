package com.example.basic.controller;

import com.example.basic.dto.LoginDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/login/check")
    public String checkLogin(@ModelAttribute LoginDTO loginDTO, Model model, HttpSession session) {
        JoinEntity user = loginService.checkUser(loginDTO.getUname(), loginDTO.getEmail());

        if (user != null) {
            //로그인 성공
            //인증된 사용자 정보를 session에 등록 처리
            session.setAttribute("loginUser", user);
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "일치하는 사용자가 없습니다.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 초기화 (로그아웃)
        return "redirect:/login";
    }
}

/*
    현재 까지 로그인 작업단계에서의 문제점
    - 현재의 로그인 인증은 DB에 저장된 데이터 매칭되는 유저 정보가 있는지만 확인
    - 로그인 성공시 유저 정보를 저장하지 않음
    - 때문에 이후 특정 사용자 요청이 와도 누가 로그인된 상태인지 기억할 방법이 없음
    - 결론 : 로그인 인증 이후에 현재 사용자가 인증된 사용자 인지 확인할 브라우저, 서버간의 정보공유 필요
    - 이때 필요한 것이 바로 쿠키, 세션, (세션활용 : 보안이슈)
 */