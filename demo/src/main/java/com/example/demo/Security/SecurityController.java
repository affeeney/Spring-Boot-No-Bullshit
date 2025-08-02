package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/open")
    public String open() {
        return "No Login Necessary";
    }

    @GetMapping("/closed")
    public String closed(){
        return "Must Login";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/special")
    public String special() {
        return "SPECIAL";
    }

    @PreAuthorize("hasRole('ADMIN') or harRole('USER')")
    @GetMapping("/basic")
    public String basic() {
        return "BASIC";
    }


}
