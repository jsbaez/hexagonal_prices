package net.jbaez.prices.infrastructure.jpa.mapper;

import net.jbaez.prices.domain.model.Price;
import net.jbaez.prices.infrastructure.jpa.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapeador de MapStruct para convertir entre PriceEntity y el dominio Price.
 */
@Mapper(componentModel = "spring")
public interface PriceJpaMapper {

    /**
     * Convierte una entidad JPA a un objeto de dominio.
     *
     * @param entity Entidad de base de datos.
     * @return Objeto de dominio.
     */
    Price toDomain(PriceEntity entity);

    /**
     * Convierte un objeto de dominio a una entidad JPA.
     *
     * @param domain Objeto de dominio.
     * @return Entidad JPA.
     */
    @Mapping(target = "id", ignore = true)
    PriceEntity toEntity(Price domain);
}
