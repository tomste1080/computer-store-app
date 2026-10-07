package com.pl.computer_store_app.web;

import com.pl.computer_store_app.cart.CartService;
import com.pl.computer_store_app.cart.dto.CartDto;
import com.pl.computer_store_app.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/koszyk")
    public String getCart(Model model, Authentication authentication) {
        String email = authentication.getName();
        Long userId = userService.findUserByEmail(email).getId();
        List<CartDto> cartProducts = cartService.getCarts(userId);
        BigDecimal productPrice = cartService.countProductPrice(userId);
        BigDecimal totalPrice = cartService.countTotalPrice(userId);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("productPrice", productPrice);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @PostMapping("/koszyk")
    public String addToCart(@RequestParam Long productId, Authentication authentication) {
        String email = authentication.getName();
        Long userId = userService.findUserByEmail(email).getId();
        cartService.addToCart(userId, productId);
        return "redirect:/koszyk";
    }

    @PostMapping("/koszyk/usun")
    public String deleteFromCart(@RequestParam Long id) {
        cartService.deleteFromCart(id);
        return "redirect:/koszyk";
    }

    @PostMapping("/koszyk/kasa")
    public String payForProduct(Authentication authentication, Model model) {
        String email = authentication.getName();
        Long userId = userService.findUserByEmail(email).getId();
        cartService.payForProducts(userId);
        model.addAttribute("success", "Płatność przebiegła pomyślnie, twoje zamówienie jest w realizacji.");
        return "payment";
    }

    @PostMapping("/koszyk/zwieksz")
    public String increaseAmountOfProduct(@RequestParam Long id) {
        cartService.increaserQuantity(id);
        return "redirect:/koszyk";
    }
}
