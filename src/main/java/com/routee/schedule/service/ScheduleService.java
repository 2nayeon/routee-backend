package com.routee.schedule.service;

import com.routee.auth.entity.User;
import com.routee.auth.repository.UserRepository;
import com.routee.schedule.dto.*;
import com.routee.schedule.entity.Place;
import com.routee.schedule.entity.Schedule;
import com.routee.schedule.entity.ScheduleTmpPlace;
import com.routee.schedule.entity.TimelineItem;
import com.routee.schedule.repository.PlaceRepository;
import com.routee.schedule.repository.ScheduleRepository;
import com.routee.schedule.repository.ScheduleTmpPlaceRepository;
import com.routee.schedule.repository.TimelineItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final PlaceRepository placeRepository;
    private final ScheduleTmpPlaceRepository scheduleTmpPlaceRepository;
    private final TimelineItemRepository timelineItemRepository;

    @Transactional
    public Long createSchedule(Long userId, ScheduleRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다. id=" + userId));

        Schedule newSchedule = Schedule.builder()
                        .title(requestDto.getTitle())
                        .startDate(requestDto.getStartDate())
                        .endDate(requestDto.getEndDate())
                        .user(user)
                        .build();

        Schedule saveSchedule = scheduleRepository.save(newSchedule);

        return saveSchedule.getScheduleId();
    }


    @Transactional
    public TmpPlaceResponseDto addTmpPlace(Long scheduleId, PlaceRequestDto requestDto) {
        Place place = placeRepository.findByNaverPlaceId(requestDto.getNaverPlaceId())
                .orElseGet(() -> {
                    Place newPlace = Place.builder()
                            .naverPlaceId(requestDto.getNaverPlaceId())
                            .placeName(requestDto.getPlaceName())
                            .address(requestDto.getAddress())
                            .latitude(requestDto.getLatitude())
                            .longitude(requestDto.getLongitude())
                            .category(requestDto.getCategory())
                            .build();

                    return placeRepository.save(newPlace);
                });

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다."));

        ScheduleTmpPlace tmpPlace = ScheduleTmpPlace.builder()
                .schedule(schedule)
                .place(place)
                .build();

        ScheduleTmpPlace savedTmpPlace = scheduleTmpPlaceRepository.save(tmpPlace);

        return new TmpPlaceResponseDto(savedTmpPlace);
    }


    @Transactional
    public TimelineItemResponseDto addTimelineItem(Long scheduleId, TimelineItemRequestDto requestDto) {
        ScheduleTmpPlace tmpPlace = scheduleTmpPlaceRepository.findById(requestDto.getTmpPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 임시 장소입니다. id=" + requestDto.getTmpPlaceId()));

        TimelineItem timelineItem = TimelineItem.builder()
                .schedule(tmpPlace.getSchedule())
                .place(tmpPlace.getPlace())
                .visitOrder(requestDto.getVisitOrder())
                .visitTime(requestDto.getVisitTime())
                .visitDate(requestDto.getVisitDate())
                .build();

        TimelineItem savedTimelineItem = timelineItemRepository.save(timelineItem);
        scheduleTmpPlaceRepository.delete(tmpPlace);

        return new TimelineItemResponseDto(savedTimelineItem);
    }
}
