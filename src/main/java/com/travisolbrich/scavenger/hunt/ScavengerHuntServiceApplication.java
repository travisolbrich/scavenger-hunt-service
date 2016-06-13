package com.travisolbrich.scavenger.hunt;

import com.travisolbrich.scavenger.hunt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@ImportResource("classpath:security.xml")
@PropertySource("classpath:auth0.properties")
@ComponentScan
public class ScavengerHuntServiceApplication {

public static void main(String[] args) {
		SpringApplication.run(ScavengerHuntServiceApplication.class, args);
	}
}
