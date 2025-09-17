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
|      |         |                     |           |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose
Describe the purpose of the SRS and its intended audience.

### 1.2 Product Scope
Identify the product whose software requirements are specified in this document, including the revision or release number. Explain what the product that is covered by this SRS will do, particularly if this SRS describes only part of the system or a single subsystem. 
Provide a short description of the software being specified and its purpose, including relevant benefits, objectives, and goals. Relate the software to corporate goals or business strategies. If a separate vision and scope document is available, refer to it rather than duplicating its contents here.

### 1.3 Definitions, Acronyms and Abbreviations
| Reference | Definition
|-----------|-------------------------------------------------------------------------|
|Java       |                                                                          
|Javascript |
|HTML       |
|CSS        |
|API        |
|Springboot |                                      
### 1.4 References
List any other documents or Web addresses to which this SRS refers. These may include user interface style guides, contracts, standards, system requirements specifications, use case documents, or a vision and scope document. Provide enough information so that the reader could access a copy of each reference, including title, author, version number, date, and source or location.

### 1.5 Document Overview
Describe what the rest of the document contains and how it is organized.

## 2. Product Overview
This section should describe the general factors that affect the product and its requirements. This section does not state specific requirements. Instead, it provides a background for those requirements, which are defined in detail in Section 3, and makes them easier to understand.

### 2.1 Product Functions
Summarize the major functions the product must perform or must let the user perform. Details will be provided in Section 3, so only a high level summary (such as a bullet list) is needed here. Organize the functions to make them understandable to any reader of the SRS. A picture of the major groups of related requirements and how they relate, such as a top level data flow diagram or object class diagram, is often effective.

### 2.2 Product Constraints
This subsection should provide a general description of any other items that will limit the developer’s options. These may include:  

* Interfaces to users, other applications or hardware.  
* Quality of service constraints.  
* Standards compliance.  
* Constraints around design or implementation.
  
### 2.3 User Characteristics
Identify the various user classes that you anticipate will use this product. User classes may be differentiated based on frequency of use, subset of product functions used, technical expertise, security or privilege levels, educational level, or experience. Describe the pertinent characteristics of each user class. Certain requirements may pertain only to certain user classes. Distinguish the most important user classes for this product from those who are less important to satisfy.

### 2.4 Assumptions and Dependencies
List any assumed factors (as opposed to known facts) that could affect the requirements stated in the SRS. These could include third-party or commercial components that you plan to use, issues around the development or operating environment, or constraints. The project could be affected if these assumptions are incorrect, are not shared, or change. Also identify any dependencies the project has on external factors, such as software components that you intend to reuse from another project, unless they are already documented elsewhere (for example, in the vision and scope document or the project plan).

## 3. Requirements

### 3.1 Functional Requirements 
- FR0: The System will allow users to create accounts as either a customer(group joiner) or provider(group creator).
  - Each account shall have a unique identifier assigned at the time of creation in order to know what type of account it is.
- FR1: The system shall allow providers to create a group with details including name of group, description, type, maximum number of members, location.
- FR2: The system shall allow providers to accept or reject customers making a request to join.
- FR3: The system shall allow providers to read and respond to reviews.
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