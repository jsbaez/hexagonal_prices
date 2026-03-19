package net.jbaez.prices.infrastructure.rest.mapper;

import net.jbaez.prices.domain.model.Price;
import net.jbaez.prices.infrastructure.rest.dto.PriceResponse;
import org.mapstruct.Mapper;

/**
 * Mapeador de MapStruct para convertir entre el dominio Price y PriceResponse.
 */
@Mapper(componentModel = "spring")
public interface PriceRestMapper {

    /**
     * Convierte un objeto de dominio a una respuesta del API REST.
     *
     * @param domain Objeto de dominio.
     * @return Respuesta para el API.
     */
    PriceResponse toResponse(Price domain);
}
