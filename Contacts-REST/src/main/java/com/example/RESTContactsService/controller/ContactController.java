package com.example.RESTContactsService.controller;

import java.util.List;

import com.example.RESTContactsService.ContactClient;
import com.example.RESTContactsService.wsdl.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
@CrossOrigin
public class ContactController {

    @Autowired
    ContactClient client;
    
    @GetMapping("")
    public List<Contact> getContacts(){
        return client.getContacts();
    }

    @GetMapping("/{id}")
    public Contact getContact(@PathVariable int id){ 
        return client.getContact(id);
    }

    @PostMapping("")
    public Contact createContact(@RequestBody Contact c){
        return client.createContact(c);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@RequestBody Contact c, @PathVariable int id){
        return client.updateContact(c, id);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable int id){
        client.deleteContact(id);
    }
}