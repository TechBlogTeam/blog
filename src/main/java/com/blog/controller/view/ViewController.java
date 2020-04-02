package com.blog.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {
    @GetMapping({"/", "/index", "index.html"})
    public String index(HttpServletRequest request){
        return "views/index";
    }
}
