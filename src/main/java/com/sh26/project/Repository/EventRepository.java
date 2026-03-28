package com.sh26.project.Repository;

import com.sh26.project.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Spring automatically implements these just from the method name:

    // Get all events on a specific date
    List<Event> findByDate(LocalDate date);

    // Get all events ordered by date then start time
    List<Event> findAllByOrderByDateAscStartTimeAsc();
}
