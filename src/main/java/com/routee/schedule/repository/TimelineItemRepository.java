package com.routee.schedule.repository;

import com.routee.schedule.entity.TimelineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelineItemRepository extends JpaRepository<TimelineItem, Long> {
    List<TimelineItem> findByScheduleScheduleIdOrderByVisitOrderAsc(Long scheduleId);
}
