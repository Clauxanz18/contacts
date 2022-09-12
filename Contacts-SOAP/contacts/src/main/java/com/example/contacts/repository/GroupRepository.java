package com.example.contacts.repository;

import com.example.contacts.model.Group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
    
}