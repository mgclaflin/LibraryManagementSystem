package hft.matthew.LibraryManagementSystem.controller;

import hft.matthew.LibraryManagementSystem.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String homePage(Authentication authentication) {
        if (authentication != null) {
            // Get the logged-in user
            try{
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String role = userDetails.getAuthorities().toString(); // Get the user's roles

                // Check role and route to the correct page
                if (role.contains("ROLE_ADMIN")) {
                    return "adminHome";  // Return the admin home page
                } else if (role.contains("ROLE_LIBRARIAN")) {
                    return "librarianHome";  // Return the librarian home page
                } else if (role.contains("ROLE_MEMBER")) {
                    return "memberHome";  // Return the member home page
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        // Default page for unauthorized users
        return "home";
    }
}
