# Wissal_Project - Student Management System

A complete mini-project consisting of a Spring Boot REST API for student management, PostgreSQL database, and Flutter mobile application.

## Project Structure

```
/Wissal-project/
├── api-spring-boot/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/wissal/etudiantapi/
│   │       │   ├── entity/
│   │       │   ├── repository/
│   │       │   ├── service/
│   │       │   ├── controller/
│   │       │   └── config/
│   │       └── resources/
│   ├── Dockerfile
│   └── pom.xml
├── mobile-app/
│   ├── lib/
│   │   ├── main.dart
│   │   ├── etudiant.dart
│   │   └── api_service.dart
│   └── pubspec.yaml
├── docker-compose.yml
└── README.md
```

## Features

### Spring Boot API
- **Entity**: Etudiant (Student) with attributes: id, cin, nom, dateNaissance
- **REST Endpoints**:
  - `GET /api/etudiants` - Retrieve all students
  - `GET /api/etudiants/{id}` - Retrieve student by ID
  - `POST /api/etudiants` - Create a new student
  - `PUT /api/etudiants/{id}` - Update a student
  - `DELETE /api/etudiants/{id}` - Delete a student
- **Database**: PostgreSQL with JPA/Hibernate
- **Sample Data**: Automatically populated with 5 students on startup

### Flutter Mobile App
- **HTTP Package**: For API communication
- **Student Model**: Etudiant class with JSON parsing
- **UI**: ListView.builder to display student list
- **Features**: Pull-to-refresh, error handling, loading states

## Prerequisites

- Docker and Docker Compose
- Flutter SDK (for mobile app development)
- Java 17+ (for local development)
- Maven (for local development)

## Quick Start

### 1. Launch Backend Services (Docker)

```bash
docker compose up --build
```

This will start:
- PostgreSQL database on port 5433
- Spring Boot API on port 8080

### 2. Test the API

Open your browser or use curl to test:

```bash
curl http://localhost:8080/api/etudiants
```

Expected response:
```json
[
  {
    "id": 1,
    "cin": "CIN123456",
    "nom": "Mohammed Ali",
    "dateNaissance": "2000-05-15"
  },
  // ... more students
]
```

### 3. Run Flutter Mobile App

```bash
cd mobile-app
flutter pub get
flutter run
```

**Important**: The app is configured to connect to `http://10.0.2.2:8080` for Android emulator. For real device testing, update the `baseUrl` in `lib/api_service.dart` to your machine's IP address.

## API Endpoints

### Get All Students
- **Method**: GET
- **URL**: `http://localhost:8080/api/etudiants`
- **Response**: Array of student objects

### Create Student
- **Method**: POST
- **URL**: `http://localhost:8080/api/etudiants`
- **Body**: 
```json
{
  "cin": "CIN123456",
  "nom": "Student Name",
  "dateNaissance": "2000-01-01"
}
```

### Update Student
- **Method**: PUT
- **URL**: `http://localhost:8080/api/etudiants/{id}`
- **Body**: 
```json
{
  "cin": "CIN999999",
  "nom": "Updated Name",
  "dateNaissance": "2000-01-01"
}
```

### Delete Student
- **Method**: DELETE
- **URL**: `http://localhost:8080/api/etudiants/{id}`
- **Response**: "Etudiant deleted successfully"

## Database Schema

```sql
CREATE TABLE etudiants (
    id BIGSERIAL PRIMARY KEY,
    cin VARCHAR(255) NOT NULL,
    nom VARCHAR(255) NOT NULL,
    date_naissance DATE NOT NULL
);
```

## Development

### Running API Locally (without Docker)

1. Navigate to `api-spring-boot`
2. Run: `mvn spring-boot:run`
3. Make sure PostgreSQL is running locally on port 5432

### Running Tests

```bash
# API Tests
cd api-spring-boot
mvn test

# Flutter Tests
cd mobile-app
flutter test
```

## Troubleshooting

### Common Issues

1. **API Connection Error in Flutter App**
   - Ensure Docker services are running
   - Check the IP address in `api_service.dart`
   - For Android emulator: use `10.0.2.2`
   - For real device: use your machine's local IP

2. **Database Connection Error**
   - Check if PostgreSQL container is running
   - Verify database credentials in `application.properties`

3. **Build Errors**
   - Ensure Java 17+ is installed
   - Run `mvn clean install` in the API directory

### Logs

- **API Logs**: View with `docker logs spring-boot-api`
- **Database Logs**: View with `docker logs postgres_db`

## Technologies Used

- **Backend**: Spring Boot 3.2.0, Spring Data JPA, PostgreSQL
- **Frontend**: Flutter, HTTP package
- **Containerization**: Docker, Docker Compose
- **Build Tools**: Maven, Flutter SDK

## Author

Wissal_Project - Student Management System
