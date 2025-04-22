package com.example.basic.controller;
import com.example.basic.dto.JoinDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    // 기본 URL뒤의 ? 다음의 값을 받을 때는 @RequestParamdmfh 전달 받음
    public String showAdminPage(@RequestParam(defaultValue="0") int page, Model model){
        //각 페이지별 출력할 데이터 개수
        int pageSize = 3;
        Page<JoinEntity> userPage = joinService.getUsersByPage(page, pageSize);

        model.addAttribute("userPage", userPage);
        model.addAttribute("currentPage", page);
        return "admin";
    }

    @GetMapping("/admin/del/{id}")
    public String delUser(@PathVariable Long id){
        joinService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    // 기존 url뒤의 /의 값을 받을 때는 @PathVariable로 전달 받음
    public String editUser(@PathVariable Long id, Model model){
        JoinEntity user = joinService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/admin/update")
    public String updateUser(JoinEntity formUser){
        joinService.updateUser(formUser);
        return "redirect:/admin";
    }
}