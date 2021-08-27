                                    
                                     ______     ______     ______      
                                    /_____/\   /_____/\   /_____/\     
                                    \:::_ \ \  \::::_\/_  \:::__\/     
                                     \:(_) \ \  \:\/___/\  \:\ \  __   
                                      \: ___\/   \:::._\/   \:\ \/_/\  
                                       \ \ \      \:\ \      \:\_\ \ \ 
                                        \_\/       \_\/       \_____\/ 
                                                                       


## Trabalho de Conclusão de Curso

The following Repository hosts the requested work (TCC) to obtain the Bachelor of Information Systems at the University of Mogi das Cruzes, presented by:

- Diego Guedes Pereira
- Danilo João Esteves

## Features

- Creation of Users with access levels
- Houses for sale Ad
- Schedule Control for House for sale
- PDF reports


## Installation

Requires [JAVA JDK 11](https://www.java.com/pt-BR/) and [DOCKER](https://www.docker.com/) to run.

#### Run docker compose and build the project:

```sh
docker-compose up
```

#### To access pgAdmin provide by docker you need to go: 127.0.0.1:5555 and put credentials email and password defined in the docker-compose file and create server connection. in hostname put:

```sh
windows: host.docker.internal
linux: 172.17.0.1
```

> Note: you need to create databases manually. first go to https://github.com/zebolinha80/PFC-Imobiliaria/blob/master/Banco/ScriptBancoPostGres.sql and copy the script and execute in psql query in postgres.

#### Building

```sh
mvn install / mvn clean install
```

#### Start application in embedded tomcat:

```sh
java -jar target/<GENERATED-JAR-NAME>.jar
```

Verify the deployment by navigating to server address in
your preferred browser.

```sh
127.0.0.1:8080
```
