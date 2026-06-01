package com.srilankagem.gembackend.trade.repository;

import com.srilankagem.gembackend.trade.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade , Long> {


}
