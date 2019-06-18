package com.yuangao.java.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }


    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio ){
        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio);
        applicationUserRepository.save(newUser);


        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/signup");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}
