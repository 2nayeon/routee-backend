package com.routee.schedule.repository;

import com.routee.schedule.entity.ScheduleTmpPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTmpPlaceRepository extends JpaRepository<ScheduleTmpPlace, Long> {

}
