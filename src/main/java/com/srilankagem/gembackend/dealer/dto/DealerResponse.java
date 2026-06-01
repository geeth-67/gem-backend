package com.srilankagem.gembackend.dealer.dto;

import com.srilankagem.gembackend.dealer.models.DealerTier;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealerResponse {

    private Long id;
    private String companyName;
    private String contactPerson;
    private String email;
    private String phone;
    private String country;
    private String shippingAddress;
    private DealerTier dealerTier;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}