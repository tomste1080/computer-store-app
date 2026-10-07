package com.pl.computer_store_app.cart;

import com.pl.computer_store_app.cart.dto.CartDto;
import com.pl.computer_store_app.product.Product;
import com.pl.computer_store_app.product.ProductRepository;
import com.pl.computer_store_app.user.User;
import com.pl.computer_store_app.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void addToCart(Long userId, Long productId) {
        Optional<Cart> cartProducts = cartRepository.findByUserIdAndProductId(userId, productId);
        if (cartProducts.isPresent()) {
            Cart cartItem = cartProducts.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartRepository.save(cartItem);
        } else {
            User user = userRepository.findUserById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Nie można zanleźć użytkownika o takim numerze ID"));
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Nie można znaleźć produktu o danym identyfikatorze"));
            Cart newCartItem = new Cart();
            newCartItem.setUser(user);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(1);
            newCartItem.setCreatedAt(LocalDateTime.now());
            cartRepository.save(newCartItem);
        }
    }

    public List<CartDto> getCarts(Long userId) {
        return cartRepository.findByUserId(userId)
                .stream()
                .map(CartDtoMapper::map)
                .toList();
    }

    public BigDecimal countProductPrice(Long userId) {
        List<Cart> items = cartRepository.findByUserId(userId);
        return items.stream()
                .map(cart -> cart.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(cart.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal countTotalPrice(Long userId) {
        BigDecimal totalPrice = getCarts(userId)
                .stream()
                .map(cart -> cart.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(cart.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalPrice.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal deliveryFee = BigDecimal.valueOf(15.00);
        return totalPrice.add(deliveryFee);
    }
}
