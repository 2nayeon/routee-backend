package com.routee.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceRequestDto {
    private Long naverPlaceId;
    private String placeName;
    private String address;
    private Double latitude;
    private Double longitude;
    private String category;
}
