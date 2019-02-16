package controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ComponentScan("application")
public class MainController {

    @Resource
    private AuthenticationManager authManager;

    private String user = "admin";
    private String pass = "root";

    @GetMapping("/login")
    @ResponseBody
    public String performLogin(
            @RequestParam("login") String username,
            @RequestParam("password") String password) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication auth = authManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "{\"status\": true}";
        } catch (BadCredentialsException ex) {
            return "{\"status\": false, \"error\": \"Bad Credentials\"}";
        }
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
