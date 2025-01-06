package hft.matthew.LibraryManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // Ensure this doesn't redirect to itself
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Ensure this doesn't redirect to itself
    }

    @GetMapping("/home")
    public String homePage() {
        return "home"; // Ensure this doesn't redirect to itself
    }
}
