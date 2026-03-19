package net.jbaez.prices.boot.config;

import net.jbaez.prices.application.service.PriceService;
import net.jbaez.prices.domain.ports.in.GetPriceUseCase;
import net.jbaez.prices.domain.ports.out.PriceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de beans para la capa de aplicacion.
 */
@Configuration
public class PricesConfig {

    /**
     * Crea el bean del caso de uso para consultar precios.
     *
     * @param priceRepositoryPort Adaptador de persistencia.
     * @return Implementacion del servicio de precios.
     */
    @Bean
    public GetPriceUseCase getPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        return new PriceService(priceRepositoryPort);
    }
}
