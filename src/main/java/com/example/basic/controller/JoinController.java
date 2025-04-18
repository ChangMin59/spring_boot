package com.example.basic.controller;
import com.example.basic.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// Model 컨트롤러에서 템플릿에 데이터를 전달시 필요한 스프릿 내장
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JoinController {
    @Autowired
    private JoinService joinService;

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join/create")
    public String create(
            @RequestParam("uname") String uname,
            @RequestParam("email") String email,
            @RequestParam("colors") String colors,
            Model model //Model 타입의 파라미터 추가
    ){
        // 서비스 메서드로 부터 전달받은 문자열을 전달 받음
        String msg = joinService.processJoin(uname, email, colors);
        // 추가한 파라미터로 자동 전달된 인스턴스의 addAttribute라는 메서드를 통해
        // ("data": 전달할 문자열) 형식의 데이터를 index템플릿에 전달
        model.addAttribute("data", msg);
        return "index";
    }
}
/*  템플릿에 데이터 전달 흐름
    1.컨트롤러에서 Model 타입의 파라미터 연결
    2.서비스에서 템플릿 전달한 문자값 리턴
    3.컨트롤러에서 전달받은 문자열을 model의 addAttribute메서드를 통해 index템플릿에 전달
 */