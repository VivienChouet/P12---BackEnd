<h1 align="center"> P 12 </h1> <br>

<p align="center">
  API pour le projet 12 d'OpenClassroom
</p>


## Table of content

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)





## Introduction


Ceci est l'api concernant le projet 12 de openClassroom dans le cadre de la formation de devellopeur java.
Cette API à été concu pour recenser et mettre en avant les chateaux viticoles.

## Features

* Gestion ajout / update / visualisation des chateaux
* Gestion de fichier d'image
* Securiser avec JWT


## Requirements
L'application a été develloper pour tourner en local


### Local
* [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)

## Quick Start

#### MySQL
Renseignez l'adresse ainsi que vos identifiant MySQL dans le fichier Ressources/properties
```bash
spring.datasource.url= `Url`
spring.datasource.username= `Username`
spring.datasource.password= `Password`
```

#### Maven
Ouvrez votre Terminal et assurez vous d'être dans le dossier Back et rentrez :

```bash
$ mvn clean
$ mvn install
```

### Run Local
```bash
$ mvn spring-boot:run
```

Application utiliser par defaut le port `8080`

### Jeux de donnée

Vous trouverez le script d'installation de la base de donnée dans le dossier script SQL et le fichier se nomme ```script.sql```

Une fois la base de donnée installé vous pourrez y ajouter mon jeux de donné se trouvant dans le même dossier.
Importer les fichiers dans cet ordre : 
```bash 
User > Chateau > File > Commentaire.
```

Sur ce jeu de donnée vous avez accès a 2 chateau ainsi qu'a 3 user pour se connecter avec les différent user voici les login / password : 
```bash
user1@test / user1
user2@test / user2
admin@admin / admin
```