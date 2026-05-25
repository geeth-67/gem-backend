package com.srilankagem.gembackend.gem.repository;

import com.srilankagem.gembackend.gem.models.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificateRepo extends JpaRepository<Certificate , Long> {

    Optional<Certificate> findByGemStoneId(Long gemStoneId);
    boolean existsByGemStoneId(Long gemStoneId);

    boolean existsByCertificateNumber(String certificateNumber);
}
