package com.srilankagem.gembackend.gem.service;

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
}
