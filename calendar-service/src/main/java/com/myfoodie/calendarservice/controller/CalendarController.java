package com.myfoodie.calendarservice.controller;

import com.myfoodie.calendarservice.dto.CalendarRequest;
import com.myfoodie.calendarservice.dto.CalendarResponse;
import com.myfoodie.calendarservice.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCalendar(@RequestBody CalendarRequest calendarRequest) {
        calendarService.createCalendar(calendarRequest);
    }

    @PatchMapping("/{userId}/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCalender(@PathVariable String userId, @PathVariable long dishId, @RequestBody CalendarRequest calendarRequest) throws Exception {
        calendarService.updateCalendar(userId, dishId, calendarRequest);
    }

    @DeleteMapping("/{userId}/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCalender(@PathVariable String userId, @PathVariable long dishId) {
        calendarService.deleteCalendar(userId, dishId);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CalendarResponse> getCalendarEntriesByUserId(@PathVariable String userId) {
        return calendarService.getCalendarEntriesByUserId(userId);
    }


}
