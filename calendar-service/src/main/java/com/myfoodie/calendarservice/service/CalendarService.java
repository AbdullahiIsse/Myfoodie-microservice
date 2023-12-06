package com.myfoodie.calendarservice.service;

import com.myfoodie.calendarservice.dto.CalendarRequest;
import com.myfoodie.calendarservice.dto.CalendarResponse;
import jakarta.validation.Valid;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface CalendarService {

    void createCalendar(@Valid CalendarRequest calendarRequest);


    void updateCalendar(String  userId,long dishId,@Valid CalendarRequest calendarRequest) throws Exception;


    void deleteCalendar(String  userId,long dishId);


    List<CalendarResponse> getCalendarEntriesByUserIdAndDate(String userId, Date date);

}
