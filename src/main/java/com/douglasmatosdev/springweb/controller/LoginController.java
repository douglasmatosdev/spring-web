package com.douglasmatosdev.springweb.controller;

import com.douglasmatosdev.springweb.models.Administrator;
import com.douglasmatosdev.springweb.repository.AdministratorsRespository;
import com.douglasmatosdev.springweb.services.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

    @Autowired
    private AdministratorsRespository administratorsRespository;

    @GetMapping("/login")
    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        String key = "error";
        String errorMessage = CookieService.getCookie(request, key);

        if (errorMessage != null) {
            model.addAttribute(key, errorMessage);
        }
        return "login/index";
    }

    @PostMapping("/signin")
    public String signin(Model model, Administrator administrator, String remember, HttpServletResponse response) throws IOException {
        int id = administrator.getId();
        String email = administrator.getEmail();
        String name = !email.contains("@") ? email : administrator.getName();
        String password = administrator.getPassword();

        Administrator admin = administratorsRespository.Login(name, email, password);

        if (admin != null) {
            int loggedTime = (60 * 60); // 1 hour

            if (remember != null) {
                loggedTime = (60 * 60 * 24 * 365); // 1 year
            }

            CookieService.setCookie(response, "userId", String.valueOf(id), loggedTime);
            CookieService.setCookie(response, "userName", String.valueOf(name), loggedTime);
            CookieService.setCookie(response, "error", "", 0);
            return "redirect:/";
        }
        CookieService.setCookie(response, "error", "User or password invalid!", (60 * 60));
//        model.addAttribute("error", "User or password invalid!");

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) throws IOException {
        CookieService.setCookie(response, "userId", "", 0);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(){
        return "register/index";
    }
}
