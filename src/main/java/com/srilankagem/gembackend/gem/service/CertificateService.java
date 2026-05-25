package com.srilankagem.gembackend.gem.service;

import com.srilankagem.gembackend.common.exception.DuplicateResourceException;
import com.srilankagem.gembackend.gem.dto.CertificateRequest;
import com.srilankagem.gembackend.gem.repository.CertificateRepo;
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
            throw new DuplicateResourceException("Certificate with this number already exists");
        }
    }
}
