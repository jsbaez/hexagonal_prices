package net.jbaez.prices.infrastructure.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Record que representa la respuesta del API de precios.
 */
public record PriceResponse(
        Long productId,
        Long brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal amount,
        String currency
) {
}
