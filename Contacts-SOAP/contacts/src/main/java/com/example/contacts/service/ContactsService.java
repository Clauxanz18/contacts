package com.example.contacts.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.example.contacts.repository.ContactRepository;
import com.example.contacts.repository.TelephoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.guides.gs_producing_web_service.Contact;
import io.spring.guides.gs_producing_web_service.Telephone;

@Service
public class ContactsService {
    
    @Autowired
    ContactRepository cRepository;

    @Autowired
    TelephoneRepository tRepository;

    public Contact getContact(int id){
        Contact c = new Contact();
        Optional<com.example.contacts.model.Contact> contact = 
            cRepository.findById((long)id); 

        if (contact.isPresent()){
            c.setId(contact.get().getId().intValue());
            c.setFirstName(contact.get().getFirstName());
            c.setLastName(contact.get().getLastName());
            c.setEmail(contact.get().getEmail());
            c.setFavorited(contact.get().isFavorite());
            for(com.example.contacts.model.Telephone t : contact.get().getPhoneNumbers() ){
                Telephone tele = new Telephone();
                tele.setNumber(t.getNumber());
                tele.setType(t.getType());
                c.getTelephones().add(tele);
            }
            return c;
        }else{
            return c;
        }
    }

    public List<Contact> getContacts(){

        List<Contact> contacts = new LinkedList<Contact>();

        for (com.example.contacts.model.Contact contact : cRepository.findAll()){
            Contact c = new Contact();
            c.setId(contact.getId().intValue());
            c.setFirstName(contact.getFirstName());
            c.setLastName(contact.getLastName());
            c.setEmail(contact.getEmail());
            c.setFavorited(contact.isFavorite());

            contacts.add(c);
        }
        return contacts;
    }

    public Contact updateContact(int id, Contact c){
        Optional<com.example.contacts.model.Contact> query = 
            cRepository.findById((long)id); 
        
        if(query.isPresent()){
            com.example.contacts.model.Contact contact = query.get();
            contact.setFirstName(c.getFirstName());
            contact.setLastName(c.getLastName());
            contact.setEmail(c.getEmail());
            contact.setFavorite(c.isFavorited());

            for (com.example.contacts.model.Telephone t : contact.getPhoneNumbers()){
                tRepository.delete(t);
            }

            contact.setPhoneNumbers(new LinkedList<com.example.contacts.model.Telephone>());
            List<com.example.contacts.model.Telephone> list = new LinkedList<>();
            contact = cRepository.save(contact);
            for(Telephone t : c.getTelephones()){
                com.example.contacts.model.Telephone telephone = new com.example.contacts.model.Telephone();
                telephone.setNumber(t.getNumber());
                telephone.setType(t.getType());
                telephone.setContact(contact);
                list.add(tRepository.save(telephone));
            }

            contact.setPhoneNumbers(list);

            c.setId(contact.getId().intValue());
            c.setFirstName(contact.getFirstName());
            c.setLastName(contact.getLastName());
            c.setEmail(contact.getEmail());
            c.setFavorited(contact.isFavorite());
            c.getTelephones().clear();
            for(com.example.contacts.model.Telephone t : contact.getPhoneNumbers() ){
                Telephone tele = new Telephone();
                tele.setNumber(t.getNumber());
                tele.setType(t.getType());
                c.getTelephones().add(tele);
            }
            return c;

        }
        return new Contact();
    }

    public Contact createContact(Contact c){
        com.example.contacts.model.Contact contact 
            = new com.example.contacts.model.Contact();

            contact.setPhoneNumbers(new LinkedList<com.example.contacts.model.Telephone>());
        
        contact.setFirstName(c.getFirstName());
        contact.setLastName(c.getLastName());
        contact.setEmail(c.getEmail());
        contact.getPhoneNumbers().clear();
        contact.setFavorite(c.isFavorited());

        contact = cRepository.save(contact);

        for(Telephone t : c.getTelephones()){
            com.example.contacts.model.Telephone telephone 
                = new com.example.contacts.model.Telephone();
            telephone.setNumber(t.getNumber());
            telephone.setType(t.getType());
            telephone.setContact(contact);
            contact.getPhoneNumbers().add(tRepository.save(telephone));

        }

        c.setId(contact.getId().intValue());
        c.setFirstName(contact.getFirstName());
        c.setLastName(contact.getLastName());
        c.setEmail(contact.getEmail());
        c.setFavorited(contact.isFavorite());
        c.getTelephones().clear();
        for(com.example.contacts.model.Telephone t : contact.getPhoneNumbers() ){
            Telephone tele = new Telephone();
            tele.setNumber(t.getNumber());
            tele.setType(t.getType());
            c.getTelephones().add(tele);
        }
        
        return c;
    }

    public void deleteContact(int id){
        Optional<com.example.contacts.model.Contact> query = 
            cRepository.findById((long) id);
            
            
        if(query.isPresent()){
            com.example.contacts.model.Contact contact = query.get();
            cRepository.delete(contact);
        }
    }
}