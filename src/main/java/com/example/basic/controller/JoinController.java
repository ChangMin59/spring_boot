package com.example.basic.controller;
import com.example.basic.dto.JoinDTO;
import com.example.basic.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// Model 컨트롤러에서 템플릿에 데이터를 전달시 필요한 스프릿 내장
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// (URL)을 받아서, HTML 화면(View 페이지) 를 반환
@Controller
public class JoinController {
    // Spring이 JoinService 객체를 자동으로 찾아서 이 변수에 주입
    // 서비스 계층으로부터 joinService라는 인스턴스 @Autowired라는 어느테이션을 통해 바로 해당 인스턴스 객체로 바로 가져올수 있음(new 연산자 호출 필요없음)
    @Autowired
    private JoinService joinService;

    // join 으로 접속하면 이 메서드가 실행
    // 처음 조인폼 화면 출력하는 get방식 요청
    @GetMapping("/join")
    public String join(){
        return "join";
    }

    // 해당 조인폼 화면에서 폼의 전송 이벤트 발생시 데이터 가공하는 post방식 요청
    // 이때 폼의 모든 요소를 일일이 전달하는 것이 아닌 DTO파일로 감싸고 전달
    // Model타입의 파라미터, 서비스에 전달된 데이터를 뷰템플릿에 전달하기 위한 전용 클래스
    @PostMapping("/join/create")
    public String create(
            // JoinDTO타입의 formDTO라는 파라미터로 기존 @RequestParam대체
            // formDTO에는 모든 폼 데이터가 객체로 그룹화되어 전달됨
            @ModelAttribute JoinDTO formDTO,
            Model model //Model 타입의 파라미터 추가
    ){
        // 서비스 메서드에 DTO
        String msg = joinService.processJoin(formDTO);
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