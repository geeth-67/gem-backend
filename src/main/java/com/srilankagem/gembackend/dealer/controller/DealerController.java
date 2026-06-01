package com.srilankagem.gembackend.dealer.controller;

import com.srilankagem.gembackend.dealer.dto.DealerRequest;
import com.srilankagem.gembackend.dealer.dto.DealerResponse;
import com.srilankagem.gembackend.dealer.models.DealerTier;
import com.srilankagem.gembackend.dealer.service.DealerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dealers")
@RequiredArgsConstructor
public class DealerController {

    private final DealerService dealerService;

    @PostMapping
    public ResponseEntity<DealerResponse> createDealer(
            @Valid @RequestBody DealerRequest request
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dealerService.createDealer(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealerResponse> getDealerById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                dealerService.getDealerById(id)
        );
    }

    @GetMapping
    public ResponseEntity<Page<DealerResponse>> getAllDealers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return ResponseEntity.ok(
                dealerService.getAllDealers(page, size)
        );
    }

    @GetMapping("/tier/{dealerTier}")
    public ResponseEntity<Page<DealerResponse>> getDealersByTier(
            @PathVariable DealerTier dealerTier,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return ResponseEntity.ok(
                dealerService.getDealersByTier(
                        dealerTier,
                        page,
                        size
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealerResponse> updateDealer(
            @PathVariable Long id,
            @Valid @RequestBody DealerRequest request
    ) {

        return ResponseEntity.ok(
                dealerService.updateDealer(id, request)
        );
    }
}