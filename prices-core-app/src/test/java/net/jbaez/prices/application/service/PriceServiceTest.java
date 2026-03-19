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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceService priceService;

    @Test
    @DisplayName("Debe llamar al repositorio y devolver el precio encontrado")
    void getPrice_Found() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        Price expectedPrice = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .amount(new BigDecimal("35.50"))
                .build();

        when(priceRepositoryPort.findApplicablePrice(applicationDate, productId, brandId))
                .thenReturn(Optional.of(expectedPrice));

        // Act
        Optional<Price> result = priceService.getPrice(applicationDate, productId, brandId);

        // Assert
        assertThat(result).isPresent().contains(expectedPrice);
        verify(priceRepositoryPort).findApplicablePrice(applicationDate, productId, brandId);
    }

    @Test
    @DisplayName("Debe devolver Optional vacio cuando el repositorio no encuentra precio")
    void getPrice_NotFound() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceRepositoryPort.findApplicablePrice(applicationDate, productId, brandId))
                .thenReturn(Optional.empty());

        // Act
        Optional<Price> result = priceService.getPrice(applicationDate, productId, brandId);

        // Assert
        assertThat(result).isEmpty();
        verify(priceRepositoryPort).findApplicablePrice(applicationDate, productId, brandId);
    }
}
