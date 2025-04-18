package com.example.basic.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JoinController {

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
        System.out.println("user name: "+uname);
        System.out.println("email: "+email);
        System.out.println("color: "+colors);
        return "index";
    }
}
