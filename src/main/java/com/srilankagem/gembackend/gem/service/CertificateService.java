package com.srilankagem.gembackend.gem.service;

import com.srilankagem.gembackend.common.exception.DuplicateResourceException;
import com.srilankagem.gembackend.gem.dto.CertificateRequest;
import com.srilankagem.gembackend.gem.repository.CertificateRepo;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {

    private final CertificateRepo certificateRepo;

    @Autowired
    public CertificateService(CertificateRepo certificateRepo) {
        this.certificateRepo = certificateRepo;
    }

    public CertificateRepo createCertificate(CertificateRequest request) {

        if (certificateRepo.existsByCertificateNumber(request.getCertificateNumber())) {
            throw new DuplicateResourceException("Certificate with" + request.getCertificateNumber() + "this number already exists");
        }
        if (certificateRepo.existsByGemStoneId(request.getGemId())) {
            throw new DuplicateResourceException("Certificate with" + request.getGemId() + "already exists");
        }
        return null;
    }
}
