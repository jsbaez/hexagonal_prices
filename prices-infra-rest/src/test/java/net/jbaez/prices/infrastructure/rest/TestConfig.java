package net.jbaez.prices.infrastructure.rest;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "net.jbaez.prices.infrastructure.rest.controller")
public class TestConfig {
}
