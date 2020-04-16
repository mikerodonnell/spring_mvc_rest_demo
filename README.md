## about
A demo REST API service implemented with [Spring MVC](http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/mvc.html) with automated [REST assured](https://github.com/jayway/rest-assured) integration tests.
The use case is a recipe service for creating recipes and searching for recipes by ingredient.

## examples
create a recipe:
```
curl -X PUT --header "Content-Type: application/json" --data '{"recipeName":"bread", "ingredientName1":"flour", "amountUnit1":"cup", "amount1":3, "ingredientName2":"banana", "amountUnit2":"pile", "amount2":4, "ingredientName3":"eggs", "amountUnit3":"handful", "amount3":77 }' "http://localhost:8080/spring_mvc_rest_demo/recipe/create"
```

get a recipe by id:
```
curl "http://localhost:8080/spring_mvc_rest_demo/recipe/c5515b57-55e9-4f37-963b-dc73ec399fb8"
```

get a recipe by id invalid id (does-not-exist error message):
```
curl "http://localhost:8080/spring_mvc_rest_demo/recipe/yayayayaya"
```

create a recipe that already exists (already-exists error message):
```
curl -X PUT --header "Content-Type: application/json" --data '{"recipeName":"soylent"}' "http://localhost:8080/spring_mvc_rest_demo/recipe/create"
```

update a recipe:
```
curl -X PUT --header "Content-Type: application/json" --data '{ "prepMinutes": 99 }' "http://localhost:8080/spring_mvc_rest_demo/recipe/update/c5515b57-55e9-4f37-963b-dc73ec399fb8"
```

search for recipes:
```
curl "http://localhost:8080/spring_mvc_rest_demo/recipe/search/ham"
```

## stack:
- Spring MVC
- REST assured
- Maven with [Jetty](http://www.eclipse.org/jetty/documentation/current/jetty-maven-plugin.html) and [Failsafe](https://maven.apache.org/surefire/maven-failsafe-plugin) plugins
- JUnit
- [Jackson](https://github.com/FasterXML/jackson) JSON Mapper

## usage:
1. `git clone git@github.com:mikerodonnell/spring_mvc_rest_demo.git`
1. `mvn clean install`
1. deploy the spring_mvc_rest_demo/target/spring_mvc_rest_demo.war file to any Java EE server container.
1. make API requests using cURL or any HTTP client tool as shown in the examples.
