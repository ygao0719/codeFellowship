package com.yuangao.java.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
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
    public String createUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio ){
        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio);
        applicationUserRepository.save(newUser);

        ApplicationUser user = applicationUserRepository.findByUsername(username);
        long id = user.getId();
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/users/" + id;
    }

    @GetMapping("/users/{id}")
    public String getProfile(@PathVariable Long id, Model m){
        ApplicationUser user = applicationUserRepository.findById(id).get();
        m.addAttribute("user",user);
        return "myProfile";
    }

    @GetMapping("/welcome")
    public String getProfile(Principal p, Model m){
        m.addAttribute("principal",p);
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("currentUser",currentUser);
        return "welcome";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }


}
