package com.routee.schedule.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "places")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "naver_place_id", nullable = false, unique = true)
    private Long naverPlaceId;

    @Column(name ="place_name", nullable = false, length = 150)
    private String placeName;

    @Column(length = 255)
    private String address;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(length = 100)
    private String category;

}
