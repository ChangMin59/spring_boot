package com.example.basic.controller;
import com.example.basic.dto.JoinDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//인스턴스를 담을 멤버변수에 final 지정 가능케 함
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join/create")
    public String create( JoinDTO formDTO ){
        joinService.processJoin(formDTO);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model){
        List<JoinEntity> users = joinService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }


    @GetMapping("/admin/del/{id}")
    public String delUser(@PathVariable Long id){
        joinService.delete(id);
        return "redirect:/admin";
    }

    // 수정 버튼 클릭시 해당 메서드 어노테이션 호출
    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        //서비스 메서드인 getUserById에 클릭한 게시글의 아이디값 전달해서 게시글 반환받음
        JoinEntity user = joinService.getUserById(id);
        //model.addAttribute 메서드에 의해서 edit 뷰화면에 해당 데이터 전달
        model.addAttribute("user", user);
        return "edit";
    }

    //수정폼 화면에서 최종 수정이 일어나는 컨트롤러
    @PostMapping("/admin/update")
    public String updateUser(JoinEntity formUser){
        joinService.updateUser(formUser);
        return "redirect:/admin";
    }


}