RestInDepthMeetup
=================

Sources for the Rest in Depth meetup. Goal is to explore HATEAOS API’s using Spring Boot and Spring HATEAOS. Secondary goal is to see how Atom protocol can be used to integrate applications.
   
Orders application with a REST/HATEAOS web interface
Products application with a REST and Atom interface


Open actions:
-Slides HATEAOS & Atom 
-Example Appl Orders (HATEAOS with Spring) 
-Example Appl Products (Atom with Jersey & ROME or Spring)

Optional topics:
-Swagger
-Client implementation for HATEAOS 
-Client implementation for Atom 
-Use of eTags

Known bugs:
1. GET orders. Indien er geen orders zijn dan zou een lege lijst moeten worden getoond, met 2 links: add (POST) order en get (GET) orders
2. Tests fail


References:
-http://oredev.org/2010/sessions/hypermedia-apis



-https://spring.io/blog/2009/03/16/adding-an-atom-view-to-an-application-using-spring-s-rest-support
