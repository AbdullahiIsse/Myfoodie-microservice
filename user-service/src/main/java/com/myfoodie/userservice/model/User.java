package com.myfoodie.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Entity(name = "user_account")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @NotBlank(message = "user_Id cant be null")
    private String id;

    @Column(name = "username")
    @NotBlank(message = "username cant be null")
    private String username;

    @Column(name = "email")
    @Email
    @NotBlank(message = "email cant be blank")
    private String email;

    @Column(name = "last_seen")
    @NotNull(message = "lastSeen cant be null")
    private Timestamp lastSeen;


}
