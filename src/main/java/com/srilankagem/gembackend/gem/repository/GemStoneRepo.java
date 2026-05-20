package com.srilankagem.gembackend.gem.repository;

import com.srilankagem.gembackend.gem.models.GemStone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GemStoneRepo extends JpaRepository<GemStone , Long> {

    Page<GemStone> findByActiveTrue(Pageable pageable);
}
