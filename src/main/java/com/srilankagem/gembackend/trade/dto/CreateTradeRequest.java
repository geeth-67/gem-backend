package com.srilankagem.gembackend.trade.dto;

import com.srilankagem.gembackend.trade.entity.Trade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTradeRequest {

    @NotNull(message = "Dealer id is required")
    private Long dealer_id;

    @NotEmpty(message = "Trade must have at least on gem")
    @Valid
    private List<Trade> items;

    @Size(max = 200 , message = "Shipping address cannot exceed 500 characters")
    private String shippingAddress;

    @Size(max = 500 , message = "Notes cannot exceed 500 characters")
    private String notes;
}
