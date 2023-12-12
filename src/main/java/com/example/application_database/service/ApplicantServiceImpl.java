package com.example.application_database.service;

import com.example.application_database.model.Applicant;
import com.example.application_database.repository.ApplicantRepository;
import com.example.application_database.service.interfaces.ApplicantService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    public ApplicantServiceImpl(ApplicantRepository postRepository) {
        super();
        this.applicantRepository = postRepository;
    }

    @Override
    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    @Override
    public Applicant createApplicant(Applicant post) {
        return applicantRepository.save(post);
    }

    @Override
    public Applicant updateApplicant(long id, Applicant applicantRequest) {

        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        applicant.setCluid(applicantRequest.getCluid());
        applicant.setTitle(applicantRequest.getTitle());
        applicant.setFirstName(applicantRequest.getFirstName());
        applicant.setLastName(applicantRequest.getLastName());
        applicant.setCurrentPosition(applicantRequest.getCurrentPosition());
        applicant.setPhone(applicantRequest.getPhone());
        applicant.setEmail(applicantRequest.getEmail());

        return applicantRepository.save(applicant);
    }

    @Override
    public void deleteApplicant(long id) {
        Applicant post = applicantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        applicantRepository.delete(post);
    }

    @Override
    public Applicant getApplicantById(long id) {
        Optional<Applicant> result = applicantRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
