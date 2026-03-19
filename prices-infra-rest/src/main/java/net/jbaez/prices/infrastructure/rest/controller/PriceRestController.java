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

/**
 * Controlador REST para la consulta de precios.
 */
@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceRestController {

    private final GetPriceUseCase getPriceUseCase;
    private final PriceRestMapper priceRestMapper;

    /**
     * Endpoint GET para consultar el precio aplicable.
     *
     * @param applicationDate Fecha de aplicacion (ISO 8601).
     * @param productId Identificador del producto.
     * @param brandId Identificador de la marca/cadena.
     * @return El precio encontrado o 404 si no existe.
     */
    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {

        return getPriceUseCase.getPrice(applicationDate, productId, brandId)
                .map(priceRestMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
