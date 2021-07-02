package com.example.MultiDB.controller;

import com.example.MultiDB.database.postgresql.domain.CountryEntity;
import com.example.MultiDB.database.postgresql.repository.CountryRepository;
import com.example.MultiDB.database.mysql.domain.CategoryEntity;
import com.example.MultiDB.database.mysql.repository.CategoryRepository;
import com.example.MultiDB.dual.dual;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@Slf4j
public class TestController {


    private CategoryRepository categoryRepository;
    private CountryRepository countryRepository;

    @Autowired
    TestController(CategoryRepository categoryRepository, CountryRepository countryRepository) {
        this.categoryRepository = categoryRepository;
        this.countryRepository = countryRepository;
    }


    @RequestMapping(value = "category", method = RequestMethod.POST)
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity domain) {

        categoryRepository.save(domain);
        return ResponseEntity.ok(domain);
    }


    @RequestMapping(value = "country", method = RequestMethod.POST)
    public ResponseEntity<CountryEntity> createCountry(@RequestBody CountryEntity domain)  {
        countryRepository.save(domain);
        return ResponseEntity.ok(domain);
    }
    //insert dual database-1
    @RequestMapping(value = "allthings", method = RequestMethod.POST)
    public ResponseEntity<?> createAll(@RequestBody CategoryEntity domain, CountryEntity domain1)  {
        Map<Object, Object> result = new HashMap<>();
        result.put("country",countryRepository.save(domain1));
        result.put("category",categoryRepository.save(domain));
        return ResponseEntity.ok(result);
    }
   /* //insert dual database
    @RequestMapping(value = "allthings", method = RequestMethod.POST)
    public ResponseEntity<dual> createAll(@RequestBody dual domain)  {
        Map<Object, Object> result = new HashMap<>();
        result.put("country",countryRepository.save(domain.countryEntity));
        result.put("category",categoryRepository.save(domain.categoryEntity));

        return ResponseEntity.ok(domain);
    }*/


    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Map<String, Object> getAll() {
        final Map<String, Object> result = new HashMap<>();
        result.put("country", countryRepository.findAll());
        result.put("category", categoryRepository.findAll());
        return result;
    }
}
