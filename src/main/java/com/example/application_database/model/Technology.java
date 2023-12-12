package com.example.application_database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "cluid")
    private int cluid;

    @Column(name = "knownTechnology")
    private String knownTechnology;

    @Column(name = "level")
    private int level;

    @Column(name = "note")
    private String note;

    @Override
    public String toString() {
        return "Technologies{" +
                "id=" + id +
                ", technology='" + knownTechnology + '\'' +
                ", level=" + level +
                ", note=" + note +
                '}';
    }
}
