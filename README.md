# LabAir API

This is my third and last project I've done that is assigned to me do during the LABFORWEB course. 
It's a Backend REST API to connect the previous project **LabAir**-project-v2 (frontend), a hypothetical e-commerce of shoes based on Nike.com that is constructed with Angular as the main framework.

Questo servizio fornisce tutte le API necessarie al frontend Angular per la gestione di utenti, catalogo prodotti, carrello e ordini.

---

## Configuration

- **Java 17**
- **Spring Boot**
- **Spring Security** + **JWT** (autenticazione e autorizzazione)
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Springdoc OpenAPI** (documentazione API)
- **Maven**

---

## Funzionalità principali

- Registrazione e autenticazione utenti (JWT)
- Gestione catalogo scarpe (CRUD + filtri per categoria e ordinamento)
- Gestione carrello
- Gestione ordini e dati di spedizione
- Supporto CORS per il frontend Angular (`http://localhost:4200`)

---

## Struttura del progetto

src/main/java/labair_api/
├── config/          # Configurazione Security, JWT Filter, Request/Response
├── controllers/     # Endpoint REST
├── dto/             # Data Transfer Objects
├── exceptions/      # Gestione eccezioni personalizzate
├── models/          # Entità JPA
├── repositories/    # Interfacce Spring Data JPA
└── services/        # Logica di business


### Controllers principali

| Controller            | Path base                  | Descrizione                          |
|-----------------------|----------------------------|--------------------------------------|
| `AuthController`      | `/api/v1/auth`             | Registrazione e login                |
| `ShoeController`      | `/api/v1/scarpeList`       | Catalogo scarpe (CRUD + filtri)      |
| `CartItemController`  | -                          | Gestione carrello                    |
| `OrderController`     | -                          | Gestione ordini                      |
| `UserController`      | -                          | Gestione utenti                      |

---

## Modelli principali

- **User** – Utenti del sistema
- **Shoe** – Prodotti (scarpe) con taglie, colori, immagini e flag (nuovi arrivi / best seller)
- **CartItem** – Elementi del carrello
- **Order** + **OrderDetails** – Ordini e dettagli
- **ShippingData** – Dati di spedizione
- **ImageColor** – Immagini associate ai colori delle scarpe

---

## Configurazione

Il file `application.properties` contiene la configurazione del database:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/labair_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=create-drop
```
Nota: in produzione modificare le credenziali e impostare ddl-auto su un valore più sicuro (validate o none).

### Come avviare il progetto
Prerequisiti

Java 17+
Maven
MySQL in esecuzione

##### Passi

Clona il repository:Bashgit clone https://github.com/LPSimeon/LABAir-project-api.git
cd LABAir-project-api
Assicurati che MySQL sia attivo e che esista il database labair_db (oppure lascia che venga creato automaticamente).
Avvia l'applicazione:Bash./mvnw spring-boot:run

L'API sarà disponibile su:

http://localhost:8080

Endpoint di autenticazione

MetodoEndpointDescrizionePOST/api/v1/auth/registerRegistrazione nuovo utentePOST/api/v1/auth/authenticateLogin e generazione JWT

Repository correlata
Frontend Angular del progetto:

LABAir-project-v2

Autore
LPSimeon

Progetto realizzato nell’ambito del corso di formazione LabForWeb.