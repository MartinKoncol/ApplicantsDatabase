package com.example.application_database.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application_database.model.Applicant;
import com.example.application_database.repository.ApplicantRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ApplicantController {

    ApplicantRepository applicantRepository;

    @Autowired

    public ApplicantController(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @GetMapping("/applicants")
    public ResponseEntity<List<Applicant>> getAllApplicants(@RequestParam(required = false) String title) {
        try {
            List<Applicant> applicants = new ArrayList<>();

            if (title == null)
                applicants.addAll(applicantRepository.findAll());
            else
                applicants.addAll(applicantRepository.findByTitleContaining(title));

            if (applicants.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(applicants, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/applicants/{id}")
    public ResponseEntity<Applicant> getApplicantById(@PathVariable("id") long id) {
        Optional<Applicant> applicantData = applicantRepository.findById(id);

        if (applicantData.isPresent()) {
            return new ResponseEntity<>(applicantData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/applicants")
    public ResponseEntity<Applicant> createApplicant(@RequestBody Applicant applicantIn) {
        try {
            Applicant applicant = applicantRepository
                    .save(new Applicant(applicantIn.getId(),
                            applicantIn.getTitle(),
                            applicantIn.getFirstName(),
                            applicantIn.getLastName(),
                            applicantIn.getCurrentPosition(),
                            applicantIn.getPhone(),
                            applicantIn.getEmail()));
            return new ResponseEntity<>(applicant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/applicants/{id}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable("id") long id, @RequestBody Applicant applicantIn) {
        Optional<Applicant> applicantData = applicantRepository.findById(id);

        if (applicantData.isPresent()) {
            Applicant applicant= applicantData.get();
            applicant.setTitle(applicantIn.getTitle());
            applicant.setFirstName(applicantIn.getFirstName());
            applicant.setLastName(applicantIn.getLastName());
            applicant.setCurrentPosition(applicantIn.getCurrentPosition());
            applicant.setPhone(applicantIn.getPhone());
            applicant.setEmail(applicantIn.getEmail());
            return new ResponseEntity<>(applicantRepository.save(applicant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/applicants/{id}")
    public ResponseEntity<HttpStatus> deleteApplicant(@PathVariable("id") long id) {
        try {
            applicantRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/applicants")
    public ResponseEntity<HttpStatus> deleteAllApplicants() {
        try {
            applicantRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/applicants/all")
    public ResponseEntity<List<Applicant>> findAll() {
        try {
            List<Applicant> basics = applicantRepository.findAll();

            if (basics.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(basics, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}