package com.myfoodie.dishservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "dish")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @Column(name = "dish_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_generator")
    @SequenceGenerator(name = "dish_generator", sequenceName = "dish_seq", allocationSize = 1)
    private long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Description is required")
    @Column(name = "Description", nullable = false)
    private String description;

    @NotBlank(message = "Recipe is required")
    @Column(name = "Recipe", nullable = false)
    private String recipe;

    @NotBlank(message = "ImageURL is required")
    @Size(max = 255, message = "ImageURL cannot exceed 255 characters")
    @Column(name = "ImageURL", nullable = false)
    private String imageURL;

    @NotNull(message = "TimeEstimate is required")
    @Min(value = 1, message = "TimeEstimate must be greater than 0")
    @Column(name = "TimeEstimate", nullable = false)
    private Integer timeEstimate;

    @NotNull(message = "MealType is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "MealType", nullable = false)
    private MealType mealType;

    @Column(name = "NutritionalContent", columnDefinition = "json")
    private String nutritionalContent;

    @NotNull(message = "CreatedAt is required")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt", nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedAt")
    private Date updatedAt;

}
