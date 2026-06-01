package com.srilankagem.gembackend.dealer.service;

import com.srilankagem.gembackend.common.exception.DuplicateResourceException;
import com.srilankagem.gembackend.common.exception.ResourceNotFoundException;
import com.srilankagem.gembackend.dealer.dto.DealerRequest;
import com.srilankagem.gembackend.dealer.dto.DealerResponse;
import com.srilankagem.gembackend.dealer.models.Dealer;
import com.srilankagem.gembackend.dealer.models.DealerTier;
import com.srilankagem.gembackend.dealer.repository.DealerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealerService {

    private final DealerRepo dealerRepo;

    public DealerResponse createDealer(DealerRequest request) {

        if (dealerRepo.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "Dealer already exists with email: " + request.getEmail()
            );
        }

        Dealer dealer = Dealer.builder()
                .companyName(request.getCompanyName())
                .contactPerson(request.getContactPerson())
                .email(request.getEmail())
                .phone(request.getPhone())
                .country(request.getCountry())
                .shippingAddress(request.getShippingAddress())
                .dealerTier(request.getDealerTier())
                .build();

        return toResponse(dealerRepo.save(dealer));
    }

    public DealerResponse getDealerById(Long id) {

        Dealer dealer = dealerRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Dealer",
                                id.toString()
                        ));

        return toResponse(dealer);
    }

    public Page<DealerResponse> getAllDealers(
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return dealerRepo.findAll(pageable)
                .map(this::toResponse);
    }

    public Page<DealerResponse> getDealersByTier(
            DealerTier dealerTier,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return dealerRepo.findByDealerTier(
                        dealerTier,
                        pageable
                )
                .map(this::toResponse);
    }

    public DealerResponse updateDealer(
            Long id,
            DealerRequest request
    ) {

        Dealer dealer = dealerRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Dealer",
                                id.toString()
                        ));

        if (!dealer.getEmail().equals(request.getEmail())
                && dealerRepo.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    "Dealer already exists with email: "
                            + request.getEmail()
            );
        }

        dealer.setCompanyName(request.getCompanyName());
        dealer.setContactPerson(request.getContactPerson());
        dealer.setEmail(request.getEmail());
        dealer.setPhone(request.getPhone());
        dealer.setCountry(request.getCountry());
        dealer.setShippingAddress(request.getShippingAddress());
        dealer.setDealerTier(request.getDealerTier());

        return toResponse(dealerRepo.save(dealer));
    }

    private DealerResponse toResponse(Dealer dealer) {

        return DealerResponse.builder()
                .id(dealer.getId())
                .companyName(dealer.getCompanyName())
                .contactPerson(dealer.getContactPerson())
                .email(dealer.getEmail())
                .phone(dealer.getPhone())
                .country(dealer.getCountry())
                .shippingAddress(dealer.getShippingAddress())
                .dealerTier(dealer.getDealerTier())
                .createdAt(dealer.getCreatedAt())
                .updatedAt(dealer.getUpdatedAt())
                .build();
    }
}