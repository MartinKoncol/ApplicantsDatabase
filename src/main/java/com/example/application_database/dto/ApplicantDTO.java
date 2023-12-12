package com.example.application_database.dto;

import lombok.Data;

@Data
public class ApplicantDTO {
    private long id;
    private int cluid;
    private String title;
    private String firstName;
    private String lastName;
    private String currentPosition;
    private String phone;
    private String email;

}
