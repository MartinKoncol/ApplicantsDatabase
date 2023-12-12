package com.example.application_database.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applicants", uniqueConstraints = {@UniqueConstraint(columnNames = {"cluid"})})
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="cluid")
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

