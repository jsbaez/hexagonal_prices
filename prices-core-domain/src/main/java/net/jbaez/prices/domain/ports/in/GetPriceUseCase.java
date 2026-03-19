package net.jbaez.prices.domain.ports.in;

import net.jbaez.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Puerto de entrada para el caso de uso de consulta de precios.
 */
public interface GetPriceUseCase {

    /**
     * Obtiene el precio aplicable para una fecha, producto y cadena especificos.
     *
     * @param applicationDate Fecha de aplicacion de la tarifa.
     * @param productId Identificador del producto.
     * @param brandId Identificador de la cadena.
     * @return Un {@link Optional} con el precio aplicable, o vacio si no se encuentra.
     */
    Optional<Price> getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
