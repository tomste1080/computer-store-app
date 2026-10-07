package com.pl.computer_store_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ComputerStoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputerStoreAppApplication.class, args);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("NOWY_HASH_ADMIN: " + encoder.encode("admin123"));
	}

}
