package com.srilankagem.gembackend.gem.service;

import com.srilankagem.gembackend.gem.dto.GemStoneResponse;
import com.srilankagem.gembackend.gem.models.GemStone;
import com.srilankagem.gembackend.gem.repository.GemStoneRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GemStoneService {

    private final GemStoneRepo gemStoneRepo;

    public Page<GemStoneResponse> getAllGemStones(Pageable pageable) {

//      return gemStoneRepo.findByActiveTrue(pageable).map(item ->toResponse(item));
        return gemStoneRepo.findByActiveTrue(pageable).map(this::toResponse);
    }

    private GemStoneResponse toResponse(GemStone gemStone) {
        return GemStoneResponse.builder()
                .id(gemStone.getId())
                .gemCode(gemStone.getGemCode())
                .type(gemStone.getType())
                .color(gemStone.getColor())
                .caratWeight(gemStone.getCaratWeight())
                .origin(gemStone.getOrigin())
                .treatment(gemStone.getTreatment())
                .pricePerCarat(gemStone.getPricePerCarat())
                .stockQuantity(gemStone.getStockQuantity())
                .description(gemStone.getDescription())
                .certified(gemStone.isCertified())
                .createdAt(gemStone.getCreatedAt())
                .updatedAt(gemStone.getUpdatedAt()).build();
    }
}
