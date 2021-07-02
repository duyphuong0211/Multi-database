package com.example.MultiDB.database.postgresql.repository;

import com.example.MultiDB.database.postgresql.domain.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository()
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    

}