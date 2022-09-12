package com.example.contacts.endpoints;

import com.example.contacts.service.ContactsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.Contact;
import io.spring.guides.gs_producing_web_service.GetContactRequest;
import io.spring.guides.gs_producing_web_service.GetContactResponse;
import io.spring.guides.gs_producing_web_service.GetContactsRequest;
import io.spring.guides.gs_producing_web_service.GetContactsResponse;
import io.spring.guides.gs_producing_web_service.CreateContactRequest;
import io.spring.guides.gs_producing_web_service.CreateContactResponse;
import io.spring.guides.gs_producing_web_service.UpdateContactRequest;
import io.spring.guides.gs_producing_web_service.UpdateContactResponse;
import io.spring.guides.gs_producing_web_service.DeleteContactRequest;


@Endpoint 
public class ContactsEndpoint {
    
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private ContactsService cService;

    @Autowired
    public ContactsEndpoint(ContactsService cService){
        this.cService= cService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getContactRequest")
	@ResponsePayload
	public GetContactResponse getContact(@RequestPayload GetContactRequest request) {
        GetContactResponse response = new GetContactResponse();
		response.setContact(cService.getContact(request.getId()));
		return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getContactsRequest")
	@ResponsePayload
	public GetContactsResponse getContacts(@RequestPayload GetContactsRequest request) {
        GetContactsResponse response = new GetContactsResponse();
        
        for(Contact c : cService.getContacts()){
            response.getContacts().add(c);
        }
		return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateContactRequest")
	@ResponsePayload
	public UpdateContactResponse getContacts(@RequestPayload UpdateContactRequest request) {
        UpdateContactResponse response = new UpdateContactResponse();
        response.setContact(cService.updateContact(request.getId(), request.getContact()));
		return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createContactRequest")
	@ResponsePayload
	public CreateContactResponse getContacts(@RequestPayload CreateContactRequest request) {
        CreateContactResponse response = new CreateContactResponse();
        response.setContact(cService.createContact(request.getContact()));
		return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteContactRequest")
	@ResponsePayload
	public void getContacts(@RequestPayload DeleteContactRequest request) {
        cService.deleteContact(request.getId());
    }

}