package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    {
        System.out.println("HomeController 생성됨!");
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/WEB-INF/jsp/index.jsp";
    }
}