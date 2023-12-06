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
import java.util.List;

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
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "ingredients is required")
    @Column(name = "ingredients")
    private List<String> ingredients;

    @NotBlank(message = "Recipe is required")
    @Column(name = "recipe", nullable = false)
    private List<String> recipe;

    @NotBlank(message = "ImageURL is required")
    @Size(max = 255, message = "ImageURL cannot exceed 255 characters")
    @Column(name = "image_url", nullable = false)
    private String imageURL;

    @NotNull(message = "TimeEstimate is required")
    @Min(value = 1, message = "TimeEstimate must be greater than 0")
    @Column(name = "time_estimate", nullable = false)
    private Integer timeEstimate;

    @NotNull(message = "MealType is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;


    @Column(name = "nutritional_content")
    private List<String> nutritionalContent;

    @NotNull(message = "CreatedAt is required")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

}
