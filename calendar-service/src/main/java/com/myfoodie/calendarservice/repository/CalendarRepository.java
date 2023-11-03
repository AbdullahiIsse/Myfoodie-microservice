package com.myfoodie.calendarservice.repository;

import com.myfoodie.calendarservice.model.Calendar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalendarRepository extends CrudRepository<Calendar,Long> {

    Calendar findCalendarByUserIdAndDishId(String UserId, long dishId);

    void deleteByUserIdAndDishId (String UserId, long dishId);

    List<Calendar>findAllByUserId (String userId);
}
