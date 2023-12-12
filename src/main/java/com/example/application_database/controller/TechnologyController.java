package com.example.application_database.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.application_database.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.application_database.model.Technology;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TechnologyController {
    TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyController(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @GetMapping("/technologies")
    public ResponseEntity<List<Technology>> getAllTechnologies() {
        try {

            List<Technology> technologies = new ArrayList<>(technologyRepository.findAll());

            if (technologies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(technologies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/technologies/{id}")
    public ResponseEntity<Object> getTechnologyById(@PathVariable("id") long id) {
        Optional<Technology> technologyData = technologyRepository.findById(id);

        if (technologyData.isPresent()) {
            return new ResponseEntity<>(technologyData.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found");
        }
    }

    @PostMapping("/technologies")
    public ResponseEntity<Object> createTechnology(@RequestBody Technology technologyIn) {
        if (technologyIn.getLevel() > 10 || technologyIn.getLevel() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Your skill level input: "
                    + technologyIn.getLevel() + " is not within limits (0-10)");
        }
        try {
            Technology technology = technologyRepository
                    .save(new Technology(technologyIn.getId(),
                            technologyIn.getCluid(),
                            technologyIn.getKnownTechnology(),
                            technologyIn.getLevel(),
                            technologyIn.getNote()));
            return new ResponseEntity<>(technology, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException( HttpStatus.INTERNAL_SERVER_ERROR, "Unable to POST with provided data");
        }
    }

    @PutMapping("/technologies/{id}")
    public ResponseEntity<Technology> updateTechnology(@PathVariable("id") long id, @RequestBody Technology technologyIn) {
        Optional<Technology> technologyData = technologyRepository.findById(id);

        if (technologyData.isPresent()) {
            Technology technology = technologyData.get();
            technology.setKnownTechnology(technologyIn.getKnownTechnology());
            technology.setLevel(technologyIn.getLevel());
            technology.setNote(technologyIn.getNote());
            return new ResponseEntity<>(technologyRepository.save(technology), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/technologies/{id}")
    public ResponseEntity<HttpStatus> deleteTechnology(@PathVariable("id") long id) {
        try {
            technologyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/technologies")
    public ResponseEntity<HttpStatus> deleteAllTechnologies() {
        try {
            technologyRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
