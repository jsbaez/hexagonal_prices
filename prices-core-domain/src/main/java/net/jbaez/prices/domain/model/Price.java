package net.jbaez.prices.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad de dominio que representa el precio de un producto para una cadena y rango de fechas.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private Long brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer priceList;

    private Long productId;

    private Integer priority;

    /**
     * Precio final a aplicar.
     */
    private BigDecimal price;

    private String currency;
}
