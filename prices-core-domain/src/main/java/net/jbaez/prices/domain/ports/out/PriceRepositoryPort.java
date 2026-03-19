package net.jbaez.prices.domain.ports.out;

import net.jbaez.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Puerto de salida para interactuar con la persistencia de precios.
 */
public interface PriceRepositoryPort {

    /**
     * Busca el precio aplicable con mayor prioridad en una fecha, producto y cadena dados.
     *
     * @param applicationDate Fecha para la que se consulta el precio.
     * @param productId Identificador del producto.
     * @param brandId Identificador de la cadena.
     * @return El precio de mayor prioridad aplicable, o vacio si no hay ninguno.
     */
    Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
