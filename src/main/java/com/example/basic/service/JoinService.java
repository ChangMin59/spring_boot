package com.example.basic.service;

import org.springframework.stereotype.Service;

@Service
public class JoinService {
    public void processJoin(String uname, String email, String colors) {
        System.out.println("user name: " + uname);
        System.out.println("email: " + email);
        System.out.println("color: " + colors);
    }
}