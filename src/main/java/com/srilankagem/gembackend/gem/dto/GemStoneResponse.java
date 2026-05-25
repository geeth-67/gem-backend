package com.srilankagem.gembackend.gem.dto;

import com.srilankagem.gembackend.gem.models.GemOrigin;
import com.srilankagem.gembackend.gem.models.GemTreatment;
import com.srilankagem.gembackend.gem.models.GemType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GemStoneResponse {

    private Long id;
    private String gemCode;
    private GemType type;
    private String color;
    private BigDecimal caratWeight;
    private GemOrigin origin;
    private GemTreatment treatment;
    private BigDecimal pricePerCarat;
    private Integer stockQuantity;
    private boolean certified;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
