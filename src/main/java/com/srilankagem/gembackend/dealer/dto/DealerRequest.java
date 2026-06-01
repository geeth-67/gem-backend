package com.srilankagem.gembackend.dealer.dto;

import com.srilankagem.gembackend.dealer.models.DealerTier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealerRequest {

    @NotBlank(message = "Company name is required")
    @Size(max = 255, message = "Company name cannot exceed 255 characters")
    private String companyName;

    @NotBlank(message = "Contact person is required")
    @Size(max = 255, message = "Contact person cannot exceed 255 characters")
    private String contactPerson;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    private String phone;

    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country cannot exceed 100 characters")
    private String country;

    @Size(max = 500, message = "Shipping address cannot exceed 500 characters")
    private String shippingAddress;

    @NotNull(message = "Dealer tier is required")
    private DealerTier dealerTier;
}