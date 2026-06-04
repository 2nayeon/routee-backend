package com.routee.schedule.repository;

import com.routee.schedule.entity.ScheduleTmpPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ScheduleTmpPlaceRepository extends JpaRepository<ScheduleTmpPlace, Long> {

}
