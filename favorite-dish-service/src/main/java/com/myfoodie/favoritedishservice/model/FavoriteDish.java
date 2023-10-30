package com.myfoodie.favoritedishservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "favorite_dish")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDish {

    @Id
    @Column(name = "favorite_dish_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_dish_generator")
    @SequenceGenerator(name = "favorite_dish_generator", sequenceName = "favorite_dish_seq", allocationSize = 1)
    private long id;

    @Column(name = "user_id")
    @NotBlank(message = "userId is required")
    private String userId;

    @Column(name = "dish_id")
    @NotNull(message = "dish_id is required")
    private long dishId;

    @Column(name = "date_added")
    @NotNull(message = "dateAdded is required")
    private LocalDate dateAdded;


}
