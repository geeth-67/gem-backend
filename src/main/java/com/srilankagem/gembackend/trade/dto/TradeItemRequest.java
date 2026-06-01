package com.srilankagem.gembackend.trade.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeItemRequest {

    @NotNull(message = "Gem ID is required")
    private Long gemId;

    @NotNull
    @Min(value = 1 , message = "Quantity must be at least 1")
    private Integer quantity;
}
