package net.jbaez.prices.infrastructure.jpa;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Configuracion minima para permitir la ejecucion de tests de persistencia (@DataJpaTest)
 * en el modulo de infraestructura JPA sin depender de prices-boot.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "net.jbaez.prices.infrastructure.jpa")
public class TestConfig {
}
