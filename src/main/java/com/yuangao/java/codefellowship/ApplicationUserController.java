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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

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
        return "myprofile";
    }

    @PostMapping("/users/{id}/followees")
    public String postFollowee(@PathVariable Long id, Principal p, Model m){
        ApplicationUser loginUser = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser newFollowee = applicationUserRepository.findById(id).get();

        loginUser.followees.add(newFollowee);
        applicationUserRepository.save(loginUser);
        Long loginUserId = loginUser.id;
        return "redirect:/users/" + loginUserId + "/followees";
    }

    @GetMapping("/users/{id}/followees")
    public String getFollowees(@PathVariable Long id, Model m){

        ApplicationUser loginUser = applicationUserRepository.findById(id).get();
        Iterable<ApplicationUser> followees = loginUser.followees;

        m.addAttribute("followees",followees);
        m.addAttribute("loginUser",loginUser);

        return "followees";
    }


    @GetMapping("/welcome")
    public String getWelcome(Principal p, Model m){
        m.addAttribute("principal",p);

        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("currentUser",currentUser);

        //get all the user and get rid of the current user, so I get all the potential friends
        Iterable<ApplicationUser> allUsers = applicationUserRepository.findAll();
        List<ApplicationUser> potentialFriends = new ArrayList<>();
        allUsers.forEach(potentialFriends::add);

        potentialFriends.remove(currentUser);
        //removeAll is important, remove doesn't work.
        potentialFriends.removeAll(currentUser.followees);

        m.addAttribute("potentialFriends", potentialFriends);

        return "welcome";
    }

    @PostMapping("/welcome")
    public RedirectView sendPost(Principal p, @RequestParam String body, Model m){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());

        Post post = new Post(user,body,new Timestamp(System.currentTimeMillis()));
        postRepository.save(post);

        return new RedirectView("/myprofile");
    }

    @GetMapping("/myprofile")
    public String getProfile(Principal p, Model m){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("user",user);
        return "myprofile";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }


}
