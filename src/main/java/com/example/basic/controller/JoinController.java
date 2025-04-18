package com.example.basic.controller;
import com.example.basic.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
            @RequestParam("colors") String colors
    ){
        joinService.processJoin(uname, email, colors);
        return "index";
    }
}