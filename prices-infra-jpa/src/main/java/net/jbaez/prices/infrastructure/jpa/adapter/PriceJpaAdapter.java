package net.jbaez.prices.infrastructure.jpa.adapter;

import lombok.RequiredArgsConstructor;
import net.jbaez.prices.domain.model.Price;
import net.jbaez.prices.domain.ports.out.PriceRepositoryPort;
import net.jbaez.prices.infrastructure.jpa.mapper.PriceJpaMapper;
import net.jbaez.prices.infrastructure.jpa.repository.PriceJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia que implementa el puerto de salida para JPA.
 */
@Component
@RequiredArgsConstructor
public class PriceJpaAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository priceJpaRepository;

    private final PriceJpaMapper priceJpaMapper;

    @Override
    public List<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceJpaRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, applicationDate, applicationDate)
                .stream()
                .map(priceJpaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
