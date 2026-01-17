package com.example.taskflow.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class Main{
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("password123"));
    }



}