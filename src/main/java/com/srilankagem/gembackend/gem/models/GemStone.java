package com.srilankagem.gembackend.gem.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gemstones")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GemStone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false ,unique = true)
    private String gemCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GemType type;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Double caratWeight;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GemOrigin origin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GemTreatment treatment;

    @Column
    private Double pricePerCarat;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    @Builder.Default
    private boolean certified;

    @Column(nullable = false , updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
