package com.example.application_database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.application_database.model.Applicant;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    List<Applicant> findByTitleContaining(String title);
}
