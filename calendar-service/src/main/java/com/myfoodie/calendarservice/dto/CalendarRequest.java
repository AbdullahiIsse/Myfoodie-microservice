package com.myfoodie.calendarservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarRequest {

    private String userId;
    private long dishId;
    private LocalDateTime date;

}
