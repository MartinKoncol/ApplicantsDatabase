package com.example.application_database.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "applicants")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "cluid")
    private int cluid;

    @Column(name = "title")
    private String title;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "currentPosition")
    private String currentPosition;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    public Applicant(long id,
                     int cluid,
                     String title,
                     String firstName,
                     String lastName,
                     String currentPosition,
                     String phone,
                     String email) {
        this.id = id;
        this.cluid = cluid;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentPosition = currentPosition;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", cluid=" + cluid +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", currentPosition='" + currentPosition + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

