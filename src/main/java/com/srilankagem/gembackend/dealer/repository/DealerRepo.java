package com.srilankagem.gembackend.dealer.repository;

import com.srilankagem.gembackend.dealer.models.Dealer;
import com.srilankagem.gembackend.dealer.models.DealerTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface DealerRepo extends JpaRepository<Dealer, Long> {

    Optional<Dealer> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<Dealer> findByDealerTier(DealerTier dealerTier, Pageable pageable);
}