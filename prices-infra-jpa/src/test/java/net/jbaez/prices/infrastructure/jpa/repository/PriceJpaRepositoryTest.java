package net.jbaez.prices.infrastructure.jpa.repository;

import net.jbaez.prices.infrastructure.jpa.entity.PriceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PriceJpaRepositoryTest {

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @Test
    @DisplayName("Debe devolver el precio con MAYOR prioridad cuando coinciden las fechas")
    void findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_Success() {
        // Arrange
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 16, 0);

        PriceEntity lowPriority = PriceEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59))
                .priceList(1)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();

        PriceEntity highPriority = PriceEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30))
                .priceList(2)
                .priority(1)
                .price(new BigDecimal("25.45"))
                .currency("EUR")
                .build();

        priceJpaRepository.save(lowPriority);
        priceJpaRepository.save(highPriority);

        // Act
        Optional<PriceEntity> result = priceJpaRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, testDate, testDate);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getPriority()).isEqualTo(1);
        assertThat(result.get().getPriceList()).isEqualTo(2);
        assertThat(result.get().getPrice()).isEqualByComparingTo("25.45");
    }

    @Test
    @DisplayName("Debe devolver vacio cuando no hay precios en el rango de fechas")
    void findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_NotFound() {
        // Arrange
        LocalDateTime testDate = LocalDateTime.of(2025, 1, 1, 0, 0);

        // Act
        Optional<PriceEntity> result = priceJpaRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                1L, 35455L, testDate, testDate);

        // Assert
        assertThat(result).isEmpty();
    }
}
