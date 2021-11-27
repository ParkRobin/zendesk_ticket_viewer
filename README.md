# Zendesk Ticket Viewer Backend

## 1. Overview

Zendesk is a customer service tool that allows the creation and management of support tickets. Your company needs you to build a Ticket Viewer that will: 

1. Connect to the Zendesk API
2. Request all the tickets for your account
3. Display them in a list
4. Display individual ticket details
5. Page through tickets when more than 25 are returned

## 2. Installation

```sh
mvn install
mvn package spring-boot:repackage
```

## 3. Run

```sh
java -jar target/zendesk_ticket_viewer-1.0-SNAPSHOT.jar com.zendesk.ticketviewer.MainApplication
```

## 4. Swagger

```
http://localhost:8080/zendesk/ticket/viewer/swagger-ui/#/
```

## 5. Deployment on Heroku

```
https://zendesk-ticket-viewer-robin.herokuapp.com/zendesk/ticket/viewer/swagger-ui/#/
```

