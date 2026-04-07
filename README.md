# Projet de Gestion des Étudiants - API REST Spring Boot v2.0

## 📋 Vue d'ensemble

Ce projet est une API REST complète pour la gestion des étudiants et des départements, développée avec Spring Boot 3.2.0 et enrichie avec les meilleures pratiques de développement.

## 🏗️ Architecture

### Structure du projet
```
api-spring-boot/
├── src/
│   ├── main/
│   │   ├── java/com/wissal/etudiantapi/
│   │   │   ├── controller/     # Contrôleurs REST
│   │   │   ├── service/        # Logique métier
│   │   │   ├── repository/     # Accès aux données
│   │   │   ├── entity/         # Entités JPA
│   │   │   ├── dto/           # Objets de transfert
│   │   │   ├── mapper/         # Conversion DTO ↔ Entité
│   │   │   ├── config/         # Configuration
│   │   │   └── exception/      # Gestion des erreurs
│   │   └── resources/
│   │       ├── static/index.html # Interface web
│   │       └── application.properties
│   └── test/
│       └── resources/features/  # Tests BDD Cucumber
├── Dockerfile
├── pom.xml
└── k8s/                       # Manifests Kubernetes
    ├── etudiant-deployment.yaml
    └── postgres-deployment.yaml
```

## 🚀 Fonctionnalités

### ✅ Partie 1 - API REST de base
- [x] CRUD complet pour les étudiants
- [x] Base de données PostgreSQL
- [x] Conteneurisation Docker
- [x] Interface web avec JavaScript

### ✅ Partie 2 - Enrichissement
- [x] **Méthode `age()`** : Calcul dynamique de l'âge
- [x] **Tests BDD** : Cucumber + Gherkin
- [x] **Architecture en couches** : DTOs, Mappers, Services
- [x] **Entité Département** : Relation ManyToOne
- [x] **Requête personnalisée** : Filtrage par année d'inscription
- [x] **Gestion des erreurs** : `@RestControllerAdvice`
- [x] **Documentation Swagger** : OpenAPI 3.0
- [x] **Cache Redis** : `@Cacheable` et `@CacheEvict`
- [x] **Kubernetes** : Manifests pour K3S
- [x] **Publication Docker** : Image prête pour déploiement

## 📱 Screenshots

### Application Flutter
![Student List App](screenshots/app-screenshot.png)

*Interface utilisateur de l'application Flutter affichant la liste des étudiants*

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

## 📊 Projet Jira Scrum

### Epic principale : **Gestion des Étudiants**

#### Sprint 1 - API REST de base (Partie 1)
| Ticket | Type | Titre | Statut |
|--------|------|-------|--------|
| US-01 | User Story | Lister les étudiants | ✅ Terminé |
| US-02 | User Story | Dockeriser l'application | ✅ Terminé |
| US-03 | User Story | Interface web simple | ✅ Terminé |

#### Sprint 2 - Enrichissement (Partie 2)
| Ticket | Type | Titre | Statut |
|--------|------|-------|--------|
| US-04 | User Story | Méthode age() + tests BDD | ✅ Terminé |
| US-05 | User Story | Architecture en couches propres | ✅ Terminé |
| US-06 | User Story | Gestion des départements | ✅ Terminé |
| US-07 | User Story | Cache Redis | ✅ Terminé |
| US-08 | User Story | Documentation Swagger | ✅ Terminé |
| US-09 | User Story | Déploiement Kubernetes | ✅ Terminé |

### Board Jira
*(Capture d'écran du board Jira à ajouter dans screenshots/jira-board.png)*

## 🔧 Technologies

### Backend
- **Java 17** : Langage principal
- **Spring Boot 3.2.0** : Framework principal
- **Spring Data JPA** : Accès aux données
- **PostgreSQL 15** : Base de données
- **Redis 7** : Cache
- **Maven** : Gestion des dépendances

### Tests & Qualité
- **Cucumber 7.14.0** : Tests BDD
- **JUnit 5** : Tests unitaires
- **Lombok** : Réduction de code boilerplate

### Documentation & Monitoring
- **SpringDoc OpenAPI 3.0** : Documentation API
- **Swagger UI** : Interface interactive

### Déploiement
- **Docker** : Conteneurisation
- **Kubernetes (K3S)** : Orchestration
- **Docker Compose** : Développement local

## 📝 Modèle de données

### Étudiant
```json
{
  "id": 1,
  "cin": "CIN123456",
  "nom": "Mohammed Ali",
  "dateNaissance": "2000-05-15",
  "email": "mohammed.ali@example.com",
  "anneePremiereInscription": 2020,
  "departementId": 1,
  "departementNom": "Informatique",
  "age": 23
}
```

### Département
```json
{
  "id": 1,
  "nom": "Informatique"
}
```


---

**Développé avec ❤️ par Wissal Project - Version 2.0**
