package com.srilankagem.gembackend.trade.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeItemResponse {

    private Long id;
    private Long gemId;
    private String gemCode;
    private String gemType;
    private BigDecimal caratWeight;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;
}
