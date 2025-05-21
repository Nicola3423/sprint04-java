package br.com.fiap.sprint03.controller;

import jakarta.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.GrantedAuthority;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/initial")
    public String initial(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "initial";
        }
        return "redirect:/";
    }
}

