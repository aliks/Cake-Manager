Build with maven (run command from cake-manager directory)
```
mvn intall
```

Run from project directory
``` 
java -jar target/cake-manager-0.0.1-SNAPSHOT.jar
```
In browser
``` 
http://localhost:8080/ or http://localhost:8080/cakes
```
In Postman GET Request ```http://localhost:8080/cakes``` this will return JSON string.
POST ```http://localhost:8080/cakes```
with the body 
```
{
  "title": "alex-cake", 
  "desc": "my first cake", 
  "image": "https://cake-picture-url.com"
}
```
will add a new cake


Build docker image
```
docker build . --tag=cake-app
```
run docker image
```
docker run -p 8080:8080/tcp cake-app
```
and access via localhost:8080/