package com.example.contacts.repository;

import com.example.contacts.model.Telephone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephoneRepository extends JpaRepository<Telephone,Long> {
    
}