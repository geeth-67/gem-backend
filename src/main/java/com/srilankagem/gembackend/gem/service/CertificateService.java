package com.srilankagem.gembackend.gem.service;

import com.srilankagem.gembackend.common.exception.DuplicateResourceException;
import com.srilankagem.gembackend.common.exception.ResourceNotFoundException;
import com.srilankagem.gembackend.gem.dto.CertificateRequest;
import com.srilankagem.gembackend.gem.dto.CertificateResponse;
import com.srilankagem.gembackend.gem.dto.GemStoneResponse;
import com.srilankagem.gembackend.gem.models.Certificate;
import com.srilankagem.gembackend.gem.models.GemStone;
import com.srilankagem.gembackend.gem.repository.CertificateRepo;
import com.srilankagem.gembackend.gem.repository.GemStoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {

    private final CertificateRepo certificateRepo;
    private final GemStoneRepo gemStoneRepo;

    @Autowired
    public CertificateService(CertificateRepo certificateRepo , GemStoneRepo gemStoneRepo, GemStoneRepo gemStoneRepo1) {
        this.certificateRepo = certificateRepo;
        this.gemStoneRepo = gemStoneRepo;
    }

    public CertificateResponse createCertificate(CertificateRequest request) {

        if (certificateRepo.existsByCertificateNumber(request.getCertificateNumber())) {
            throw new DuplicateResourceException(
                    "Certificate with number " +
                            request.getCertificateNumber() +
                            " already exists");
        }

        if (certificateRepo.existsByGemStoneId(request.getGemId())) {
            throw new DuplicateResourceException(
                    "Certificate for gem " +
                            request.getGemId() +
                            " already exists");
        }

        GemStone stone = gemStoneRepo.findById(request.getGemId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Gem",
                                request.getGemId().toString()
                        )
                );

        Certificate certificate = Certificate.builder()
                .certificateNumber(request.getCertificateNumber())
                .gemStone(stone)
                .issuedBy(request.getIssuedBy())
                .issuedDate(request.getIssueDate())
                .expiryDate(request.getExpiryDate())
                .remarks(request.getRemarks())
                .build();

        return toResponse(certificateRepo.save(certificate));
    }

    private CertificateResponse toResponse(Certificate cert) {
        return CertificateResponse
                .builder()
                .id(cert.getId())
                .certificateNumber(cert.getCertificateNumber())
                .gemId(cert.getGemStone().getId())
                .gemCode(cert.getGemStone().getGemCode())
                .issuedBy(cert.getIssuedBy())
                .issuedDate(cert.getIssuedDate())
                .expiryDate(cert.getExpiryDate())
                .remarks(cert.getRemarks())
                .createdAt(cert.getCreatedAt())
                .build();
    }

    public CertificateResponse getCertificateById(Long id) throws ResourceNotFoundException {
        return toResponse(certificateRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("certificate" , id.toString())
        ));
    }
}
