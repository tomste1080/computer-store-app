package com.pl.computer_store_app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @GetMapping("/koszyk/kasa")
    public String getPaymentMethod(Model model) {
        model.addAttribute("success", "Płatność przebiegła pomyślnie, twoje zamówienie jest w realizacji.");
        return "payment";
    }
}
