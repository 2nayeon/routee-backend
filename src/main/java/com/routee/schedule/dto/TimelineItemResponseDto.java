package com.routee.schedule.dto;

import com.routee.schedule.entity.TimelineItem;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class TimelineItemResponseDto {
    private Long timelineItemId;
    private Long placeId;
    private String placeName;
    private String address;
    private String category;
    private Integer visitOrder;
    private LocalTime visitTime;
    private LocalDate visitDate;

    public TimelineItemResponseDto(TimelineItem timelineItem) {
        this.timelineItemId = timelineItem.getTimelineItemId();
        this.placeId = timelineItem.getPlace().getPlaceId();
        this.placeName = timelineItem.getPlace().getPlaceName();
        this.address = timelineItem.getPlace().getAddress();
        this.category = timelineItem.getPlace().getCategory();
        this.visitOrder = timelineItem.getVisitOrder();
        this.visitTime = timelineItem.getVisitTime();
        this.visitDate = timelineItem.getVisitDate();
    }
}
