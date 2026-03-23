package net.jbaez.prices.application.service;

import net.jbaez.prices.domain.model.Price;
import net.jbaez.prices.domain.ports.out.PriceRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias para el servicio PriceService.
 * Verifica la logica de negocio de seleccion de precios por prioridad.
 */
@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceService priceService;

    @Test
    @DisplayName("Debe seleccionar el precio con mayor prioridad cuando hay multiples candidatos")
    void getPrice_HighestPriority() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        Price lowPriorityPrice = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .build();

        Price highPriorityPrice = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .priority(1)
                .price(new BigDecimal("25.45"))
                .build();

        when(priceRepositoryPort.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of(lowPriorityPrice, highPriorityPrice));

        // Act
        Optional<Price> result = priceService.getPrice(date, productId, brandId);

        // Assert
        assertThat(result).isPresent().contains(highPriorityPrice);
        assertThat(result.get().getPriority()).isEqualTo(1);
        verify(priceRepositoryPort).findApplicablePrices(date, productId, brandId);
    }

    @Test
    @DisplayName("Debe devolver el unico precio disponible cuando solo hay un candidato")
    void getPrice_SingleCandidate() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        Price expectedPrice = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .build();

        when(priceRepositoryPort.findApplicablePrices(date, productId, brandId))
                .thenReturn(List.of(expectedPrice));

        // Act
        Optional<Price> result = priceService.getPrice(date, productId, brandId);

        // Assert
        assertThat(result).isPresent().contains(expectedPrice);
        verify(priceRepositoryPort).findApplicablePrices(date, productId, brandId);
    }

    @Test
    @DisplayName("Debe devolver Optional vacio cuando no hay candidatos")
    void getPrice_NoCandidates() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceRepositoryPort.findApplicablePrices(date, productId, brandId))
                .thenReturn(Collections.emptyList());

        // Act
        Optional<Price> result = priceService.getPrice(date, productId, brandId);

        // Assert
        assertThat(result).isEmpty();
        verify(priceRepositoryPort).findApplicablePrices(date, productId, brandId);
    }
}
