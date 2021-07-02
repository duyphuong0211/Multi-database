package com.example.MultiDB.database.postgresql.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "country")
@Data
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String isoCode;

    public CountryEntity() {

    }

}
