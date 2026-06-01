package com.srilankagem.gembackend.dealer.models;

import com.srilankagem.gembackend.trade.entity.Trade;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dealers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String contactPerson;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String country;

    @Column(length = 500)
    private String shippingAddress;

    @OneToMany(mappedBy = "dealer" , cascade = CascadeType.ALL , orphanRemoval = true)
    @Builder.Default
    private List<Trade> trades = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DealerTier dealerTier;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}