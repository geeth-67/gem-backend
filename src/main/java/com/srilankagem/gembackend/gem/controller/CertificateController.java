package com.srilankagem.gembackend.gem.controller;
import com.srilankagem.gembackend.gem.dto.CertificateRequest;
import com.srilankagem.gembackend.gem.dto.CertificateResponse;
import com.srilankagem.gembackend.gem.service.CertificateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/certificate")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    public ResponseEntity<CertificateResponse> createCertificate(@Valid @RequestBody CertificateRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(certificateService.createCertificate(request));
    }
}
