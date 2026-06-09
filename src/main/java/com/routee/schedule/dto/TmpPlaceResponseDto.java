package com.routee.schedule.dto;

import com.routee.schedule.entity.ScheduleTmpPlace;
import lombok.Getter;

@Getter
public class TmpPlaceResponseDto {
    private Long tmpPlaceId;
    private Long placeId;
    private Long naverPlaceId;
    private String placeName;
    private String address;
    private String category;

    public TmpPlaceResponseDto(ScheduleTmpPlace tmpPlace) {
        this.tmpPlaceId = tmpPlace.getTmpPlaceId();
        this.placeId = tmpPlace.getPlace().getPlaceId();
        this.naverPlaceId = tmpPlace.getPlace().getNaverPlaceId();
        this.placeName = tmpPlace.getPlace().getPlaceName();
        this.address = tmpPlace.getPlace().getAddress();
        this.category = tmpPlace.getPlace().getCategory();
    }
}
