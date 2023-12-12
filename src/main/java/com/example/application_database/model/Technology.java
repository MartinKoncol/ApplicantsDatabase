package com.example.application_database.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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

    public Technology(long id,int cluid, String technology, int level, String note) {
        this.id = id;
        this.cluid=cluid;
        this.knownTechnology = technology;
        this.level = level;
        this.note = note;
    }

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
