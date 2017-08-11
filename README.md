# RMS (ReminderManagementServ)

RMS provides REST interface to  :
1) Add Reminders
2) Update Reminders
3) Search Reminders based on due date and / or Status

## Reminders

Reminders can be added / modified or searched using REST endpoints. A single reminder can be added for multiple events [Date Time].
User can add reminder by passing values as few as :
1) Reminder subject [Name]
2) Description
3) Date and Time to reminder

### Endpoints

1) Add Reminder :
```
Method : POST
URI : /reminders/update
Media : XML / JSON
```
2) Update Reminder :
```
Method : POST
URI : /reminders/add
Media : XML / JSON
```
3) Get Reminder :
```
Method : Get
URI : /reminders/search?date={{date}}&status={{status}}
Media : XML / JSON
```

### Technology Stack
RMS is written using :

1) Spring Boot
2) JPA - Hibernate
3) Rest - Jersey
4) Javax Validation
5) H2 DB
6) Mockito & PowerMock - WIP
7) Ajax HTML - WIP

## Schema

WIP

## PostMan Collection

https://www.getpostman.com/collections/4caf3c36566bb06e6814

### Start Application

Application can be star by executing main method of RMSApplication.java

## Authors

* **Ritvik Pandya** - https://www.linkedin.com/in/ritvik-pandya-70251b15/
