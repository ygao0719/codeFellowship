package com.yuangao.java.codefellowship;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CodeFellowshipController {

    @GetMapping("/")
    public String getCode(){
        return "codeFellowship";
    }
}
