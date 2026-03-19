package net.jbaez.prices.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase principal para lanzar la aplicacion Spring Boot.
 */
@SpringBootApplication
@ComponentScan(basePackages = "net.jbaez.prices")
@EnableJpaRepositories(basePackages = "net.jbaez.prices.infrastructure.jpa.repository")
@EntityScan(basePackages = "net.jbaez.prices.infrastructure.jpa.entity")
public class PricesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricesApplication.class, args);
    }
}
