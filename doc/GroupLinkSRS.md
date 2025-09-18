# Software Requirements Specification
## For GroupLink

Version 0.1  
Prepared by Javier Sedano and Isaiah Hames  
CSC340-01  
September 17, 2025

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Introduction](#1-introduction)
  * 1.1 [Document Purpose](#11-document-purpose)
  * 1.2 [Product Scope](#12-product-scope)
  * 1.3 [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
  * 1.4 [References](#14-references)
  * 1.5 [Document Overview](#15-document-overview)
* 2 [Product Overview](#2-product-overview)
  * 2.1 [Product Functions](#21-product-functions)
  * 2.2 [Product Constraints](#22-product-constraints)
  * 2.3 [User Characteristics](#23-user-characteristics)
  * 2.4 [Assumptions and Dependencies](#24-assumptions-and-dependencies)
* 3 [Requirements](#3-requirements)
  * 3.1 [Functional Requirements](#31-functional-requirements)
    * 3.1.1 [User Interfaces](#311-user-interfaces)
    * 3.1.2 [Hardware Interfaces](#312-hardware-interfaces)
    * 3.1.3 [Software Interfaces](#313-software-interfaces)
  * 3.2 [Non-Functional Requirements](#32-non-functional-requirements)
    * 3.2.1 [Performance](#321-performance)
    * 3.2.2 [Security](#322-security)
    * 3.2.3 [Reliability](#323-reliability)
    * 3.2.4 [Availability](#324-availability)
    * 3.2.5 [Compliance](#325-compliance)
    * 3.2.6 [Cost](#326-cost)
    * 3.2.7 [Deadline](#327-deadline)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|   Javier Sedano   |   9-17-25      |   Added Requirements                  |    1.0       |
|   Isaiah Hames   |    9-17-25     |    Added Requirements                 |     1.0      |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose
The purpose of this SRS document is to describe the client-view and developer-view requirements for the GroupLink application.

Client-oriented requirements describe the system from the client’s perspective. These requirements include a description of the different types of users served by the system.

Developer-oriented requirements describe the system from a software developer’s perspective. These requirements include a detailed description of functional, data, performance, and other important requirements.

### 1.2 Product Scope
The purpose of GroupLink is to connect members with a variety of groups and to create a convenient and easy-to-use application for group leaders to reach new members and manage their groups. The system is a web-based application to simplify searching and creating group functions. We will have a server supporting groups of different goals/purposes. Above all, we hope to provide a comfortable user experience along with the best offerings available.

### 1.3 Definitions, Acronyms and Abbreviations
| Reference | Definition
|-----------|-------------------------------------------------------------------------|
|Java       |A programming language originally developed by James Gosling at Sun Microsystems. We will be using this language to build the backend service for GroupLink                                                                          
|Javascript |An object-oriented computer programming language commonly used to create interactive effects within web browsers. Will be used in conjuction with HTML and CSS to make the web app.
|HTML       |Hypertext Markup Language. This is the code that will be used to structure and design the web application and its content.
|CSS        |Cascading Style Sheets. Will be used to add styles and appearance to the web app.
|API        |Application Programming Interface. This will be used to interface the backend and the fronted of our application.
|Springboot |An open-source Java-based framework used to create a micro Service. This will be used to create and run our application.                                      
### 1.4 References
Spring Framework Guide - https://spring.io/guides

### 1.5 Document Overview
Section 1 is a general introduction to the document, intended for any readers. Section 2 is focused on the product and its features. This section is for customers and business stakeholders. Section 3 specifies the requirements and constraints for the product and development process. This section is intended for all stakeholders, especially the development team.

## 2. Product Overview
GroupLink is a web-based application designed to help members discover and join a variety of groups. Members can browse group profiles, view open groups, apply to groups, and leave reviews based on their experience working with their group. Group leaders can manage their groups, update group descriptions and purpose, and accept or decline interested members to their group. The system supports two user roles including customers and providers; both with tailored services to ensure a vibrant, transparent, and community-driven application.

### 2.1 Product Functions
Group Leader (Provider)
* Create Groups
* Manage groups
* Accept and Reject member applications
* Read and respond to reviews

Member (Customer)
* Search/Filter for groups
* Apply to groups
* Leave ratings and reviews about group leaders

### 2.2 Product Constraints
The program will only run on a computer with Java jdk 21 installed. The full scope of the project is realized, however the team has a deadline of about 10 weeks, which could lead to feature cuts. The program would have a challenge scaling, as the current plan is to use a free version of a Postgresql database to store the information. The program would also have a challenge executing proficent UI/UX design due to deadline and workload capacity.
  
### 2.3 User Characteristics
Our website application does not expect our users to have any prior knowledge of a computer, apart from using a web browser. As long as users know what produce they are interested in, they should be expert level within several uses of the application.

### 2.4 Assumptions and Dependencies
We will be using Java, with our program being dependent on Spring & SpringBoot, and RestAPI to connect to external APIs and developed with VS Code. The application will also use an external location API that will help customers filter between local and non-local groups.

## 3. Requirements

### 3.1 Functional Requirements 
- FR0: The System will allow users to create accounts as either a customer(group joiner) or provider(group creator).
  - Each account shall have a unique identifier assigned at the time of creation in order to know what type of account it is.
- FR1: The system shall allow providers to create a group with details including name of group, description, type, maximum number of members, location.
- FR2: The system shall allow providers to accept or reject customers making a request to join.
- FR3: The system shall allow providers to read and respond to reviews.
- FR4: The system shall allow customers to filter and browse through available groups
- FR5: The system shall allow customers to apply to available groups
- FR6: The system shall allow customers to leave reviews about provider(group leader)
#### 3.1.1 User interfaces
Web pages using HTML, CSS, and Javascript

Will be responsive and adjust whether user is on mobile or desktop browser

#### 3.1.2 Hardware interfaces
Devices that have a web browser

System will run on a cloud server that supports Java

Database of system will run on a cloud server that supports PostgreSQL

#### 3.1.3 Software interfaces
- Java jdk 21
- PostgreSQL 17.6
- SpringBoot 3.5.5

### 3.2 Non Functional Requirements 

#### 3.2.1 Performance
- NFR0: The GroupLink system will consume less than 100mb of memory.
- NFR1: The novice user will be able to create and manage a group in less than 5 minutes.
- NFR2: The novice user will be able to search and join a group in less than 5 minutes.
- NFR3: The expert user will be able to create and manage a group in less than 2 minutes.
- NFR4: The expert user will be able to search and join a group in less than 2 minutes.

#### 3.2.2 Security
- NFR5: This system is going to be available to authorized users, using their names and passwords.

- NFR6: The System shall use security best practices to protect against common web threats.

#### 3.2.3 Reliability
- NFR7: The system shall have downtime of less than 1%.

- NFR8: The system shall ensure that data is permanent and not lost in the event of a crash or other system failure.

#### 3.2.4 Availability
- NFR9: GroupLink will be available 24/7. Scheduled maintenance will be performed between 2am eastern and 4am eastern time during low activity hours to minimize conflict with users using the application.

#### 3.2.5 Compliance
- NFR10: The system shall comply with best practices for API development and coding.

#### 3.2.6 Cost
- NFR11: We plan to spend zero dollars on this project.

#### 3.2.7 Deadline
- NFR12: The final product must be delived by December 2025.