package com.myfoodie.calendarservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity(name = "calendar")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calendar_generator")
    @SequenceGenerator(name = "calendar_generator", sequenceName = "calendar_seq", allocationSize = 1)
    private long id;

    @Column(name = "user_id")
    @NotBlank(message = "userId is required")
    private String userId;

    @Column(name = "dish_id")
    @NotNull(message = "dish_id is required")
    private long dishId;

    @Column(name = "date")
    @NotNull(message = "date is required")
    private Date date;


}
