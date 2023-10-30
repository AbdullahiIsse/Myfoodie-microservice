package com.myfoodie.favoritedishservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDishRequest {
    private String userId;
    private long dishId;
    private LocalDate dateAdded;
}
