# Contacts App

This is a monorepo containing three independent apps which together compose a simple contacts manager.

The project is a proof of concept for a real-case scenario in which a client has an outdated SOAP backend, and wants to bring this backend service into modern frontend front-end apps.

The approach taken here is to construct an adapter interface between the front and backend applications to make the conversion between **REST** and **SOAP**, giving the app REST compatibility without modifying the original SOAP backend, effectively maintaining **retrocompatibility**.

The apps are structured as follows: 

## Contacts-SOAP

Backend application with SOAP interface. Connects to database and handles the actual storage retrieval for the app.

## Contacts-REST

REST Interface which bridges the gap between the Angular front and SOAP backend.

## Contacts-Front

Angular 9 project which connects to a REST API to handle contact creation and edition.

# Documentation

This arquitecture documentation was done based on the [C4 Arquitechtural model](https://c4model.com/) Levels 1 and 3.

---

![System Context Diagram](/img/Wepsys%20C4%20Model%20-%20System%20Context%20Diagram.jpg)

---

![Component Diagram](/img/Wepsys%20C4%20Model%20-%20Component%20Diagram.jpg)