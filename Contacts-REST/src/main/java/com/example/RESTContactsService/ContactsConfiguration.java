package com.example.RESTContactsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ContactsConfiguration {

  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    // this package must match the package in the <generatePackage> specified in
    // pom.xml
    marshaller.setContextPath("com.example.RESTContactsService.wsdl");
    return marshaller;
  }

  @Bean
  public ContactClient countryClient(Jaxb2Marshaller marshaller) {
    ContactClient client = new ContactClient();
    client.setDefaultUri("http://localhost:8080/contactsSOAP");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }

}