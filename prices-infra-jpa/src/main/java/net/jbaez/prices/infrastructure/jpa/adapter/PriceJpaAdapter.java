package net.jbaez.prices.infrastructure.jpa.adapter;

import lombok.RequiredArgsConstructor;
import net.jbaez.prices.domain.model.Price;
import net.jbaez.prices.domain.ports.out.PriceRepositoryPort;
import net.jbaez.prices.infrastructure.jpa.mapper.PriceJpaMapper;
import net.jbaez.prices.infrastructure.jpa.repository.PriceJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Adaptador de salida para la persistencia de precios usando JPA.
 */
@Component
@RequiredArgsConstructor
public class PriceJpaAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository priceJpaRepository;
    private final PriceJpaMapper priceJpaMapper;

    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceJpaRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate)
                .map(priceJpaMapper::toDomain);
    }
}
