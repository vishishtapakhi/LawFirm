# Legal Case Management System

![License](https://img.shields.io/badge/license-MIT-blue.svg)

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction

The **Legal Case Management System** is a comprehensive web application designed to streamline the management of legal cases, clients, appointments, and related administrative tasks for law firms and legal professionals. Built with Java and Spring Boot, this system offers a robust backend coupled with an intuitive frontend interface, ensuring efficient handling of various legal processes.

## Features

- **User Management**
  - Registration and authentication for lawyers, paralegals, and clients.
  - Role-based access control to ensure data security and appropriate permissions.

- **Case Management**
  - Create, update, and manage different types of cases (Civil, Criminal, Corporate, Matrimonial).
  - Assign cases to lawyers and paralegals.
  - Track case notes and evidence.

- **Appointment Scheduling**
  - Schedule and manage client appointments.
  - View appointment lists and details.

- **Client Management**
  - Add and manage client information.
  - Track client interactions and communications.

- **Invoice Management**
  - Generate and manage invoices for clients.
  - Track payment statuses.

- **Task Management**
  - Assign and track tasks for legal staff.
  - Monitor task progress and completion.

- **Dashboard**
  - Overview of system metrics and activities.
  - Visual representations of case statuses, appointments, and more.

## Technologies Used

- **Backend:**
  - Java 11
  - Spring Boot
  - Spring Data JPA
  - Spring Security
  - Maven

- **Frontend:**
  - Thymeleaf
  - HTML5
  - CSS3
  - JavaScript

- **Database:**
  - H2 Database (for development)
  - MySQL/PostgreSQL (for production)

- **Others:**
  - Git
  - GitHub

## Project Structure

```
DBMS_Project/
│   .gitignore
│   mvnw
│   mvnw.cmd
│   pom.xml
│   README.md
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───example
│   │   │           └───demo
│   │   │               │   DemoApplication.java
│   │   │               │
│   │   │               ├───config
│   │   │               │       SecurityConfig.java
│   │   │               │
│   │   │               ├───controller
│   │   │               │       AppointmentController.java
│   │   │               │       CaseNotesController.java
│   │   │               │       CivilCaseController.java
│   │   │               │       ClientController.java
│   │   │               │       CorporateCaseController.java
│   │   │               │       CourtController.java
│   │   │               │       CriminalCaseController.java
│   │   │               │       DashboardController.java
│   │   │               │       InvoiceController.java
│   │   │               │       LawyerController.java
│   │   │               │       MatrimonialCaseController.java
│   │   │               │       ParalegalController.java
│   │   │               │       TaskController.java
│   │   │               │       UserController.java
│   │   │               │       WitAndEviController.java
│   │   │               │
│   │   │               ├───dao
│   │   │               │       AppointmentDAO.java
│   │   │               │       CaseCourtDAO.java
│   │   │               │       CaseNotesDAO.java
│   │   │               │       CaseWitDAO.java
│   │   │               │       CategoryDAO.java
│   │   │               │       CivilCaseDAO.java
│   │   │               │       ClientDAO.java
│   │   │               │       CorporateCaseDAO.java
│   │   │               │       CourtDAO.java
│   │   │               │       CriminalCaseDAO.java
│   │   │               │       InvoiceDAO.java
│   │   │               │       LawyerDAO.java
│   │   │               │       MatrimonialCaseDAO.java
│   │   │               │       ParalegalDAO.java
│   │   │               │       TaskDAO.java
│   │   │               │       WitAndEviDAO.java
│   │   │               │
│   │   │               ├───dto
│   │   │               │       UserDto.java
│   │   │               │
│   │   │               ├───model
│   │   │               │       Appointment.java
│   │   │               │       CaseCourt.java
│   │   │               │       CaseNotes.java
│   │   │               │       CaseWit.java
│   │   │               │       Category.java
│   │   │               │       CivilCase.java
│   │   │               │       Client.java
│   │   │               │       ClientEmail.java
│   │   │               │       ClientPhone.java
│   │   │               │       ClientPhoneId.java
│   │   │               │       CorporateCase.java
│   │   │               │       Court.java
│   │   │               │       CriminalCase.java
│   │   │               │       Invoice.java
│   │   │               │       Lawyer.java
│   │   │               │       LawyerEmail.java
│   │   │               │       LawyerPhone.java
│   │   │               │       MatrimonialCase.java
│   │   │               │       Paralegal.java
│   │   │               │       ParalegalEmail.java
│   │   │               │       ParalegalPhone.java
│   │   │               │       Task.java
│   │   │               │       User.java
│   │   │               │       UserPhone.java
│   │   │               │       UserPhoneId.java
│   │   │               │       WitAndEvi.java
│   │   │               │
│   │   │               ├───repository
│   │   │               │       UserRepository.java
│   │   │               │
│   │   │               └───service
│   │   │                       CustomSuccessHandler.java
│   │   │                       CustomUserDetail.java
│   │   │                       CustomUserDetailsService.java
│   │   │                       UserService.java
│   │   │                       UserServiceImpl.java
│   │   │
│   │   └───resources
│   │       │   application.properties
│   │       │
│   │       ├───static
│   │       │   ├───css
│   │       │   │       style.css
│   │       │   │       styles.css
│   │       │   │
│   │       │   └───js
│   │       │           ind.js
│   │       │
│   │       └───templates
│   │               addinvoCorp.html
│   │               admin.html
│   │               appointform.html
│   │               appointlist.html
│   │               assigncase.html
│   │               assignCourt.html
│   │               assignLawyers.html
│   │               assignParalegals.html
│   │               casenote.html
│   │               casenotelist.html
│   │               category.html
│   │               cattask.html
│   │               choosecase.html
│   │               civilCase.html
│   │               civilCaseList.html
│   │               client.html
│   │               client_list.html
│   │               corpAppoint.html
│   │               corporateCase.html
│   │               corporateCaseList.html
│   │               Court.html
│   │               CourtList.html
│   │               criminalCase.html
│   │               criminalCaseList.html
│   │               dashboard.html
│   │               edit_client.html
│   │               frontpage.html
│   │               invoicelist.html
│   │               lawyer.html
│   │               lawyerdash.html
│   │               law_list.html
│   │               login.html
│   │               matrimonialCase.html
│   │               matrimonialCaseList.html
│   │               navbar.html
│   │               paralegal.html
│   │               paralegaldash.html
│   │               para_list.html
│   │               register.html
│   │               register_form.html
│   │               register_success.html
│   │               sidebar.html
│   │               task.html
│   │               taskList.html
│   │               user.html
│   │               witandeviform.html
│   │               witcat.html
│   │               witlist.html
│   │
│   └───test
│       └───java
│           └───com
│               └───example
│                   └───demo
│                           DemoApplicationTests.java
│
└───target
    ├───classes
    │   │   application.properties
    │   │
    │   ├───com
    │   │   └───example
    │   │       └───demo
    │   │           │   DemoApplication.class
    │   │           │
    │   │           ├───config
    │   │           │       SecurityConfig.class
    │   │           │
    │   │           ├───controller
    │   │           │       AppointmentController.class
    │   │           │       CaseNotesController.class
    │   │           │       CivilCaseController.class
    │   │           │       ClientController.class
    │   │           │       CorporateCaseController.class
    │   │           │       CourtController.class
    │   │           │       CriminalCaseController.class
    │   │           │       DashboardController.class
    │   │           │       InvoiceController.class
    │   │           │       LawyerController.class
    │   │           │       MatrimonialCaseController.class
    │   │           │       ParalegalController.class
    │   │           │       TaskController.class
    │   │           │       UserController.class
    │   │           │       WitAndEviController.class
    │   │           │
    │   │           ├───dao
    │   │           │       AppointmentDAO.class
    │   │           │       CaseCourtDAO.class
    │   │           │       CaseNotesDAO.class
    │   │           │       CaseWitDAO.class
    │   │           │       CategoryDAO.class
    │   │           │       CivilCaseDAO.class
    │   │           │       ClientDAO.class
    │   │           │       CorporateCaseDAO.class
    │   │           │       CourtDAO.class
    │   │           │       CriminalCaseDAO.class
    │   │           │       InvoiceDAO.class
    │   │           │       LawyerDAO.class
    │   │           │       MatrimonialCaseDAO.class
    │   │           │       ParalegalDAO.class
    │   │           │       TaskDAO.class
    │   │           │       WitAndEviDAO.class
    │   │           │
    │   │           ├───dto
    │   │           │       UserDto.class
    │   │           │
    │   │           ├───model
    │   │           │       Appointment.class
    │   │           │       CaseCourt.class
    │   │           │       CaseNotes.class
    │   │           │       CaseWit.class
    │   │           │       Category.class
    │   │           │       CivilCase.class
    │   │           │       Client.class
    │   │           │       ClientEmail.class
    │   │           │       ClientPhone.class
    │   │           │       ClientPhoneId.class
    │   │           │       CorporateCase.class
    │   │           │       Court.class
    │   │           │       CriminalCase.class
    │   │           │       Invoice.class
    │   │           │       Lawyer.class
    │   │           │       LawyerEmail.class
    │   │           │       LawyerPhone.class
    │   │           │       MatrimonialCase.class
    │   │           │       Paralegal.class
    │   │           │       ParalegalEmail.class
    │   │           │       ParalegalPhone.class
    │   │           │       Task.class
    │   │           │       User.class
    │   │           │       UserPhone.class
    │   │           │       UserPhoneId.class
    │   │           │       WitAndEvi.class
    │   │           │
    │   │           ├───repository
    │   │           │       UserRepository.class
    │   │           │
    │   │           └───service
    │   │                   CustomSuccessHandler.class
    │   │                   CustomUserDetail.class
    │   │                   CustomUserDetailsService.class
    │   │                   UserService.class
    │   │                   UserServiceImpl.class
    │   │
    │   ├───static
    │   │   ├───css
    │   │   │       style.css
    │   │   │       styles.css
    │   │   │
    │   │   └───js
    │   │           ind.js
    │   │
    │   └───templates
    │           addinvoCorp.html
    │           admin.html
    │           appointform.html
    │           appointlist.html
    │           assigncase.html
    │           assignCourt.html
    │           assignLawyers.html
    │           assignParalegals.html
    │           casenote.html
    │           casenotelist.html
    │           category.html
    │           cattask.html
    │           choosecase.html
    │           civilCase.html
    │           civilCaseList.html
    │           client.html
    │           client_list.html
    │           corpAppoint.html
    │           corporateCase.html
    │           corporateCaseList.html
    │           Court.html
    │           CourtList.html
    │           criminalCase.html
    │           criminalCaseList.html
    │           dashboard.html
    │           edit_client.html
    │           frontpage.html
    │           invoicelist.html
    │           lawyer.html
    │           lawyerdash.html
    │           law_list.html
    │           login.html
    │           matrimonialCase.html
    │           matrimonialCaseList.html
    │           navbar.html
    │           paralegal.html
    │           paralegaldash.html
    │           para_list.html
    │           register.html
    │           register_form.html
    │           register_success.html
    │           sidebar.html
    │           task.html
    │           taskList.html
    │           user.html
    │           witandeviform.html
    │           witcat.html
    │           witlist.html
    │
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    └───test-classes
        └───com
            └───example
                └───demo
                        DemoApplicationTests.class
```

## Installation

### Prerequisites

- **Java Development Kit (JDK) 11** or higher
- **Maven** (included via `mvnw`)
- **Git** (for cloning the repository)
- **Database**: H2 (default for development), MySQL, or PostgreSQL for production
- **IDE**: IntelliJ IDEA, Eclipse, or any preferred Java IDE

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Aterolite24/DBMS_Project.git
   cd DBMS_Project
   ```

2. **Build the Project**
   The project uses Maven Wrapper, so you don't need to have Maven installed.
   ```bash
   ./mvnw clean install
   ```

3. **Configure the Database**
   - For **H2 Database** (default):
     - No additional configuration needed.
   - For **MySQL/PostgreSQL**:
     - Update the `application.properties` file located in `src/main/resources/` with your database credentials.
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_database
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

4. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```
   The application will start on `http://localhost:8080`.

## Configuration

- **Security Configuration**
  - Located at `src/main/java/com/example/demo/config/SecurityConfig.java`.
  - Manages user authentication and authorization.
  - Customize roles and access permissions as needed.

- **Application Properties**
  - Located at `src/main/resources/application.properties`.
  - Configure database settings, server port, and other application-specific properties.

## Running the Application

Once the application is running, you can access it via your web browser:

```
http://localhost:8080
```

### Default Credentials

- **Admin User**
  - **Username**: `admin`
  - **Password**: `admin123`

> **Note**: It's recommended to change the default admin credentials after the first login for security purposes.

## API Endpoints

The application follows a RESTful architecture. Below are some of the key API endpoints:

### User Management

- **Register a New User**
  - **URL**: `/register`
  - **Method**: `POST`
  - **Body**: `UserDto`

- **Login**
  - **URL**: `/login`
  - **Method**: `GET`

### Client Management

- **Add a New Client**
  - **URL**: `/client`
  - **Method**: `POST`
  - **Body**: `Client`

- **List All Clients**
  - **URL**: `/client/list`
  - **Method**: `GET`

### Case Management

- **Add a New Civil Case**
  - **URL**: `/civilCase`
  - **Method**: `POST`
  - **Body**: `CivilCase`

- **List All Civil Cases**
  - **URL**: `/civilCase/list`
  - **Method**: `GET`

*(Similar endpoints exist for Criminal Cases, Corporate Cases, Matrimonial Cases, etc.)*

### Appointment Management

- **Schedule an Appointment**
  - **URL**: `/appointment`
  - **Method**: `POST`
  - **Body**: `Appointment`

- **List All Appointments**
  - **URL**: `/appointment/list`
  - **Method**: `GET`

### Invoice Management

- **Create an Invoice**
  - **URL**: `/invoice`
  - **Method**: `POST`
  - **Body**: `Invoice`

- **List All Invoices**
  - **URL**: `/invoice/list`
  - **Method**: `GET`

*(Additional endpoints are available for other functionalities like Task Management, Lawyer Management, etc.)*

## Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the Repository**
2. **Create a New Branch**
   ```bash
   git checkout -b feature/YourFeatureName
   ```
3. **Commit Your Changes**
   ```bash
   git commit -m "Add some feature"
   ```
4. **Push to the Branch**
   ```bash
   git push origin feature/YourFeatureName
   ```
5. **Open a Pull Request**

Please ensure your code follows the project's coding standards and includes relevant tests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or support, please contact:

- **Developers**: Achal, Akshun, Manvi, Sanskriti, Vishishta 
- **Email**: aterolite24@gmail.com

---

*Happy Coding!*
