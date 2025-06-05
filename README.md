# 🍕 Pizzeria - Gestione Ordini e Ingredienti

[![Java](https://img.shields.io/badge/Java-OpenJDK%201.8-blue)]()
[![Spring](https://img.shields.io/badge/Framework-Spring%20Boot%20%7C%20JPA%20%7C%20SOAP%20%7C%20REST-brightgreen)]()
[![Build](https://img.shields.io/badge/Build-Maven-orange)]()
[![Status](https://img.shields.io/badge/Stato-In%20sviluppo-yellow)]()

Sistema gestionale per una pizzeria con funzionalità di CRUD sugli ordini, ingredienti e pizze, esposto sia tramite **Web Service SOAP** che **REST API**, con persistenza dati su database relazionale tramite **JPA**.

---

## 🧱 Tecnologie Utilizzate

- **Java 8 (OpenJDK)**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **SOAP (JAX-WS)**
- **REST (Spring Web)**
- **Database:** MySQL / Oracle (configurabile)
- **Maven** per build e gestione dipendenze
- **Tomcat / WildFly** come application server (in base alla configurazione)

---

## ✨ Funzionalità Implementate

- ✅ CRUD Ingredienti
- ✅ CRUD Pizze (con lista ingredienti)
- ✅ CRUD Ordini
- 🔗 **Web Service SOAP** per la consultazione degli ingredienti
- 🌐 **REST API** per la gestione degli ordini
- 🔄 Conversione tra entità e DTO
- 🧪 Supporto a test (unit test / integrazione)

---

## 🧩 Architettura del Progetto

- **Model**: Entità JPA per Ingredienti, Pizze, Ordini  
- **Repository**: interfacce `JpaRepository` personalizzate  
- **Service Layer**: logica di business isolata dai controller  
- **Controller REST**: endpoint JSON-based  
- **Endpoint SOAP**: classi annotate con `@WebService`  
- **DTO**: per l’esposizione dei dati in formato semplificato  

---

## ⚙️ Configurazione

- **Datasource** configurato tramite `application.properties` (Spring Boot) o `persistence.xml` (Java EE)
- Profilazione tramite branch (es. `springboot`, `datasource`, `soap-adapter`)
- Utilizzo di un server locale (Tomcat o WildFly) per il deploy dei WAR


