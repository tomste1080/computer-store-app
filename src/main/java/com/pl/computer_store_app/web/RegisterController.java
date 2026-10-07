package com.pl.computer_store_app.web;

import com.pl.computer_store_app.address.Address;
import com.pl.computer_store_app.address.dto.AddressDto;
import com.pl.computer_store_app.user.User;
import com.pl.computer_store_app.user.UserRepository;
import com.pl.computer_store_app.user.UserRole;
import com.pl.computer_store_app.user.UserService;
import com.pl.computer_store_app.user.dto.UserDto;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/rejestracja")
    public String getForm(Model model) {
        User user = new User();
        user.setAddress(new Address());
        model.addAttribute("user", user);
        return "register-form";
    }

    @PostMapping("/rejestracja")
    public String getForm(
            @ModelAttribute("user") User user,
            @RequestParam String secPassword,
            Model model
    ) {
        if (!secPassword.equals(user.getPassword())) {
            model.addAttribute("error", "Hasła nie są identyczne");
            return "register-form";
        }
        if (userService.existUserByEmail(user.getEmail())) {
            model.addAttribute("repeat", "Użytkownik o takim email jest już zarejestrowany");
            return "register-form";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.ROLE_CUSTOMER);
        userRepository.save(user);
        return "redirect:/login?registered";
    }
}
