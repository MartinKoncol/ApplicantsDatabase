package com.example.application_database.service.interfaces;

import com.example.application_database.model.Applicant;

import java.util.List;

public interface ApplicantService {
    Applicant createApplicant(Applicant post);
    Applicant updateApplicant(long id, Applicant post);
    List<Applicant> getAllApplicants();
    Applicant getApplicantById(long id);
    void deleteApplicant(long id);

}