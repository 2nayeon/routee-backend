package com.routee.schedule.service;

import com.routee.auth.entity.User;
import com.routee.auth.repository.UserRepository;
import com.routee.schedule.dto.ScheduleRequestDto;
import com.routee.schedule.entity.Schedule;
import com.routee.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

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



}
