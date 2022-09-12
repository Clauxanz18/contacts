package com.example.RESTContactsService;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.List;

import com.example.RESTContactsService.wsdl.*;

public class ContactClient extends WebServiceGatewaySupport{
	private final String URL = "http://localhost:8080/ContactsSOAP/contactsSOAP";

	public Contact getContact(int id){

		GetContactRequest request = new GetContactRequest();
		request.setId(id);

		GetContactResponse response = (GetContactResponse) getWebServiceTemplate()
				.marshalSendAndReceive(URL, request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetContactRequest"));

		return response.getContact();
	}

	public Contact createContact(Contact c){

		CreateContactRequest request = new CreateContactRequest();
		request.setContact(c);

		CreateContactResponse response = (CreateContactResponse) getWebServiceTemplate()
				.marshalSendAndReceive(URL, request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/CreateContactRequest"));

		return response.getContact();
    }
    
    public List<Contact> getContacts(){

		GetContactsRequest request = new GetContactsRequest();

		GetContactsResponse response = (GetContactsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(URL, request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetContactRequest"));

		return response.getContacts();
    }
    
    public Contact updateContact(Contact c, int id){
        UpdateContactRequest request = new UpdateContactRequest();
        request.setId(id);
        request.setContact(c);

		UpdateContactResponse response = (UpdateContactResponse) getWebServiceTemplate()
				.marshalSendAndReceive(URL, request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetContactRequest"));

		return response.getContact();
    }

    public void deleteContact(int id){
        DeleteContactRequest request = new DeleteContactRequest();
        request.setId(id);

		getWebServiceTemplate()
				.marshalSendAndReceive(URL, request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetContactRequest"));

    }
}