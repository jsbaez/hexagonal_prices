package net.jbaez.prices.infrastructure.rest.controller;

import lombok.RequiredArgsConstructor;
import net.jbaez.prices.domain.ports.in.GetPriceUseCase;
import net.jbaez.prices.infrastructure.rest.dto.PriceResponse;
import net.jbaez.prices.infrastructure.rest.mapper.PriceRestMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controlador REST para la consulta de precios.
 */
@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class PriceRestController {

    private final GetPriceUseCase getPriceUseCase;
    private final PriceRestMapper priceRestMapper;

    /**
     * Endpoint GET para consultar el precio aplicable de un producto en una cadena.
     * Ejemplo: GET /brands/1/products/35455/prices?applicationDate=2020-06-14T10:00:00
     *
     * @param brandId Identificador de la marca/cadena.
     * @param productId Identificador del producto.
     * @param applicationDate Fecha de aplicacion (ISO 8601).
     * @return El precio encontrado o 404 si no existe.
     */
    @GetMapping("/{brandId}/products/{productId}/prices")
    public ResponseEntity<PriceResponse> getPrice(
            @PathVariable("brandId") Long brandId,
            @PathVariable("productId") Long productId,
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

        return getPriceUseCase.getPrice(applicationDate, productId, brandId)
                .map(priceRestMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
