package com.myfoodie.categoryservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "category")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_seq", allocationSize = 1)
    private long id;

    @Column(name = "title")
    @NotBlank(message = "title is required")
    private String title;

    @Column(name = "dish_id")
    @NotNull(message = "dish_id is required")
    private long dishId;

}
