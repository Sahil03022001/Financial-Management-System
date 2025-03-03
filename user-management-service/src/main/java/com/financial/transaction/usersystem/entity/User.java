package com.financial.transaction.usersystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String userId;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobNo;

    //Format -> yyyy-mm-dd
    private Date dateOfBirth;

    private Date created;

    private Date updated;

    private String password;

}
