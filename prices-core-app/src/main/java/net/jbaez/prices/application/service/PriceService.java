package net.jbaez.prices.application.service;

import lombok.RequiredArgsConstructor;
import net.jbaez.prices.domain.model.Price;
import net.jbaez.prices.domain.ports.in.GetPriceUseCase;
import net.jbaez.prices.domain.ports.out.PriceRepositoryPort;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Servicio de aplicacion para la gestion de precios.
 * Implementa el puerto de entrada para consultar precios.
 */
@RequiredArgsConstructor
public class PriceService implements GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Optional<Price> getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepositoryPort.findApplicablePrice(applicationDate, productId, brandId);
    }
}
