package com.pl.computer_store_app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    @GetMapping("/kontakt")
    public String getContact(Model model) {
        return "contact-page";
    }

    @PostMapping("/kontakt/message")
    public String getMessage(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", "Wiadomość została wysłana");
        return "redirect:/kontakt";
    }
}
