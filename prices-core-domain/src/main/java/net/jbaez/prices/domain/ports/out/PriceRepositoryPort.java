package net.jbaez.prices.domain.ports.out;

import net.jbaez.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Puerto de salida para interactuar con la persistencia de precios.
 */
public interface PriceRepositoryPort {

    /**
     * Busca los precios candidatos para una fecha, producto y cadena dados.
     *
     * @param applicationDate Fecha para la que se consulta el precio.
     * @param productId Identificador del producto.
     * @param brandId Identificador de la cadena.
     * @return Lista de precios candidatos aplicables.
     */
    List<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId);
}
