package net.jbaez.prices.infrastructure.jpa.repository;

import net.jbaez.prices.infrastructure.jpa.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repositorio de Spring Data JPA para la entidad PriceEntity.
 */
@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Busca el primer registro que coincida con la marca, producto y rango de fechas,
     * ordenado por prioridad descendente para obtener el mas relevante.
     *
     * @param brandId Identificador de la cadena.
     * @param productId Identificador del producto.
     * @param date1 Fecha de aplicacion (para START_DATE).
     * @param date2 Fecha de aplicacion (para END_DATE).
     * @return Un {@link Optional} con el primer resultado encontrado.
     */
    Optional<PriceEntity> findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long brandId, Long productId, LocalDateTime date1, LocalDateTime date2);
}
