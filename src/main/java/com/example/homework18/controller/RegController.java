package com.example.homework18.controller;

import com.example.homework18.model.UserPass;
import com.example.homework18.model.UserPassDto;
import com.example.homework18.service.UserPassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
//@RequiredArgsConstructor
//@RequestMapping("/")
public class RegController {

    private final UserPassService userPassService;

    public RegController(UserPassService userPassService) {
        this.userPassService = userPassService;
    }

    @GetMapping({"/startpage"})
    public String login(Model model) {
        return "startpage";
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        System.out.println("12321");
        model.addAttribute("userForm", new UserPass());
        return "registration";
    }
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") UserPassDto user, BindingResult result,
                               Model model) {

        UserPass existing = userPassService.findByUsername(user.getUsername());
        if (existing != null) {
            result.rejectValue("username", null, "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            model.addAttribute("userpassdto", user);
            return "registration";
        }
        userPassService.creatUser(user);
        System.out.println("method post open");
        return "redirect:/registration";
    }



}
