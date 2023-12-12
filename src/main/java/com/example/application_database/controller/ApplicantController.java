package com.example.application_database.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.application_database.dto.ApplicantDTO;
import com.example.application_database.service.interfaces.ApplicantService;
import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.application_database.model.Applicant;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ApplicantController {

    private final ModelMapper modelMapper;
    private final ApplicantService applicantService;

    @Autowired
    public ApplicantController(ApplicantService applicantService, ModelMapper modelMapper) {
        super();
        this.applicantService = applicantService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/applicants")
    public List<ApplicantDTO> getAllApplicants() {

        return applicantService.getAllApplicants().stream().map(applicants -> modelMapper.map(applicants, ApplicantDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/applicants/{id}")
    public ResponseEntity<ApplicantDTO> getApplicantById(@PathVariable(name = "id") Long id) {

        Applicant applicant = applicantService.getApplicantById(id);
        ApplicantDTO postResponse = modelMapper.map(applicant, ApplicantDTO.class);

        return ResponseEntity.ok().body(postResponse);
    }

    @PostMapping("/applicants")
    public ResponseEntity<ApplicantDTO> createPost(@RequestBody ApplicantDTO applicantDTO) {

        Applicant postRequest = modelMapper.map(applicantDTO, Applicant.class);
        Applicant applicant = applicantService.createApplicant(postRequest);
        ApplicantDTO postResponse = modelMapper.map(applicant, ApplicantDTO.class);

        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    @PutMapping("/applicants/{id}")
    public ResponseEntity<ApplicantDTO> updatePost(@PathVariable long id, @RequestBody ApplicantDTO applicantDTO) {

        Applicant postRequest = modelMapper.map(applicantDTO, Applicant.class);
        Applicant post = applicantService.updateApplicant(id, postRequest);
        ApplicantDTO postResponse = modelMapper.map(post, ApplicantDTO.class);

        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping("/applicants/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable(name = "id") Long id) {
        applicantService.deleteApplicant(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}