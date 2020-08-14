# Product-Image-Color-Service
A web service for determining the main colours for product images from the MEC catalog. When a user or client submits a search query, the service should return a list of product image URIs along with their associated colours as hex codes.

## Play with it

This service has been deployed to AWS, you can use following url to play with it. You can replace keyword "bike" with anyone you like
```
http://productimagecolorservice-env.eba-bp2ipzt2.us-east-1.elasticbeanstalk.com/api/v1/product-image-color/search?keyword=bike
```

## Getting Started

These instructions will show you how to build, run the application locally

### Prerequisites

JDK 8; Maven; IntelliJ


### Installing

```
mvn clean install
```
### Run Junit tests
```
mvn clean test
```

### Run the Application
```
./mvnw spring-boot:run
```
### Call the Endpoint

```
curl localhost:8080/api/v1/product-image-color/search?keyword=bike
```

## More Information

### Swagger
```
http://productimagecolorservice-env.eba-bp2ipzt2.us-east-1.elasticbeanstalk.com/swagger-ui/index.html
```

