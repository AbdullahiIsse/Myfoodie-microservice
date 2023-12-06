package com.myfoodie.calendarservice.repository;

import com.myfoodie.calendarservice.model.Calendar;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface CalendarRepository extends CrudRepository<Calendar,Long> {

    Calendar findCalendarByUserIdAndDishId(String UserId, long dishId);

    void deleteByUserIdAndDishId (String UserId, long dishId);

    List<Calendar>findAllByUserIdAndDate (String userId, Date date);




}
