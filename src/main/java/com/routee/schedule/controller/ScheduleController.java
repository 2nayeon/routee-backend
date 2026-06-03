package com.routee.schedule.controller;

import com.routee.schedule.dto.PlaceRequestDto;
import com.routee.schedule.dto.ScheduleRequestDto;
import com.routee.schedule.dto.TmpPlaceResponseDto;
import com.routee.schedule.entity.Schedule;
import com.routee.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Long> createSchedule(
            @RequestParam(name = "userId") Long userId,
            @RequestBody ScheduleRequestDto requestDto){
        Long scheduleId = scheduleService.createSchedule(userId, requestDto);
        return ResponseEntity.ok(scheduleId);
    }

    @PostMapping("{scheduleId}/tmp-places")
    public ResponseEntity<TmpPlaceResponseDto> addTmpPlace(
            @PathVariable(name = "scheduleId") Long scheduleId,
            @RequestBody PlaceRequestDto requestDto) {
        TmpPlaceResponseDto responseDto = scheduleService.addTmpPlace(scheduleId, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
