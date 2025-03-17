
# Applicazione Gestione Biblioteca - Spring Boot

Questa applicazione è un sistema di gestione di una biblioteca, sviluppato con Spring Boot. Consente di gestire libri, autori e utenti tramite un'interfaccia web. Include funzionalità come l'autenticazione dell'utente, l'uso di DTO (Data Transfer Objects) per la gestione delle richieste e delle risposte, e l'integrazione di WebSocket per una comunicazione in tempo reale.

## Funzionalità

- **Gestione dei Libri**: È possibile visualizzare, aggiungere, modificare e rimuovere libri dalla biblioteca.
- **Gestione degli Autori**: Gli autori dei libri sono gestiti separatamente, con la possibilità di visualizzare e aggiungere autori.
- **Gestione degli Utenti**: Gli utenti possono autenticarsi per accedere al sistema e gestire i propri dati.
- **Swagger**: È integrato Swagger per la documentazione e l'interazione con le API RESTful.
- **WebSocket**: Implementazione di WebSocket per la comunicazione in tempo reale.
- **DTO**: Uso di DTO per una migliore separazione tra le entità di dominio e le risposte o richieste API.

## Tecnologie Utilizzate

- **Spring Boot**: Framework principale per lo sviluppo dell'applicazione.
- **Spring Web**: Per la gestione delle API RESTful.
- **Spring Security**: Per l'autenticazione e la gestione degli utenti.
- **Spring Data JPA**: Per l'accesso al database con Hibernate.
- **Swagger**: Per la documentazione delle API e l'interazione tramite un'interfaccia grafica.
- **WebSocket**: Per la gestione della comunicazione in tempo reale.
- **Thymeleaf**: Per la creazione delle pagine HTML dinamiche.
- **H2 Database**: Database in memoria per la gestione dei dati (è possibile configurare un database diverso).

## Avvio del Progetto

### Prerequisiti

- Java 17 o superiore
- Maven o Gradle per la gestione delle dipendenze
- Un IDE come IntelliJ IDEA o Eclipse

### Passaggi per avviare l'applicazione

1. **Clonare il repository**:

   ```bash
   git clone https://github.com/NotSoupCarry/BackEnd_JavaLibrary.git
   ```

2. **Navigare nella cartella del progetto**:

   ```bash
   cd BackEnd_JavaLibrary
   ```

3. **Compilare il progetto**:

   Se usi Maven:
   ```bash
   mvn clean install
   ```

   Se usi Gradle:
   ```bash
   gradle build
   ```

4. **Avviare l'applicazione**:

   Se usi Maven:
   ```bash
   mvn spring-boot:run
   ```

   Se usi Gradle:
   ```bash
   gradle bootRun
   ```

   L'applicazione sarà avviata su `http://localhost:8080`.

## Documentazione delle API

Le API RESTful sono documentate tramite **Swagger**. Puoi accedere all'interfaccia di Swagger all'indirizzo:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger ti permette di visualizzare tutte le rotte API, i metodi supportati (GET, POST, PUT, DELETE), e di interagire con esse direttamente.

## WebSocket

L'applicazione utilizza WebSocket per inviare messaggi in tempo reale. Puoi connetterti al WebSocket all'indirizzo:

```
ws://localhost:8080/ws
```

## Struttura del Progetto

- `src/main/java/com/example/library/`: Contiene la logica dell'applicazione, inclusi i controller, servizi, repository e la configurazione WebSocket.
- `src/main/resources/`: Contiene i file di configurazione, tra cui `application.properties` per configurare il database, il server, ecc.
- `src/main/resources/static/`: Contiene i file statici (come CSS, JavaScript, immagini) per la parte frontend.
- `src/main/resources/templates/`: Contiene i file di template Thymeleaf per il rendering delle pagine web.

## Configurazione

### Configurazione del database

L'applicazione è configurata per utilizzare un database **H2** in memoria. Per configurare un altro database, modifica il file `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

### Configurazione di WebSocket

WebSocket è configurato nel file `WebSocketConfig.java`:

```java
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(), "/ws").setAllowedOrigins("*");
    }
}
```

### Swagger

Swagger è abilitato nel progetto tramite la dipendenza `springfox-swagger2`. Puoi personalizzare il comportamento di Swagger nel file di configurazione `SwaggerConfig.java`.

## Contribuire

Se desideri contribuire a questo progetto, puoi fare un fork del repository, implementare le modifiche e inviare una pull request.

### Esempio di Pull Request

1. Crea un nuovo branch per la tua funzionalità:
   ```bash
   git checkout -b nome-branch
   ```

2. Implementa la tua funzionalità e aggiungi test se necessario.
3. Commetti le tue modifiche:
   ```bash
   git commit -m "Descrizione della tua modifica"
   ```

4. Pusha le modifiche:
   ```bash
   git push origin nome-branch
   ```

5. Invia una pull request tramite GitHub.

---

Grazie per aver utilizzato il progetto! Se hai domande, non esitare a creare una issue nel repository o a contattare gli sviluppatori.
