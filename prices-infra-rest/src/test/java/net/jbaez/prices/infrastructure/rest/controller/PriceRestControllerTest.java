package net.jbaez.prices.infrastructure.rest.controller;

import net.jbaez.prices.domain.model.Price;
import net.jbaez.prices.domain.ports.in.GetPriceUseCase;
import net.jbaez.prices.infrastructure.rest.dto.PriceResponse;
import net.jbaez.prices.infrastructure.rest.mapper.PriceRestMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceRestController.class)
class PriceRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetPriceUseCase getPriceUseCase;

    @MockitoBean
    private PriceRestMapper priceRestMapper;

    @Test
    @DisplayName("Debe retornar 200 y el precio cuando existe una tarifa aplicable")
    void getPrice_Success() throws Exception {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        Price price = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .amount(new BigDecimal("35.50"))
                .currency("EUR")
                .build();

        PriceResponse response = new PriceResponse(productId, brandId, 1, applicationDate, applicationDate, new BigDecimal("35.50"), "EUR");

        when(getPriceUseCase.getPrice(any(), eq(productId), eq(brandId))).thenReturn(Optional.of(price));
        when(priceRestMapper.toResponse(any())).thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T10:00:00")
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.amount").value(35.50));
    }

    @Test
    @DisplayName("Debe retornar 404 cuando no existe tarifa aplicable")
    void getPrice_NotFound() throws Exception {
        // Arrange
        when(getPriceUseCase.getPrice(any(), any(), any())).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T10:00:00")
                .param("productId", "999")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
